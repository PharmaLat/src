package GestioneOrdini;

import javax.swing.*;
import java.awt.*;


public class OrdFarmaciForm extends JFrame {
    private JPanel lblCenter1;
    private JPanel lblCenter2;
    private JPanel lblCenter3;
    private JPanel spnCenter;

    private JPanel btnCenter;
    private JPanel pnlRight;

    private Container cont;
    private JButton nuovoOrdineBtn;
    public OrdFarmaciForm() {
        this.setTitle("Nuovo GestioneOrdini.Ordine");
        this.setSize(1280, 720);
        this.setResizable(false);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.initItems();
        this.setVisible(true);
    }

    private void initItems() {

        //top
        JPanel pnlTop = new JPanel();
        JLabel nomeFarmacia = new JLabel("Nome farmacia");
        pnlTop.setBackground(Color.darkGray);
        pnlTop.setPreferredSize(new Dimension(300, 50));
        pnlTop.add(nomeFarmacia);

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
        pnlRight2.setPreferredSize(new Dimension(350, 100));
        pnlRight2.add(pnlRight);

        //bot
        nuovoOrdineBtn = new JButton("Nuovo ordine");
        JPanel pnlBot1 = new JPanel();
        pnlBot1.add(nuovoOrdineBtn);
        pnlBot1.setLayout(new BoxLayout(pnlBot1, BoxLayout.X_AXIS));
        JPanel pnlBot2 = new JPanel();
        pnlBot2.setPreferredSize(new Dimension(250, 70));
        pnlBot2.add(pnlBot1);

        //pop up

        //frame
        cont = this.getContentPane();
        cont.add(pnlTop, BorderLayout.NORTH);
        cont.add(pnlCenter2, BorderLayout.CENTER);
        cont.add(pnlRight2, BorderLayout.EAST);
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
}

