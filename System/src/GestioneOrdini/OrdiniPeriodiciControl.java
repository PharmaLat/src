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

public class OrdiniPeriodiciControl {

    private SchermataPrincipale s;
    private Utente utente;
    private DBMSInterface db;
    private SchermataOrdiniPeriodici schermataOrdiniPeriodici;
    private ArrayList<Ordine> ordiniPeriodici = new ArrayList<Ordine>();

    public OrdiniPeriodiciControl(SchermataPrincipale sc, Utente u, DBMSInterface db){
        this.s = sc;
        this.utente = u;
        this.db = db;
        visualizzaOrdini();
    }
    public void visualizzaOrdini(){
        JButton visualizza = s.getVisOrdiniPeriodici();
        ActionListener visualizzaPeriodiciListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ordiniPeriodici = db.getOrdiniPeriodici(utente.getIndirizzoFarmacia());
                System.out.println(ordiniPeriodici);
                schermataOrdiniPeriodici  = new SchermataOrdiniPeriodici();
                formVisualizzaOrdini();
            }
        };
        visualizza.addActionListener(visualizzaPeriodiciListener);
    }

    public void formVisualizzaOrdini(){

        schermataOrdiniPeriodici.getLblCenter1().setLayout(new GridLayout(this.ordiniPeriodici.size(), 1, 20, 5 ));
        schermataOrdiniPeriodici.getLblCenter2().setLayout(new GridLayout(this.ordiniPeriodici.size(), 1,20, 5 ));
        schermataOrdiniPeriodici.getLblCenter3().setLayout(new GridLayout(this.ordiniPeriodici.size(), 1, 20, 5 ));
        schermataOrdiniPeriodici.getLblCenter4().setLayout(new GridLayout(this.ordiniPeriodici.size(), 1, 20, 5 ));
        schermataOrdiniPeriodici.getBtnCenter1().setLayout(new GridLayout(this.ordiniPeriodici.size(), 1, 20, 5 ));

        for (int i = 0; i < this.ordiniPeriodici.size(); i++) {

            JButton btnM = new JButton("Modifica");
            btnM.addActionListener(modificaListener);
            btnM.setActionCommand(String.valueOf(ordiniPeriodici.get(i).getID_O()));

            schermataOrdiniPeriodici.getLblCenter1().add(new JLabel(ordiniPeriodici.get(i).getFarmaci().get(0).getNome()));
            schermataOrdiniPeriodici.getLblCenter2().add(new JLabel(String.valueOf(ordiniPeriodici.get(i).getFarmaci().get(0).getQuantita())));
            schermataOrdiniPeriodici.getLblCenter3().add(new JLabel(ordiniPeriodici.get(i).getFarmaci().get(0).getPrincipioAttivo()));
            schermataOrdiniPeriodici.getLblCenter4().add(new JLabel(ordiniPeriodici.get(i).getDataUltimoOrdine()));
            schermataOrdiniPeriodici.getBtnCenter1().add(btnM);

        }
    }

    ActionListener modificaListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {

            JButton btn = (JButton) e.getSource();

            for(int i = 0; i < ordiniPeriodici.size(); i++){
                if(btn.getActionCommand().equals(String.valueOf(ordiniPeriodici.get(i).getID_O()))){
                    System.out.println("SONO QUI " + ordiniPeriodici.get(i).getID_O());
                    new ModificaPeriodicoControl(schermataOrdiniPeriodici, utente, db,  ordiniPeriodici, i);
                    break;
                }
            }
        }
    };

}
