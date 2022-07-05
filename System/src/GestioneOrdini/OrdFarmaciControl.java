package GestioneOrdini;

import Autenticazione.Utente;
import Connectivity.DBMSInterface;
import GestioneMagazzino.Farmaco;
import Main.SchermataPrincipale;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.Timer;
import static java.time.temporal.ChronoUnit.DAYS;
import java.util.TimerTask;

public class OrdFarmaciControl {
    private SchermataPrincipale s;
    private Utente utente;
    private DBMSInterface db;
    private OrdFarmaciForm ordForm;
    private ArrayList<Farmaco> carrello = new ArrayList<Farmaco>();
    private ArrayList<Farmaco> preOrdineList = new ArrayList<>();
    private ArrayList<JSpinner> qtaFarm = new ArrayList<JSpinner>();
    private ArrayList<Farmaco> farmAcquistabili = new ArrayList<Farmaco>();
    private Timer timer;
    public OrdFarmaciControl(SchermataPrincipale sc, Utente u, DBMSInterface db) {
        this.s = sc;
        this.utente = u;
        this.db = db;
        ordinaFarmaci();
        timer = new Timer();
    }
    private void ordinaFarmaci(){
        JButton ordina = s.getOrdinaFarmaci();
        ActionListener ordinaListener = e -> {
            farmAcquistabili = db.getFarmaciAcquistabili();
            for(int i = 0; i < farmAcquistabili.size(); i++){
                farmAcquistabili.get(i).setQuantita(db.getQuantitaAqcuistabile(farmAcquistabili.get(i).getID()));
            }
            carrello.clear();
            qtaFarm.clear();
            ordForm = new OrdFarmaciForm();
            ordForm.getFarmaciaLbl().setText(utente.getNomeFarmacia());
            ordForm.getLogoutButton().addActionListener(e1 -> ordForm.dispose());
            formFarmAcquistabili();
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
            JSpinner spinner = new JSpinner(new SpinnerNumberModel(1, 1, 10000, 1));
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
        int sceltaPreOrdine;
        System.out.println(f.getQuantita());
        if (f.getQuantita() < qta){

            //((qta - f.getQuantita()) / (100)) * 30; secondi necessari per quantità da ordinare

            sceltaPreOrdine = JOptionPane.showConfirmDialog(ordForm, "Quantità farmaco non disponibile al momento, vuoi effettuare un pre-ordine di " + String.valueOf(qta - f.getQuantita()) + " " +  f.getNome() + " in consegna in data: ");
            if(sceltaPreOrdine == JOptionPane.YES_OPTION)
                preOrdine(f, qta);
            return false;
        }
        else if(ChronoUnit.MONTHS.between(date, LocalDate.now()) > -2) {
            conferma = JOptionPane.showConfirmDialog(ordForm, "Farmaco in scadenza, vuoi comunque aggiungere al carrello?");
            if(conferma == JOptionPane.YES_OPTION) {
                return true;
            }
            return false;
        }
        return true;
    }

    void nuovoOrdine(){
        if(this.carrello.size() > 0){
            db.inviaOrdine(this.carrello, utente.getIndirizzoFarmacia());
            JOptionPane.showMessageDialog(ordForm.getCont(), "Ordine completato con successo");
            ordForm.dispose();
            carrello.clear();
            qtaFarm.clear();
        }
        else {
            JOptionPane.showMessageDialog(ordForm.getCont(), "Carrello vuoto");
        }
    }

    void preOrdine(Farmaco f, int qta){

        Farmaco f1 = new Farmaco(f.getNome(), f.getPrincipioAttivo(), f.getData(), f.getDaBanco(), qta - f.getQuantita());
        f1.setID(f.getID());

        Farmaco f2 = new Farmaco(f.getNome(), f.getPrincipioAttivo(), f.getData(), f.getDaBanco(), f.getQuantita());
        f2.setID(f.getID());

        preOrdineList.add(f1);

        if(f2.getQuantita() > 0) {
            System.out.println("check");
            carrello.add(f2);

            JPanel pnl = new JPanel(new GridLayout(1, 2, 10, 10));

            pnl.add(new JLabel(f.getNome()));
            pnl.add(new JLabel(f.getData()));
            pnl.add(new JLabel(String.valueOf(f2.getQuantita())));

            ordForm.getPnlRight().add(pnl);
            ordForm.validate();
            ordForm.repaint();
            System.out.println(carrello);
        }

        String prova = "2022-07-07";
        db.inviaPreOrdine(this.preOrdineList, utente.getIndirizzoFarmacia(), prova);
        preOrdineList.clear();
        f.setQuantita(0);
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