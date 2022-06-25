package GestioneSegnalazioni;

import Autenticazione.Utente;
import Connectivity.DBMSInterface;
import GestioneMagazzino.Farmaco;
import GestioneOrdini.OrdFarmaciControl;
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
                NuovoOrdineAziendaForm noaf = new NuovoOrdineAziendaForm();
                System.out.println("Cliccato nuovo ordine di id "+e.getActionCommand());
                ArrayList<Farmaco> farmaci = db.getOrdine(Integer.parseInt(e.getActionCommand()));
                JPanel label = noaf.getLabelPnl();
                JPanel text = noaf.getTextPnl();
                JTextField qta[] = new JTextField[farmaci.size()];
                JButton ok = noaf.getButtonOK();
                JButton annulla = noaf.getButtonCancel();

                for (int j = 0; j < farmaci.size(); j++) {
                    JPanel label1 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
                    JPanel text1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
                    JLabel nome = new JLabel(farmaci.get(j).getNome());
                    qta[j] = new JTextField();

                    qta[j].setColumns(10);
                    label1.add(nome);
                    text1.add(qta[j]);
                    label.add(label1);
                    text.add(text1);
                }

                ok.addActionListener(e1 -> {
                    ArrayList<Farmaco> farmaciOrdine = new ArrayList<>();
                    for (int j = 0; j < qta.length; j++) {
                        Farmaco f = new Farmaco(farmaci.get(j).getNome(), farmaci.get(j).getPrincipioAttivo(),farmaci.get(j).getData(), farmaci.get(j).getDaBanco(),Integer.parseInt(qta[j].getText()));
                        f.setID(farmaci.get(j).getID());
                        farmaciOrdine.add(f);
                    }
                    db.inviaOrdine(farmaciOrdine, db.getIndirizzoFromOrdine(Integer.parseInt(e.getActionCommand())));
                });

                annulla.addActionListener(e1 -> {
                    noaf.dispose();
                });
            });

            modificaOrdine.addActionListener(e -> {
                ModificaOrdineForm moaf = new ModificaOrdineForm();
                System.out.println("Cliccato modifica ordine di id "+e.getActionCommand());
                ArrayList<Farmaco> farmaci = db.getOrdine(Integer.parseInt(e.getActionCommand()));
                JPanel label = moaf.getLabelPnl();
                JPanel text = moaf.getTextPnl();
                JTextField qta[] = new JTextField[farmaci.size()];
                JButton ok = moaf.getButtonOK();
                JButton annulla = moaf.getButtonCancel();

                for (int j = 0; j < farmaci.size(); j++) {
                    JPanel label1 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
                    JPanel text1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
                    JLabel nome = new JLabel(farmaci.get(j).getNome());
                    qta[j] = new JTextField();

                    qta[j].setColumns(10);
                    label1.add(nome);
                    text1.add(qta[j]);
                    JLabel qtaprima = new JLabel(""+farmaci.get(j).getQuantità());
                    text.add(qtaprima);
                    label.add(label1);
                    text.add(text1);
                }

                ok.addActionListener(e1 -> {
                    ArrayList<Farmaco> farmaciOrdine = new ArrayList<>();
                    for (int j = 0; j < qta.length; j++) {
                        Farmaco f = new Farmaco(farmaci.get(j).getNome(), farmaci.get(j).getPrincipioAttivo(),farmaci.get(j).getData(), farmaci.get(j).getDaBanco(),Integer.parseInt(qta[j].getText()));
                        f.setID(farmaci.get(j).getID());
                        farmaciOrdine.add(f);
                    }
                    db.modificaOrdine(farmaciOrdine, Integer.parseInt(e.getActionCommand()));
                });

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
