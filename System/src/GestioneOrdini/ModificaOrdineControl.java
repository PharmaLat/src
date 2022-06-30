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

public class ModificaOrdineControl {

    private SchermataOrdini scOrdini;
    private Utente utente;
    private DBMSInterface db;
    private ArrayList<Ordine> ordiniList;
    private int nOrdine;
    private ArrayList<Farmaco> farmaciMod = new ArrayList<>();

    private ArrayList<JSpinner> qtaFarm = new ArrayList<JSpinner>();
    private  JDialog modificaDialog;
    public ModificaOrdineControl(SchermataOrdini s, Utente u, DBMSInterface db, ArrayList<Ordine> ordiniList, int nOrdine){

        this.scOrdini = s;
        this.utente = u;
        this.db = db;
        this.ordiniList = ordiniList;
        this.nOrdine = nOrdine;


        System.out.println("NUOVO MODIFICA ORDINE CONTROL");
        modifica();
    }

    public void modifica(){

        modificaDialog = new JDialog( scOrdini, "Modifica ordine");
        modificaDialog.setVisible(true);
        modificaDialog.setSize(300, 400);
        modificaDialog.setLocationRelativeTo(scOrdini);

        JPanel lblCenter = new JPanel(new GridLayout(ordiniList.get(nOrdine).getFarmaci().size(), 1));
        JPanel spnCenter = new JPanel(new GridLayout(ordiniList.get(nOrdine).getFarmaci().size(), 1));

        for(int i = 0; i < ordiniList.get(nOrdine).getFarmaci().size(); i++){

            lblCenter.add(new JLabel(ordiniList.get(nOrdine).getFarmaci().get(i).getNome()));

            JSpinner spinner = new JSpinner(new SpinnerNumberModel(1, 1, 999, 1));//vedere se posso mettere di default quello vecchio
            qtaFarm.add(spinner);
            spnCenter.add(spinner);

        }

        //center
        JPanel pnlCenter1 = new JPanel();
        pnlCenter1.setLayout(new BoxLayout(pnlCenter1, BoxLayout.X_AXIS));
        pnlCenter1.add(lblCenter);
        pnlCenter1.add(spnCenter);
        JPanel pnlCenter2 = new JPanel();
        pnlCenter2.add(pnlCenter1);

        //top
        JPanel pnlTop = new JPanel();
        pnlTop.add(new JLabel("Modifica ordine " + String.valueOf(ordiniList.get(nOrdine).getID_O())) );

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

            for(int i = 0; i < ordiniList.get(nOrdine).getFarmaci().size(); i++){
                Farmaco f = ordiniList.get(nOrdine).getFarmaci().get(i) ;
                f.setQuantita((Integer) qtaFarm.get(i).getValue());
                farmaciMod.add(f);
            }

            System.out.println(farmaciMod);
            
            db.modificaOrdine(nOrdine, farmaciMod);
            farmaciMod.clear();
            qtaFarm.clear();
            modificaDialog.dispose();
        }
    };

}
