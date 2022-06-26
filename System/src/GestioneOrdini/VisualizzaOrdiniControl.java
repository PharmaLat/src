package GestioneOrdini;

import Autenticazione.Utente;
import Connectivity.DBMSInterface;
import GestioneMagazzino.Farmaco;
import Main.SchermataPrincipale;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class VisualizzaOrdiniControl {

    private SchermataPrincipale s;
    private Utente utente;
    private DBMSInterface db;
    private SchermataOrdini scOrdini;
    private List<Map<Farmaco,String>> ordiniList;

    public VisualizzaOrdiniControl(SchermataPrincipale sc, Utente u, DBMSInterface db){
        this.s = sc;
        this.utente = u;
        this.db = db;
        visualizzaOrdini();
    }

    public void formVisualizzaOrdini(){

        scOrdini.getGridCenter().setLayout(new GridLayout(this.ordiniList.size(), 1));

        for (int i = 0; i < this.ordiniList.size(); i++) {
            JPanel pnlFarm = new JPanel(new GridLayout(this.ordiniList.get(i).size(), 1));
            scOrdini.getLblCenter().setLayout(new GridLayout(this.ordiniList.size(), 1));
            scOrdini.getBtnCenter1().setLayout(new GridLayout(this.ordiniList.size(), 1));
            JButton btnE = new JButton("Elimina");
            btnE.addActionListener(eliminaListener);
            JButton btnM = new JButton("Modifica");
            btnM.addActionListener(modificaListener);
            String id = "";
            String data = "";
            for (Map.Entry<Farmaco, String> entry : ordiniList.get(i).entrySet()) {
                pnlFarm.add(new JLabel(entry.getKey().getNome()));
                String info[] = entry.getValue().split("-");
                id = info[1];
            }
            btnE.setActionCommand(id);
            btnM.setActionCommand(id);
            scOrdini.getLblCenter().add(new JLabel(data));
            scOrdini.getBtnCenter2().add(btnE);
            scOrdini.getBtnCenter1().add(btnM);
            ScrollPane scroll = new ScrollPane();
            scroll.add(pnlFarm);
            scOrdini.getGridCenter().add(scroll);
        }

        scOrdini.getBtnCenter2().setLayout(new GridLayout(this.ordiniList.size(), 1));
    }

    ActionListener eliminaListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton btn = (JButton) e.getSource();

            String id = "";
            for (int i = 0; i < ordiniList.size(); i++) {
                for (Map.Entry<Farmaco, String> entry : ordiniList.get(i).entrySet()) {
                    String info[] = entry.getValue().split("-");
                    id = info[1];
                }
                //if(isModificabile()){
                    if(btn.getActionCommand().equals(id)) {
                        int conferma = JOptionPane.showConfirmDialog(scOrdini, "Vuoi eliminare l'ordine");
                        if (conferma == JOptionPane.YES_OPTION) {
                            new EliminaOrdineControl(db, id);
                            scOrdini.dispose();
                            ordiniList = db.getOrdini(utente.getIndirizzoFarmacia());
                            break;
                        }
                  //   }
                }
            }
        }
    };
    ActionListener modificaListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton btn = (JButton) e.getSource();

            String id = "";
            for (int i = 0; i < ordiniList.size(); i++) {
                for (Map.Entry<Farmaco, String> entry : ordiniList.get(i).entrySet()) {
                    String info[] = entry.getValue().split("-");
                    id = info[1];

                }
                //if(isModificabile()){
                if(btn.getActionCommand().equals(id)) {
                    new ModificaOrdineControl(scOrdini, utente, db, id, ordiniList, i);
                    break;
                    //   }
                }
            }
        }
    };


    public void visualizzaOrdini(){
        JButton visualizza = s.getVisOrdini();
        ActionListener visualizzaListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ordiniList = db.getOrdini(utente.getIndirizzoFarmacia());
                scOrdini  = new SchermataOrdini();
                formVisualizzaOrdini();
            }
        };
        visualizza.addActionListener(visualizzaListener);
    }

    public boolean isModificabile(Ordine o){
        System.out.println("check");
        return true;
    }
}

/*
for (int i = 0; i < listaOrdini.size(); i++) {
        System.out.println("GestioneOrdini.GestioneOrdini.Ordine "+i);
        for (Map.Entry<Farmaco, String> entry : listaOrdini.get(i).entrySet()) {
        System.out.println(entry.getKey()+ " " + entry.getValue());
        }
}

 */