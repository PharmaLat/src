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
    private ArrayList<Ordine> ordiniList;

    public VisualizzaOrdiniControl(SchermataPrincipale sc, Utente u, DBMSInterface db){
        this.s = sc;
        this.utente = u;
        this.db = db;
        visualizzaOrdini();
    }

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

    public void formVisualizzaOrdini(){

        scOrdini.getGridCenter().setLayout(new GridLayout(this.ordiniList.size(), 1));
        scOrdini.getLblCenter().setLayout(new GridLayout(this.ordiniList.size(), 1));
        scOrdini.getBtnCenter1().setLayout(new GridLayout(this.ordiniList.size(), 1));
        scOrdini.getBtnCenter2().setLayout(new GridLayout(this.ordiniList.size(), 1));

        for (int i = 0; i < this.ordiniList.size(); i++) {

            JPanel pnlFarm = new JPanel(new GridLayout(this.ordiniList.get(i).getFarmaci().size(), 1));//griglia farmaci

            for(int j = 0; j < this.ordiniList.get(i).getFarmaci().size(); j++){
                pnlFarm.add(new JLabel(ordiniList.get(i).getFarmaci().get(j).getNome()));
            }
            ScrollPane scroll = new ScrollPane();
            scroll.setPreferredSize(new Dimension(300, 100));
            scroll.add(pnlFarm);


            JButton btnE = new JButton("Elimina");
            btnE.addActionListener(eliminaListener);
            btnE.setActionCommand(String.valueOf(ordiniList.get(i).getID_O()));

            JButton btnM = new JButton("Modifica");
            btnM.addActionListener(modificaListener);
            btnM.setActionCommand(String.valueOf(ordiniList.get(i).getID_O()));

            scOrdini.getGridCenter().add(scroll);
            scOrdini.getLblCenter().add(new JLabel(ordiniList.get(i).getDataConsegna()));
            scOrdini.getBtnCenter2().add(btnE);
            scOrdini.getBtnCenter1().add(btnM);
        }

    }

    ActionListener eliminaListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton btn = (JButton) e.getSource();

            for (int i = 0; i < ordiniList.size(); i++) {
                    if(btn.getActionCommand().equals(String.valueOf(ordiniList.get(i).getID_O()))) {
                        if(isModificabile(ordiniList.get(i))) {
                            new EliminaOrdineControl(db, ordiniList.get(i).getID_O());
                            scOrdini.dispose();
                        }
                        break;
                }
            }
        }
    };
    ActionListener modificaListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {

            JButton btn = (JButton) e.getSource();

            for (int i = 0; i < ordiniList.size(); i++) {
                if(btn.getActionCommand().equals(String.valueOf(ordiniList.get(i).getID_O()))) {
                    if(isModificabile(ordiniList.get(i)))
                        new ModificaOrdineControl(scOrdini, utente, db, ordiniList, i);
                    break;
                }
            }
        }
    };

    public boolean isModificabile(Ordine o){
        System.out.println("check");
        if(o.getStato().equals("In consegna") || o.getStato().equals("Consegnato")){
            System.out.println("Ordine in consegna");
            return false;
        }
        return true;
    }
}
