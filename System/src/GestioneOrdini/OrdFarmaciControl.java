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
    private ArrayList<JSpinner> qtaFarm = new ArrayList<JSpinner>();
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
                farmAcquistabili = db.getFarmaciAcquistabili();
                carrello.clear();
                qtaFarm.clear();
                ordForm = new OrdFarmaciForm();
                formFarmAcquistabili();
            }
        };
       ordina.addActionListener(ordinaListener);
    }
    void formFarmAcquistabili(){

        ordForm.getLblCenter1().setLayout(new GridLayout(farmAcquistabili.size(), 1));
        ordForm.getLblCenter2().setLayout(new GridLayout(farmAcquistabili.size(), 1));
        ordForm.getLblCenter3().setLayout(new GridLayout(farmAcquistabili.size(), 1));
        ordForm.getSpnCenter().setLayout(new GridLayout(farmAcquistabili.size(), 1));
        ordForm.getBtnCenter().setLayout(new GridLayout(farmAcquistabili.size(), 1));
        ordForm.getNuovoOrdineBtn().addActionListener(nuovoOrdineListener);

        for (int i = 0; i < farmAcquistabili.size(); i++) {
            ordForm.getLblCenter1().add(new JLabel(farmAcquistabili.get(i).getNome()));
            ordForm.getLblCenter2().add(new JLabel(farmAcquistabili.get(i).getPrincipioAttivo()));
            ordForm.getLblCenter3().add(new JLabel(farmAcquistabili.get(i).getData()));
            JSpinner spinner = new JSpinner(new SpinnerNumberModel(1, 1, 999, 1));
            qtaFarm.add(spinner);
            ordForm.getSpnCenter().add(spinner);
            JButton addBtn = new JButton("Aggiungi al carrello " + farmAcquistabili.get(i).getNome());
            addBtn.addActionListener(addCarrelloListener);
            addBtn.setActionCommand(String.valueOf(farmAcquistabili.get(i).getID()));
            ordForm.getBtnCenter().add(addBtn);
        }
        ordForm.validate();
        ordForm.repaint();
    }

    void aggiungiAlCarrello(Farmaco f, int qta){
        if(isFarmAcquistabile(f, qta)){
            Farmaco f1 = new Farmaco(f.getNome(), f.getPrincipioAttivo(), f.getData(), f.getDaBanco(), qta);
            f1.setID(f.getID());
            carrello.add(f1);
            f.setQuantita(f.getQuantita() - qta);
            JPanel pnl = new JPanel(new GridLayout(1, 2, 10, 10));
            pnl.add(new JLabel(f.getNome()));
            pnl.add(new JLabel(f.getData()));
            pnl.add(new JLabel(String.valueOf(qta)));
            ordForm.getPnlRight().add(pnl);

            ordForm.validate();
            ordForm.repaint();
        }

    }

    boolean isFarmAcquistabile(Farmaco f, int qta){
        LocalDate date = LocalDate.parse(f.getData());
        ChronoUnit.MONTHS.between(date, LocalDate.now());
        int conferma;
        if (f.getQuantita() < qta){
            JOptionPane.showMessageDialog(ordForm.getCont(), "Quantita non disponibile");
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
        qtaFarm.clear();
    }

    ActionListener addCarrelloListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton btn = (JButton) e.getSource();
            for(int i = 0; i < farmAcquistabili.size(); i++){
                if (btn.getActionCommand().equals(String.valueOf(farmAcquistabili.get(i).getID())))
                    aggiungiAlCarrello(farmAcquistabili.get(i), (Integer) qtaFarm.get(i).getValue());
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
