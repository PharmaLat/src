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
    private List<Map<Farmaco,String>> ordiniList;
    private String id;
    private int index;
    private ArrayList<Farmaco> farmaciMod = new ArrayList<>();

    private ArrayList<JSpinner> qtaFarm = new ArrayList<JSpinner>();
    private  JDialog modificaDialog;
    public ModificaOrdineControl(SchermataOrdini s, Utente u, DBMSInterface db, String id, List<Map<Farmaco,String>> ordiniList, int index){
        this.scOrdini = s;
        this.utente = u;
        this.db = db;
        this.ordiniList = ordiniList;
        this.index = index;
        this.id = id;

        System.out.println("NUOVO MODIFICA ORDINE CONTROL");
        System.out.println(id);
        modifica();
    }

    public void modifica(){
        modificaDialog = new JDialog( scOrdini, "Modifica ordine");
        modificaDialog.setVisible(true);
        modificaDialog.setSize(300, 300);

        JPanel lblCenter = new JPanel(new GridLayout(ordiniList.get(index).size(), 1));
        JPanel spnCenter = new JPanel(new GridLayout(ordiniList.get(index).size(), 1));

        for(int i = 0; i < ordiniList.get(index).size(); i++){
            lblCenter.add(new JLabel("CIAO"));
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
        pnlTop.add(new JLabel("Modifica ordine " + id));

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


        //boh test

    }

    ActionListener confermaListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            int i = 0;
            for (Map.Entry<Farmaco, String> entry : ordiniList.get(index).entrySet()) {
                Farmaco f = entry.getKey();
                f.setQuantita((Integer) qtaFarm.get(i).getValue());
                farmaciMod.add(f);
                i++;
            }
            System.out.println(farmaciMod);
            
            db.modificaOrdine(Integer.parseInt(id), farmaciMod);
            farmaciMod.clear();
            qtaFarm.clear();
            modificaDialog.dispose();
        }
    };


}
