package Main;

import Autenticazione.Utente;

import javax.swing.*;
import java.awt.*;

public class SchermataPrincipale extends JFrame {

    String titolo = "Pagina Principale";
    int width = 1280;
    int heigth = 720;

    public SchermataPrincipale(){
        this.setTitle(titolo);
        this.setSize(width, heigth);
        this.setVisible(true);
        this.setResizable(false);
        this.initItems();
    }


    JLabel gestioneOrdini = new JLabel("Gestione Ordini", JLabel.CENTER);
    JButton ordinaFarmaci;
    JButton visOrdiniPeriodici;
    JButton visOrdini;

    JLabel gestioneMagazzino = new JLabel("Gestione Magazzino", JLabel.CENTER);
    JButton caricaFarmaci;
    JButton scaricaFarmaci;
    JButton visInventario;

    JLabel gestioneConsegne = new JLabel("Gestione Consegne", JLabel.CENTER);
    JButton visConsegne;

    JLabel gestioneSegnalazioni = new JLabel("Gestione Segnalazioni", JLabel.CENTER);
    JButton avviaSegnalazione;
    JButton visualizzaSegnalazioni;


    private void initItems(){
        JPanel gst_ordPanel = new JPanel(new GridLayout(4, 1, 10, 10));
        JPanel gst_magPanel = new JPanel(new GridLayout(4, 1, 10, 10));
        JPanel gst_conPanel = new JPanel(new GridLayout(4, 1, 10, 10));
        JPanel gst_segPanel = new JPanel(new GridLayout(4, 1, 10, 10));

        ordinaFarmaci = new JButton("Ordina Farmaci");
        visOrdiniPeriodici = new JButton("Visualizza Ordini Periodici");
        visOrdini = new JButton("Visualizza Ordini");
        gst_ordPanel.add(gestioneOrdini);
        gst_ordPanel.add(ordinaFarmaci);
        gst_ordPanel.add(visOrdiniPeriodici);
        gst_ordPanel.add(visOrdini);

        caricaFarmaci = new JButton("Carica farmaci");
        scaricaFarmaci = new JButton("Scarica farmaci");
        visInventario = new JButton("Visualizza Inventario");
        gst_magPanel.add(gestioneMagazzino);
        gst_magPanel.add(caricaFarmaci);
        gst_magPanel.add(scaricaFarmaci);
        gst_magPanel.add(visInventario);

        visConsegne = new JButton("Visualizza Consegne");
        gst_conPanel.add(gestioneConsegne);
        gst_conPanel.add(visConsegne);

        avviaSegnalazione = new JButton("Avvia Segnalazione");
        visualizzaSegnalazioni = new JButton("Visualizza Segnalazioni");
        gst_segPanel.add(gestioneSegnalazioni);
        gst_segPanel.add(avviaSegnalazione);
        gst_segPanel.add(visualizzaSegnalazioni);

        JPanel grid2 = new JPanel(new GridLayout(1, 4, 10, 0));
        grid2.add(gst_ordPanel);
        grid2.add(gst_magPanel);
        grid2.add(gst_conPanel);
        grid2.add(gst_segPanel);

        JPanel boxGst = new JPanel();
        boxGst.setLayout(new BoxLayout(boxGst, BoxLayout.X_AXIS));

        boxGst.add(grid2);
        //boxGst.add(gst_magPanel);
        //boxGst.add(gst_conPanel);
        //boxGst.add(gst_segPanel);

        JPanel flowPanel = new JPanel(new FlowLayout());
        flowPanel.add(boxGst);

        if (checkUser()== 1){
        } else if (checkUser()==2) {
        } else if (checkUser()==3) {
        }

        Container cont = this.getContentPane();
        cont.add(flowPanel, BorderLayout.CENTER);
    }

    private int checkUser(){
        switch (Utente.getRuolo()){
            case "Addetto Azienda":
                return 1;

            case "corriere":
                return 2;

            case "Farmacista":
                return 3;
        }
        return 0;
    }

}
