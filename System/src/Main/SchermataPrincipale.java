package Main;

import Autenticazione.Utente;

import javax.swing.*;
import java.awt.*;

public class SchermataPrincipale extends JFrame {

    String titolo = "Pagina Principale";
    int width = 1280;
    int heigth = 720;
    Container cont = this.getContentPane();
    private JPanel headerPnl;
    private JPanel mainPnl;
    private JPanel farmaciaPnl;
    private JPanel notifichePnl;
    private JPanel LogoutPanel;
    private JLabel farmaciaLbl;
    private JButton notificheButton;
    private JButton logoutButton;
    public SchermataPrincipale(){
        this.setTitle(titolo);
        this.setSize(width, heigth);
        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.initItems();

    }

    JLabel gestioneOrdini = new JLabel("Gestione Ordini", JLabel.CENTER);
    JButton ordinaFarmaci = new JButton("Ordina Farmaci");
    JButton visOrdiniPeriodici = new JButton("Visualizza Ordini Periodici");
    JButton visOrdini = new JButton("Visualizza Ordini");

    JLabel gestioneMagazzino = new JLabel("Gestione Magazzino", JLabel.CENTER);
    JButton caricaFarmaci = new JButton("Carica farmaci");
    JButton scaricaFarmaci = new JButton("Scarica farmaci");

    JButton visInventario = new JButton("Visualizza Inventario");


    JLabel gestioneConsegne = new JLabel("Gestione Consegne", JLabel.CENTER);
    JButton visConsegne = new JButton("Visualizza Consegne");

    JLabel gestioneSegnalazioni = new JLabel("Gestione Segnalazioni", JLabel.CENTER);
    JButton avviaSegnalazione = new JButton("Avvia Segnalazione");
    JButton visualizzaSegnalazioni = new JButton("Visualizza Segnalazioni");

    JButton logout = new JButton("Logout");
    JButton notifiche = new JButton("Notifiche");
    JLabel farmacia = new JLabel("Farmacia");

    JPanel farmPanel;
    JPanel addettoPanel;
    JPanel corrierePanel;

    public void initFarmacista(){
        JPanel gst_ordPanel = new JPanel(new GridLayout(4, 1, 10, 10));
        JPanel gst_magPanel = new JPanel(new GridLayout(4, 1, 10, 10));
        JPanel gst_segPanel = new JPanel(new GridLayout(4, 1, 10, 10));

        initGstOrdini(gst_ordPanel);
        initGstMagazzino(gst_magPanel);
        initGstSegnalazioni(gst_segPanel);

        JPanel grid2 = new JPanel(new GridLayout(1, 3, 10, 0));
        grid2.add(gst_ordPanel);
        grid2.add(gst_magPanel);
        grid2.add(gst_segPanel);
        visualizzaSegnalazioni.setVisible(false);

        JPanel boxGst = new JPanel();
        boxGst.setLayout(new BoxLayout(boxGst, BoxLayout.X_AXIS));
        boxGst.add(grid2);
        farmPanel = new JPanel(new FlowLayout());
        farmPanel.add(boxGst);

        cont.add(farmPanel, BorderLayout.CENTER);

    }

    public void initAddetto(){
        JPanel gst_segPanel = new JPanel(new GridLayout(4, 1, 10, 10));
        initGstSegnalazioni(gst_segPanel);

        JPanel boxGst = new JPanel();
        boxGst.setLayout(new BoxLayout(boxGst, BoxLayout.X_AXIS));
        boxGst.add(gst_segPanel);
        avviaSegnalazione.setVisible(false);
        addettoPanel = new JPanel(new FlowLayout());
        addettoPanel.add(boxGst);
        cont.add(addettoPanel, BorderLayout.CENTER);
    }

    public void initCorriere(){
        JPanel gst_conPanel = new JPanel(new GridLayout(4, 1, 10, 10));
        initGstConsegne(gst_conPanel);

        JPanel boxGst = new JPanel();
        boxGst.setLayout(new BoxLayout(boxGst, BoxLayout.X_AXIS));
        boxGst.add(gst_conPanel);
        corrierePanel = new JPanel(new FlowLayout());
        corrierePanel.add(boxGst);
        cont.add(corrierePanel, BorderLayout.CENTER);
    }

     public void initItems(){
         headerPnl = new JPanel();
         headerPnl.setLayout(new GridLayout());

         notifichePnl = new JPanel();
         notifichePnl.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
         headerPnl.add(notifichePnl);
         notificheButton = new JButton();
         notificheButton.setText("Notifiche");
         notifichePnl.add(notificheButton);
         farmaciaPnl = new JPanel();
         farmaciaPnl.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
         headerPnl.add(farmaciaPnl);
         farmaciaLbl = new JLabel();
         farmaciaLbl.setText("Farmacia");
         farmaciaLbl.setFont(new Font("Calibri", Font.PLAIN, 23));
         farmaciaPnl.add(farmaciaLbl);
         LogoutPanel = new JPanel();
         LogoutPanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
         headerPnl.add(LogoutPanel);
         headerPnl.setBounds(0, 0, width-15, 50);
         logoutButton = new JButton();
         logoutButton.setText("Logout");
         LogoutPanel.add(logoutButton);

        cont.add(headerPnl, BorderLayout.NORTH);
    }

    private void initGstOrdini(JPanel g){

        ordinaFarmaci.setActionCommand("ordina");


        g.add(gestioneOrdini);
        g.add(ordinaFarmaci);
        g.add(visOrdiniPeriodici);
        g.add(visOrdini);
    }

    private void initGstMagazzino(JPanel g){

        g.add(gestioneMagazzino);
        g.add(caricaFarmaci);
        g.add(scaricaFarmaci);
        g.add(visInventario);
    }

    private void initGstConsegne(JPanel g){

        g.add(gestioneConsegne);
        g.add(visConsegne);
    }

    private void initGstSegnalazioni(JPanel g){


        g.add(gestioneSegnalazioni);
        g.add(avviaSegnalazione);
        g.add(visualizzaSegnalazioni);
    }

    public void removeFarmPanel(){cont.remove(farmPanel);}
    public void removeAddettoPanel(){cont.remove(addettoPanel);}
    public void removeCorrierePanel(){cont.remove(corrierePanel);}
    public JButton getLogout(){return logoutButton;}

    public Container getContainerPane(){
        return cont;
    }

    public JButton getOrdinaFarmaci() {
        return ordinaFarmaci;
    }

    public JButton getVisOrdiniPeriodici() {
        return visOrdiniPeriodici;
    }

    public JButton getVisOrdini() {
        return visOrdini;
    }

    public JButton getCaricaFarmaci() {
        return caricaFarmaci;
    }

    public JButton getScaricaFarmaci() {
        return scaricaFarmaci;
    }

    public JButton getVisInventario() {
        return visInventario;
    }

    public JButton getAvviaSegnalazione() {
        return avviaSegnalazione;
    }

    public JButton getVisualizzaSegnalazioni() {
        return visualizzaSegnalazioni;
    }

    public JButton getNotifiche() {
        return notificheButton;
    }

    public JButton getVisConsegne() {
        return visConsegne;
    }

    public JLabel getFarmaciaLbl() {
        return farmaciaLbl;
    }
}
