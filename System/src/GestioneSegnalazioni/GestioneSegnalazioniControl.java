package GestioneSegnalazioni;

import Autenticazione.Utente;
import Connectivity.DBMSInterface;
import GestioneMagazzino.Farmaco;
import Main.SchermataPrincipale;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class GestioneSegnalazioniControl {

    private SchermataPrincipale s;
    private Utente u;
    private VisualizzaSegnalazioni vs;
    private DBMSInterface db;

    public GestioneSegnalazioniControl(SchermataPrincipale s, Utente u, DBMSInterface db) {
        this.s = s;
        this.u = u;
        this.db = db;
        visualizzaSegnalazioni();
    }

    private void visualizzaSegnalazioni(){
        JButton visualizza = s.getVisualizzaSegnalazioni();

        ActionListener visualizzaLstnr = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Cliccato visualizza segnalazioni");
                vs = new VisualizzaSegnalazioni();
                gestisciSegnalazioni();
            }
        };
        visualizza.addActionListener(visualizzaLstnr);
    }

    JLabel ordine;
    JButton nuovoOrdine;
    JButton modificaOrdine;
    JButton chiudiSegnalazione;
    private void gestisciSegnalazioni(){
        ArrayList<Segnalazione> segnalazioni = db.getSegnalazioni();
        JPanel segnalazioniPanel =vs.getSegnalazioniPnl();
        segnalazioniPanel.setLayout(new GridLayout(segnalazioni.size(), 4, 20, 10));
        JPanel flow1 = new JPanel();
        flow1.setLayout(new BoxLayout(flow1, BoxLayout.Y_AXIS));

        for (int i = 0; i < segnalazioni.size(); i++) {
            JPanel grid = new JPanel(new GridLayout(1, 4, 20, 10));
            int idOrdine = segnalazioni.get(i).getID_O();
            ordine = new JLabel("Ordine N. "+ idOrdine);
            nuovoOrdine = new JButton("Nuovo Ordine");
            modificaOrdine = new JButton("Modifica Ordine");
            chiudiSegnalazione = new JButton("Chiudi Segnalazione");

            nuovoOrdine.setActionCommand(idOrdine+"");
            modificaOrdine.setActionCommand(idOrdine+"");
            chiudiSegnalazione.setActionCommand(idOrdine+"");

            nuovoOrdine.addActionListener(e -> {
                NuovoOrdineAziendaForm ordine = new NuovoOrdineAziendaForm();
                System.out.println("Cliccato nuovo ordine di id "+e.getActionCommand());
                ArrayList<Farmaco> farmaci = db.getOrdine(Integer.parseInt(e.getActionCommand()));
                JPanel label = ordine.getLabelPnl();
                JPanel text = ordine.getTextPnl();
                JTextField qta;

                for (int j = 0; j < farmaci.size(); j++) {
                    JPanel label1 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
                    JPanel text1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
                    JLabel nome = new JLabel(farmaci.get(j).getNome());
                    qta = new JTextField();

                    qta.setColumns(10);
                    label1.add(nome);
                    text1.add(qta);
                    label.add(label1);
                    text.add(text1);
                }
            });
            modificaOrdine.addActionListener(e -> {

            });
            chiudiSegnalazione.addActionListener(e -> {});

            grid.add(ordine);
            grid.add(nuovoOrdine);
            grid.add(modificaOrdine);
            grid.add(chiudiSegnalazione);
            flow1.add(grid);
        }
        segnalazioniPanel.add(flow1);
    }


}
