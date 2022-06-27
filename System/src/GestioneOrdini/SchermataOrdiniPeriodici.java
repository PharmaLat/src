package GestioneOrdini;

import javax.swing.*;
import java.awt.*;

public class SchermataOrdiniPeriodici extends JFrame {

    JPanel lblCenter1;
    JPanel lblCenter2;
    JPanel lblCenter3;
    JPanel lblCenter4;
    JPanel btnCenter1;

    Container cont;

    public SchermataOrdiniPeriodici() {
        this.setTitle("Schermata ordini periodici");
        this.setSize(1280, 720);
        this.setResizable(false);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.initItems();
        this.setVisible(true);
    }

    public void initItems(){

        //top
        JPanel pnlTop = new JPanel();
        JLabel nomeFarmacia = new JLabel("Nome Farmacia");
        pnlTop.setBackground(Color.darkGray);
        pnlTop.setPreferredSize(new Dimension(300, 50));
        pnlTop.add(nomeFarmacia);

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

        //frame
        cont = this.getContentPane();
        cont.add(pnlTop, BorderLayout.NORTH);
        cont.add(pnlCenter2, BorderLayout.CENTER);

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

}
