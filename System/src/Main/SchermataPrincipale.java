package Main;

import Autenticazione.Utente;

import javax.swing.*;
import java.awt.*;

public class SchermataPrincipale extends JFrame {

    String titolo = "Pagina Principale";
    int width = 1280;
    int heigth = 720;
    Container cont = this.getContentPane();
    public SchermataPrincipale(){
        this.setTitle(titolo);
        this.setSize(width, heigth);
        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
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
        JPanel flowPanelN = new JPanel(new FlowLayout());
        logout.setActionCommand("logout");

        flowPanelN.add(notifiche);
        flowPanelN.add(farmacia);
        flowPanelN.add(logout);

        cont.add(flowPanelN, BorderLayout.NORTH);
    }

    private void initGstOrdini(JPanel g){
        ordinaFarmaci = new JButton("Ordina Farmaci");
        ordinaFarmaci.setActionCommand("ordina");
        visOrdiniPeriodici = new JButton("Visualizza Ordini Periodici");
        visOrdini = new JButton("Visualizza Ordini");
        g.add(gestioneOrdini);
        g.add(ordinaFarmaci);
        g.add(visOrdiniPeriodici);
        g.add(visOrdini);
    }

    private void initGstMagazzino(JPanel g){
        caricaFarmaci = new JButton("Carica farmaci");
        scaricaFarmaci = new JButton("Scarica farmaci");
        visInventario = new JButton("Visualizza Inventario");
        g.add(gestioneMagazzino);
        g.add(caricaFarmaci);
        g.add(scaricaFarmaci);
        g.add(visInventario);
    }

    private void initGstConsegne(JPanel g){
        visConsegne = new JButton("Visualizza Consegne");
        g.add(gestioneConsegne);
        g.add(visConsegne);
    }

    private void initGstSegnalazioni(JPanel g){
        avviaSegnalazione = new JButton("Avvia Segnalazione");
        visualizzaSegnalazioni = new JButton("Visualizza Segnalazioni");
        g.add(gestioneSegnalazioni);
        g.add(avviaSegnalazione);
        g.add(visualizzaSegnalazioni);
    }

    public void removeFarmPanel(){cont.remove(farmPanel);}
    public void removeAddettoPanel(){cont.remove(addettoPanel);}
    public void removeCorrierePanel(){cont.remove(corrierePanel);}
    public JButton getLogout(){return logout;}

    public Container getContainerPane(){
        return cont;
    }

}
