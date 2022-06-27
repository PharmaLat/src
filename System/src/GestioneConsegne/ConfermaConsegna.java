package GestioneConsegne;

import javax.swing.*;
import java.awt.*;

public class ConfermaConsegna extends JFrame {

    private int width = 500;
    private int height = 300;
    private Container cont = this.getContentPane();
    private JPanel framePnl;
    private JPanel headerPnl;
    private JPanel mainPnl;
    private JPanel farmaciaPnl;
    private JPanel notifichePnl;
    private JPanel LogoutPanel;
    private JLabel farmaciaLbl;
    private JButton notificheButton;
    private JButton logoutButton;
    private JLabel firmaLbl;
    private JTextField firmaText;
    private JButton conferma;

    public ConfermaConsegna() {
        this.setSize(width, height);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        initItems();
    }

    private void initItems() {
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
        headerPnl.setBounds(0, 0, width - 15, 50);
        logoutButton = new JButton();
        logoutButton.setText("Indietro");
        LogoutPanel.add(logoutButton);

        /*mainPnl = new JPanel();
        mainPnl.setLayout(new BoxLayout(mainPnl, BoxLayout.LINE_AXIS));
        mainPnl.setEnabled(true);
        mainPnl.setBounds(0, 100, width, 620);*/

        firmaLbl = new JLabel("Firma del farmacista:");
        firmaLbl.setBounds(50, 50, 150, 25);
        firmaText = new JTextField();
        firmaText.setColumns(200);
        firmaText.setBounds(205, 50, 200, 25);
        conferma = new JButton("Invia");
        conferma.setBounds(400, 200, 75, 25 );

        framePnl.add(firmaLbl);
        framePnl.add(firmaText);
        framePnl.add(conferma);
        framePnl.add(headerPnl);
        /*framePnl.add(mainPnl);*/
        cont.add(framePnl);
    }

    public JButton getConferma() {
        return conferma;
    }

    public JTextField getFirmaText() {
        return firmaText;
    }
}