package GestioneOrdini;

import javax.swing.*;
import java.awt.*;


public class OrdFarmaciForm extends JFrame {
    private int width = 1280;
    private int heigth = 720;
    private JPanel lblCenter1;
    private JPanel lblCenter2;
    private JPanel lblCenter3;
    private JPanel spnCenter;
    private JPanel btnCenter;
    private JPanel pnlRight;
    private JLabel farmaciaLbl;
    private JButton logoutButton;
    private Container cont;
    private JButton nuovoOrdineBtn;
    public OrdFarmaciForm() {
        this.setTitle("Nuovo GestioneOrdini.Ordine");
        this.setSize(width, heigth);
        this.setResizable(false);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        initItems();
        this.setVisible(true);
    }
    private void initItems() {

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
        lblCenter1 = new JPanel();
        lblCenter2 = new JPanel();
        lblCenter3 = new JPanel();
        spnCenter = new JPanel();
        btnCenter = new JPanel();
        JPanel pnlCenter1 = new JPanel();
        pnlCenter1.setLayout(new BoxLayout(pnlCenter1, BoxLayout.X_AXIS));
        pnlCenter1.add(lblCenter1);
        pnlCenter1.add(lblCenter2);
        pnlCenter1.add(lblCenter3);
        pnlCenter1.add(spnCenter);
        pnlCenter1.add(btnCenter);
        JPanel pnlCenter2 = new JPanel();
        pnlCenter2.setBackground(Color.GRAY);
        pnlCenter2.add(pnlCenter1);

        //right
        pnlRight = new JPanel();
        pnlRight.setLayout(new BoxLayout(pnlRight, BoxLayout.Y_AXIS));
        JPanel pnlRight2 = new JPanel();
        pnlRight2.add(pnlRight);

        //bot
        nuovoOrdineBtn = new JButton("Nuovo ordine");
        JPanel pnlBot1 = new JPanel();
        pnlBot1.add(nuovoOrdineBtn);
        pnlBot1.setLayout(new BoxLayout(pnlBot1, BoxLayout.X_AXIS));
        JPanel pnlBot2 = new JPanel();
        pnlBot2.setPreferredSize(new Dimension(250, 70));
        pnlBot2.add(pnlBot1);

        //scroll
        ScrollPane sCenter = new ScrollPane();
        sCenter.add(pnlCenter2);
        ScrollPane sRight = new ScrollPane();
        sRight.setPreferredSize(new Dimension(350, 100));
        sRight.add(pnlRight2);
        //frame
        cont = this.getContentPane();
        cont.add(headerPnl, BorderLayout.NORTH);
        cont.add(sCenter, BorderLayout.CENTER);
        cont.add(sRight, BorderLayout.EAST);
        cont.add(pnlBot2, BorderLayout.SOUTH);
    }

    public JPanel getLblCenter1() {
        return lblCenter1;
    }
    public JPanel getLblCenter2() {
        return lblCenter2;
    }
    public JPanel getLblCenter3() {
        return lblCenter3;
    }
    public JPanel getSpnCenter() {
        return spnCenter;
    }
    public JPanel getBtnCenter() {
        return btnCenter;
    }
    public JPanel getPnlRight() {
        return pnlRight;
    }
    public Container getCont() {return cont;}
    public JButton getNuovoOrdineBtn() {return nuovoOrdineBtn;}

    public JLabel getFarmaciaLbl() {
        return farmaciaLbl;
    }

    public JButton getLogoutButton() {
        return logoutButton;
    }
}

