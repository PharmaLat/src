package GestioneOrdini;

import javax.swing.*;
import java.awt.*;

public class SchermataOrdini extends JFrame {
    private int width = 1280;
    private int height = 720;
    JPanel gridCenter;
    JPanel lblCenter;
    JPanel btnCenter1;
    JPanel btnCenter2;
    Container cont;
    JLabel farmaciaLbl;
    private JButton logoutButton;
    public SchermataOrdini() {
        this.setTitle("Schermata ordini");
        this.setSize(width, height);
        this.setResizable(false);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        initItems();
        this.setVisible(true);
    }

    public void initItems(){

        //top
        JPanel headerPnl = new JPanel();
        headerPnl.setLayout(new GridLayout());

        JPanel notifichePnl = new JPanel();
        notifichePnl.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
        headerPnl.add(notifichePnl);
        JPanel farmaciaPnl = new JPanel();
        farmaciaPnl.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        headerPnl.add(farmaciaPnl);
        farmaciaLbl = new JLabel();
        farmaciaLbl.setFont(new Font("Calibri", Font.PLAIN, 23));
        farmaciaPnl.add(farmaciaLbl);
        JPanel LogoutPanel = new JPanel();
        LogoutPanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
        headerPnl.add(LogoutPanel);
        headerPnl.setBounds(0, 0, width-15, 50);
        logoutButton = new JButton();
        logoutButton.setText("Indietro");
        LogoutPanel.add(logoutButton);

        //center

        gridCenter = new JPanel();

        lblCenter = new JPanel();

        btnCenter1 = new JPanel();

        btnCenter2 = new JPanel();

        JPanel pnlCenter1 = new JPanel();
        pnlCenter1.setLayout(new BoxLayout(pnlCenter1, BoxLayout.X_AXIS));

        pnlCenter1.add(gridCenter);
        pnlCenter1.add(lblCenter);
        pnlCenter1.add(btnCenter1);
        pnlCenter1.add(btnCenter2);
        JPanel pnlCenter2 = new JPanel();
        pnlCenter2.setBackground(Color.GRAY);
        pnlCenter2.add(pnlCenter1);

        //frame
        ScrollPane scroll = new ScrollPane();
        scroll.add(pnlCenter2);

        cont = this.getContentPane();
        cont.add(headerPnl, BorderLayout.NORTH);
        cont.add(scroll, BorderLayout.CENTER);

    }

    public JPanel getGridCenter() {
        return gridCenter;
    }


    public JPanel getLblCenter() {
        return lblCenter;
    }

    public JPanel getBtnCenter1() {
        return btnCenter1;
    }

    public JPanel getBtnCenter2() {
        return btnCenter2;
    }

    public Container getCont() { return cont; }

    public JLabel getFarmaciaLbl() {
        return farmaciaLbl;
    }

    public JButton getLogoutButton() {
        return logoutButton;
    }
}
