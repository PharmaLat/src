package GestioneOrdini;

import Autenticazione.Utente;
import Connectivity.DBMSInterface;
import GestioneMagazzino.Farmaco;
import Main.SchermataPrincipale;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class OrdFarmaciControl {
    private SchermataPrincipale s;
    private Utente utente;
    private DBMSInterface db;
    private OrdFarmaciForm ordForm;
    private ArrayList<Farmaco> carrello = new ArrayList<Farmaco>();
    private ArrayList<JSpinner> qtàFarm = new ArrayList<JSpinner>();
    private ArrayList<Farmaco> farmAcquistabili = new ArrayList<Farmaco>();

    public OrdFarmaciControl(SchermataPrincipale sc, Utente u, DBMSInterface db) {
        this.s = sc;
        this.utente = u;
        this.db = db;
        ordinaFarmaci();
    }

    private void ordinaFarmaci(){
        JButton ordina = s.getOrdinaFarmaci();
        farmAcquistabili = db.getFarmaciAcquistabili();
        ActionListener ordinaListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Cliccato ordina farmaci");
                farmAcquistabili = db.getFarmaciAcquistabili();
                carrello.clear();
                qtàFarm.clear();
                ordForm = new OrdFarmaciForm();
                formFarmAcquistabili();
            }
        };
       ordina.addActionListener(ordinaListener);
    }
    void formFarmAcquistabili(){

        ordForm.getCompCenter().setLayout(new GridLayout(farmAcquistabili.size(), 4));
        ordForm.getNuovoOrdineBtn().addActionListener(nuovoOrdineListener);

        for (int i = 0; i < farmAcquistabili.size(); i++) {
            ordForm.getCompCenter().add(new JLabel(farmAcquistabili.get(i).getNome()));
            ordForm.getCompCenter().add(new JLabel(farmAcquistabili.get(i).getPrincipioAttivo()));
            JSpinner spinner = new JSpinner(new SpinnerNumberModel(1, 1, 500, 1));
            qtàFarm.add(spinner);
            ordForm.getCompCenter().add(spinner);
            JButton addBtn = new JButton("Aggiungi al carrello " + farmAcquistabili.get(i).getNome());
            addBtn.addActionListener(addCarrelloListener);
            addBtn.setActionCommand(farmAcquistabili.get(i).getNome());
            ordForm.getCompCenter().add(addBtn);
        }

        ordForm.validate();
        ordForm.repaint();
    }

    void aggiungiAlCarrello(Farmaco f, int qta){
        if(isFarmAcquistabile(f, qta)){
            Farmaco f1 = new Farmaco(f.getNome(), f.getPrincipioAttivo(), f.getData(), f.getDaBanco(), qta);
            f1.setID(f.getID());
            carrello.add(f1);
            System.out.println(f.getQuantità());
            f.setQuantità(f.getQuantità() - qta);
            System.out.println(f.getQuantità());
            System.out.println(carrello);
            ordForm.getCompRight().setLayout(new GridLayout(carrello.size(), 2));
            ordForm.getCompRight().add(new JLabel(f.getNome()));
            ordForm.getCompRight().add(new JLabel(String.valueOf(qta)));
            ordForm.validate();
            ordForm.repaint();
        }

    }

    boolean isFarmAcquistabile(Farmaco f, int qta){
        LocalDate date = LocalDate.parse(f.getData());
        ChronoUnit.MONTHS.between(date, LocalDate.now());
        int conferma;
        if (f.getQuantità() < qta){
            JOptionPane.showMessageDialog(ordForm.getCont(), "Quantità non disponibile");
            return false;
        }
        else if(ChronoUnit.MONTHS.between(date, LocalDate.now()) > -2) {
            conferma = JOptionPane.showConfirmDialog(ordForm.getCont(), "Farmaco in scadenza, vuoi comunque aggiungere al carrello?");
                if(conferma == JOptionPane.YES_OPTION) {
                    return true;
                }
            return false;
        }
        return true;
    }

    void nuovoOrdine(){
        db.inviaOrdine(this.carrello, utente.getIndirizzoFarmacia());
        JOptionPane.showMessageDialog(ordForm.getCont(), "Ordine completato con successo");
        ordForm.dispose();
        carrello.clear();
        qtàFarm.clear();
    }

    ActionListener addCarrelloListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton btn = (JButton) e.getSource();
            for(int i = 0; i < farmAcquistabili.size(); i++){
                if (btn.getActionCommand().equals(farmAcquistabili.get(i).getNome()))
                    aggiungiAlCarrello(farmAcquistabili.get(i), (Integer) qtàFarm.get(i).getValue());
            }

        }
    };

    ActionListener nuovoOrdineListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            nuovoOrdine();
        }
    };

}
