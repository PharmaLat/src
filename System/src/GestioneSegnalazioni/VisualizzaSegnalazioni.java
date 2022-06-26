package GestioneSegnalazioni;

import javax.swing.*;
import java.awt.*;

public class VisualizzaSegnalazioni extends JFrame {

    private int width = 1280;
    private int heigth = 720;
    Container cont = this.getContentPane();
    private JPanel framePnl;
    private JPanel headerPnl;
    private JPanel mainPnl;
    private JPanel farmaciaPnl;
    private JPanel notifichePnl;
    private JPanel LogoutPanel;
    private JLabel farmaciaLbl;
    private JButton notificheButton;
    private JButton logoutButton;
    private JLabel segnalazioniLbl;
    private JPanel segnalazioniPnl;
    public VisualizzaSegnalazioni(){
        this.setTitle("Visualizza Segnalazioni");
        this.setResizable(false);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setSize(width, heigth);
        this.setVisible(true);
        initItems();
    }

    private void initItems(){
        framePnl = new JPanel();
        framePnl.setLayout(null);

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
        farmaciaPnl.add(farmaciaLbl);
        LogoutPanel = new JPanel();
        LogoutPanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
        headerPnl.add(LogoutPanel);
        headerPnl.setBounds(0, 0, width-15, 50);
        logoutButton = new JButton();
        logoutButton.setText("Indietro");
        LogoutPanel.add(logoutButton);

        mainPnl = new JPanel();
        mainPnl.setLayout(new BoxLayout(mainPnl, BoxLayout.LINE_AXIS));
        mainPnl.setEnabled(true);
        mainPnl.setBounds(0,100,width, 620);

        /*segnalazioniLbl = new JLabel("Segnalazioni ricevute:");
        JPanel flowPnl1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        flowPnl1.add(segnalazioniLbl);*/

        segnalazioniPnl = new JPanel();
        JPanel flowPnl2 = new JPanel(new BorderLayout());
        flowPnl2.add(segnalazioniPnl, BorderLayout.CENTER);
        flowPnl2.add(new JLabel("       "), BorderLayout.EAST);
        flowPnl2.add(new JLabel("       "), BorderLayout.WEST);
        /*mainPnl.add(flowPnl1);*/
        mainPnl.add(flowPnl2);
        framePnl.add(headerPnl, BorderLayout.NORTH);
        framePnl.add(mainPnl, BorderLayout.CENTER);
        cont.add(framePnl);
    }

    public JPanel getSegnalazioniPnl() {
        return segnalazioniPnl;
    }
}
