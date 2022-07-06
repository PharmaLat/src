package GestioneOrdini;

import javax.swing.*;
import java.awt.*;

public class SchermataOrdiniPeriodici extends JFrame {

    private final int width = 1280;
    private final int height = 720;
    private JPanel lblCenter1;
    private JPanel lblCenter2;
    private JPanel lblCenter3;
    private JPanel lblCenter4;
    private JPanel btnCenter1;
    private JLabel farmaciaLbl;
    private JButton logoutButton;
    private Container cont;

    public SchermataOrdiniPeriodici() {
        this.setTitle("Schermata ordini periodici");
        this.setSize(width, height);
        this.setResizable(false);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.initItems();
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
        lblCenter1 = new JPanel();
        lblCenter2 = new JPanel();
        lblCenter3 = new JPanel();
        lblCenter4 = new JPanel();
        btnCenter1 = new JPanel();

        JPanel pnlCenter1 = new JPanel();
        pnlCenter1.setLayout(new BoxLayout(pnlCenter1, BoxLayout.X_AXIS));
        pnlCenter1.add(lblCenter1);
        pnlCenter1.add(lblCenter2);
        pnlCenter1.add(lblCenter3);
        pnlCenter1.add(lblCenter4);
        pnlCenter1.add(btnCenter1);

        JPanel pnlCenter2 = new JPanel();
        pnlCenter2.setBackground(Color.GRAY);
        pnlCenter2.add(pnlCenter1);

        ScrollPane scroll = new ScrollPane();
        scroll.add(pnlCenter2);

        //frame
        cont = this.getContentPane();
        cont.add(headerPnl, BorderLayout.NORTH);
        cont.add(scroll, BorderLayout.CENTER);

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
    public JPanel getLblCenter4() {
        return lblCenter4;
    }
    public JPanel getBtnCenter1() {
        return btnCenter1;
    }
    public Container getCont() {
        return cont;
    }

    public JLabel getFarmaciaLbl() {
        return farmaciaLbl;
    }

    public JButton getLogoutButton() {
        return logoutButton;
    }
}
