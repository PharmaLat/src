package GestioneSegnalazioni;

import Autenticazione.Utente;
import Connectivity.DBMSInterface;
import GestioneMagazzino.Farmaco;
import GestioneOrdini.OrdFarmaciControl;
import Main.SchermataPrincipale;
import org.w3c.dom.events.Event;

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


    private void gestisciSegnalazioni(){
        ArrayList<Segnalazione> segnalazioni = db.getSegnalazioni();

        JPanel segnalazioniPanel =vs.getSegnalazioniPnl();
        segnalazioniPanel.setLayout(new GridLayout(segnalazioni.size(), 5, 20, 10));
        JPanel boxPnl = new JPanel();
        boxPnl.setLayout(new BoxLayout(boxPnl, BoxLayout.Y_AXIS));

        JLabel ordine;
        JButton nuovoOrdine[] = new JButton[segnalazioni.size()];
        JButton modificaOrdine[] = new JButton[segnalazioni.size()];
        JButton chiudiSegnalazione[] = new JButton[segnalazioni.size()];

        boolean cliccatoModifica[] = new boolean[segnalazioni.size()];
        boolean cliccatoNuovoOrdine[] = new boolean[segnalazioni.size()];

        for (int i = 0; i < segnalazioni.size(); i++) {
            JPanel grid = new JPanel(new GridLayout(1, 4, 20, 10));
            int idOrdine = segnalazioni.get(i).getID_O();
            ordine = new JLabel("Ordine N. "+ idOrdine);
            nuovoOrdine[i] = new JButton("Nuovo Ordine " +i);
            modificaOrdine[i] = new JButton("Modifica Ordine " +i);
            chiudiSegnalazione[i] = new JButton("Chiudi Segnalazione " +i);

            nuovoOrdine[i].setActionCommand(idOrdine+"");
            modificaOrdine[i].setActionCommand(idOrdine+"");
            chiudiSegnalazione[i].setActionCommand(idOrdine+"");

            nuovoOrdine[i].addActionListener(e -> {
                int id = Integer.parseInt(e.getActionCommand());
                modificaOrdine(e);
                nuovoOrdine(e);
                int k=0;
                for (int j = 0; j < segnalazioni.size(); j++) {
                    if (id == Integer.parseInt(nuovoOrdine[j].getActionCommand())){
                        modificaOrdine[j].setEnabled(false);
                        System.out.println("j: "+j);
                        k=j;
                    }
                }

                nuovoOrdine[k].setEnabled(false);
                cliccatoNuovoOrdine[k] = true;
                cliccatoModifica[k] = true;
            });

            modificaOrdine[i].addActionListener(e -> {
                int id = Integer.parseInt(e.getActionCommand());
                modificaOrdine(e);
                int k=0;
                for (int j = 0; j < segnalazioni.size(); j++) {
                    if (id == Integer.parseInt(nuovoOrdine[j].getActionCommand())){
                        modificaOrdine[j].setEnabled(false);
                        System.out.println("j: "+j);
                        k=j;
                    }
                }
                cliccatoModifica[k] = true;
            });
            chiudiSegnalazione[i].addActionListener(e -> {
                int id = Integer.parseInt(e.getActionCommand());
                for (int j = 0; j < segnalazioni.size(); j++) {
                    if (id == Integer.parseInt(nuovoOrdine[j].getActionCommand())){
                        if (!cliccatoModifica[j] && !cliccatoNuovoOrdine[j]){
                            JOptionPane.showMessageDialog(vs, "Devi prima creare un nuovo ordine o modificare il vecchio");
                        }else{
                            chiudisegnalazione(e);
                            modificaOrdine[j].setEnabled(false);
                            nuovoOrdine[j].setEnabled(false);
                            chiudiSegnalazione[j].setEnabled(false);
                        }
                    }
                }

            });

            grid.add(ordine);
            grid.add(new JLabel(segnalazioni.get(i).getDescrizione()));
            grid.add(nuovoOrdine[i]);
            grid.add(modificaOrdine[i]);
            grid.add(chiudiSegnalazione[i]);
            boxPnl.add(grid);
        }
        segnalazioniPanel.add(boxPnl);
    }

    private void  nuovoOrdine(ActionEvent e){
        NuovoOrdineAziendaForm noaf = new NuovoOrdineAziendaForm();
        noaf.setTitle("Nuovo ordine per id: "+e.getActionCommand());
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
            JOptionPane.showMessageDialog(noaf, "Ordine creato con successo");
            noaf.dispose();
        });

        annulla.addActionListener(e1 -> {
            noaf.dispose();
        });
    }
    private void modificaOrdine(ActionEvent e){
        ModificaOrdineForm moaf = new ModificaOrdineForm();
        moaf.setTitle("Modifica ordine per id: "+e.getActionCommand());
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
                Farmaco f = new Farmaco(farmaci.get(j).getNome(), farmaci.get(j).getPrincipioAttivo(),farmaci.get(j).getData(), farmaci.get(j).getDaBanco(),qta[j].getText().equals("0")?0:Integer.parseInt(qta[j].getText()));
                f.setID(farmaci.get(j).getID());
                farmaciOrdine.add(f);
            }
            db.modificaOrdine(farmaciOrdine, Integer.parseInt(e.getActionCommand()));
            JOptionPane.showMessageDialog(moaf, "Ordine creato con successo");
            moaf.dispose();
        });

        annulla.addActionListener(e1 -> {
            moaf.dispose();
        });
    }

    private void chiudisegnalazione(ActionEvent e){
        db.chiudiSegnalazione(Integer.parseInt(e.getActionCommand()), u.getID());
        JOptionPane.showMessageDialog(vs, "Segnalazione Chiusa");
    }

}
