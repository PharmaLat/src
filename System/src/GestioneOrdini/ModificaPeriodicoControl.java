package GestioneOrdini;

import Autenticazione.Utente;
import Connectivity.DBMSInterface;
import GestioneMagazzino.Farmaco;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ModificaPeriodicoControl {

    private SchermataOrdiniPeriodici scOrdini;
    private Utente utente;
    private DBMSInterface db;
    private ArrayList<Ordine> ordiniPeriodici;
    private int nOrdine;
    private ArrayList<Farmaco> farmaciMod = new ArrayList<>();

    private ArrayList<JSpinner> qtaFarm = new ArrayList<JSpinner>();
    private  JDialog modificaDialog;
    public ModificaPeriodicoControl(SchermataOrdiniPeriodici s, Utente u, DBMSInterface db, ArrayList<Ordine> ordiniPeriodici,int nOrdine){

        this.scOrdini = s;
        this.utente = u;
        this.db = db;
        this.nOrdine = nOrdine;
        this.ordiniPeriodici = ordiniPeriodici;


        System.out.println("NUOVO MODIFICA ORDINE CONTROL");
        modifica();
    }

    public void modifica(){

        modificaDialog = new JDialog( scOrdini, "Modifica ordine");
        modificaDialog.setVisible(true);
        modificaDialog.setSize(300, 200);
        modificaDialog.setLocationRelativeTo(scOrdini);

        JPanel lblCenter = new JPanel();
        JPanel spnCenter = new JPanel();

        lblCenter.add(new JLabel(ordiniPeriodici.get(nOrdine).getFarmaci().get(0).getNome()));
        JSpinner spinner = new JSpinner(new SpinnerNumberModel(ordiniPeriodici.get(nOrdine).getFarmaci().get(0).getQuantita(), 1, 999, 1));//vedere se posso mettere di default quello vecchio
        qtaFarm.add(spinner);
        spnCenter.add(spinner);


        //center
        JPanel pnlCenter1 = new JPanel();
        pnlCenter1.setLayout(new BoxLayout(pnlCenter1, BoxLayout.X_AXIS));
        pnlCenter1.add(lblCenter);
        pnlCenter1.add(spnCenter);
        JPanel pnlCenter2 = new JPanel();
        pnlCenter2.add(pnlCenter1);


        //top
        JPanel pnlTop = new JPanel();
        pnlTop.add(new JLabel("Modifica ordine " + String.valueOf(ordiniPeriodici.get(nOrdine).getID_O())) );

        //bot
        JButton btnConferma = new JButton("Conferma");
        btnConferma.addActionListener(confermaListener);
        JButton btnBotAnnulla = new JButton("Annulla");

        JPanel pnlBot = new JPanel();
        pnlBot.add(btnConferma);
        pnlBot.add(btnBotAnnulla);

        Container cont = modificaDialog.getContentPane();
        cont.add(pnlTop, BorderLayout.NORTH);
        cont.add(pnlCenter1, BorderLayout.CENTER);
        cont.add(pnlBot, BorderLayout.SOUTH);

    }
    ActionListener confermaListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {

            for(int i = 0; i < ordiniPeriodici.get(nOrdine).getFarmaci().size(); i++){
                Farmaco f = ordiniPeriodici.get(nOrdine).getFarmaci().get(i) ;
                f.setQuantita((Integer) qtaFarm.get(i).getValue());
                farmaciMod.add(f);
            }

            System.out.println(farmaciMod);

            //db.modificaOrdinePeriodico(f.getIDqualcosa, farmaciMod);
            farmaciMod.clear();
            qtaFarm.clear();
            modificaDialog.dispose();
        }
    };

}
