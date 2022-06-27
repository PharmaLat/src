package GestioneOrdini;

import javax.swing.*;
import java.awt.*;

public class SchermataOrdini extends JFrame {

    JPanel gridCenter;
    JPanel lblCenter;
    JPanel btnCenter1;
    JPanel btnCenter2;
    Container cont;

    public SchermataOrdini() {
        this.setTitle("Schermata ordini");
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
        cont = this.getContentPane();
        cont.add(pnlTop, BorderLayout.NORTH);
        cont.add(pnlCenter2, BorderLayout.CENTER);

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
}
