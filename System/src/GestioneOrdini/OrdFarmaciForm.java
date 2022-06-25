package GestioneOrdini;

import javax.swing.*;
import java.awt.*;


public class OrdFarmaciForm extends JFrame {
    private JPanel compCenter;
    private JPanel compRight;
    private Container cont;
    private JButton nuovoOrdineBtn;
    public OrdFarmaciForm() {
        this.setTitle("Nuovo GestioneOrdini.Ordine");
        this.setSize(1280, 720);
        this.setResizable(false);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        initItems();
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
        compCenter = new JPanel();
        JPanel pnlCenter1 = new JPanel();
        pnlCenter1.setLayout(new BoxLayout(pnlCenter1, BoxLayout.X_AXIS));
        pnlCenter1.add(compCenter);
        JPanel pnlCenter2 = new JPanel();
        pnlCenter2.setBackground(Color.GRAY);
        pnlCenter2.add(pnlCenter1);

        //right
        compRight = new JPanel();
        JPanel pnlRight1 = new JPanel();
        pnlRight1.setLayout(new BoxLayout(pnlRight1, BoxLayout.Y_AXIS));
        pnlRight1.add(compRight);
        JPanel pnlRight2 = new JPanel();
        pnlRight2.setPreferredSize(new Dimension(250, 100));
        pnlRight2.add(pnlRight1);

        //bot
        nuovoOrdineBtn = new JButton("Nuovo ordine");
        JPanel pnlBot1 = new JPanel();
        pnlBot1.add(nuovoOrdineBtn);
        pnlBot1.setLayout(new BoxLayout(pnlBot1, BoxLayout.X_AXIS));
        JPanel pnlBot2 = new JPanel();
        pnlBot2.setPreferredSize(new Dimension(250, 100));
        pnlBot2.add(pnlBot1);

        //pop up

        //frame
        cont = this.getContentPane();
        cont.add(pnlTop, BorderLayout.NORTH);
        cont.add(pnlCenter2, BorderLayout.CENTER);
        cont.add(pnlRight2, BorderLayout.EAST);
        cont.add(pnlBot2, BorderLayout.SOUTH);
    }

    public JPanel getCompCenter() { return compCenter; }

    public JPanel getCompRight() { return compRight; }

    public Container getCont() {return cont;}

    public JButton getNuovoOrdineBtn() {return nuovoOrdineBtn;}
}

