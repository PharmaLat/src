package GestioneOrdini;

import Autenticazione.Utente;
import Connectivity.DBMSInterface;
import GestioneMagazzino.Farmaco;
import Main.SchermataPrincipale;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Map;

public class OrdiniPeriodiciControl {

    private SchermataPrincipale s;
    private Utente utente;
    private DBMSInterface db;
    private SchermataOrdiniPeriodici schermataOrdiniPeriodici;
    private List<Map<Farmaco,String>> ordiniListPeriodici;

    public OrdiniPeriodiciControl(SchermataPrincipale sc, Utente u, DBMSInterface db){
        this.s = sc;
        this.utente = u;
        this.db = db;
        visualizzaOrdini();
    }

    public void formVisualizzaOrdini(){

        schermataOrdiniPeriodici.getLblCenter1().setLayout(new GridLayout(this.ordiniListPeriodici.size(), 1, 20, 5 ));
        schermataOrdiniPeriodici.getLblCenter2().setLayout(new GridLayout(this.ordiniListPeriodici.size(), 1,20, 5 ));
        schermataOrdiniPeriodici.getLblCenter3().setLayout(new GridLayout(this.ordiniListPeriodici.size(), 1, 20, 5 ));
        schermataOrdiniPeriodici.getLblCenter4().setLayout(new GridLayout(this.ordiniListPeriodici.size(), 1, 20, 5 ));
        schermataOrdiniPeriodici.getBtnCenter1().setLayout(new GridLayout(this.ordiniListPeriodici.size(), 1, 20, 5 ));

        for (int i = 0; i < this.ordiniListPeriodici.size(); i++) {

            String id = "";
            String nome = "";
            String qta = "";
            String periodicita = "";
            String data = "";
            for (Map.Entry<Farmaco, String> entry : ordiniListPeriodici.get(i).entrySet()) {
                String info[] = entry.getValue().split("-");
                nome = entry.getKey().getNome();
                qta = String.valueOf(entry.getKey().getQuantita());
                data = entry.getKey().getData();
                id = info[1];
            }

            JButton btnM = new JButton("Modifica");
            btnM.addActionListener(modificaListener);
            btnM.setActionCommand(id);

            schermataOrdiniPeriodici.getLblCenter1().add(new JLabel(nome));
            schermataOrdiniPeriodici.getLblCenter2().add(new JLabel(qta));
            schermataOrdiniPeriodici.getLblCenter3().add(new JLabel(periodicita));
            schermataOrdiniPeriodici.getLblCenter4().add(new JLabel(data));
            schermataOrdiniPeriodici.getBtnCenter1().add(btnM);
        }
    }

    ActionListener modificaListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {

            JButton btn = (JButton) e.getSource();
            /*
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
            }*/
            System.out.println("SONO QUI " + btn.getActionCommand());
        }
    };

    public void visualizzaOrdini(){
        JButton visualizza = s.getVisOrdiniPeriodici();
        ActionListener visualizzaPeriodiciListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ordiniListPeriodici = db.getOrdiniPeriodici(utente.getIndirizzoFarmacia());
                System.out.println(ordiniListPeriodici);
                schermataOrdiniPeriodici  = new SchermataOrdiniPeriodici();
                formVisualizzaOrdini();
            }
        };
        visualizza.addActionListener(visualizzaPeriodiciListener);
    }

}
