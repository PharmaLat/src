package GestioneSegnalazioni;

import javax.swing.*;
import java.awt.*;

public class ModificaOrdineForm extends JFrame{
    private Container cont = this.getContentPane();
    private JPanel contentPane = new JPanel();
    private JButton buttonOK;
    private JButton buttonCancel;
    private JPanel labelPnl;
    private JPanel textPnl;
    private JPanel mainPnl;
    private JPanel bottomPnl;
    private JPanel okCancelPnl;

    public ModificaOrdineForm(){
        setContentPane(cont);
        setVisible(true);
        setSize(500, 400);
        initItems();
    }

    public void initItems(){
        contentPane.setLayout(new GridLayout(2, 1));
        mainPnl = new JPanel();
        bottomPnl = new JPanel();
        contentPane.add(mainPnl);
        contentPane.add(bottomPnl);

        mainPnl.setLayout(new GridLayout(1, 2));
        labelPnl = new JPanel();
        textPnl = new JPanel();
        mainPnl.add(labelPnl);
        mainPnl.add(textPnl);

        labelPnl.setLayout(new BoxLayout(labelPnl, BoxLayout.Y_AXIS));
        textPnl.setLayout(new BoxLayout(textPnl, BoxLayout.Y_AXIS));

        bottomPnl.setLayout(new FlowLayout(FlowLayout.RIGHT));

        okCancelPnl = new JPanel();
        okCancelPnl.setLayout(new GridLayout(1, 2));
        buttonCancel = new JButton("Annulla");
        buttonOK = new JButton("Conferma");
        okCancelPnl.add(buttonCancel);
        okCancelPnl.add(buttonOK);
        JLabel errore = new JLabel();
        bottomPnl.add(errore);
        bottomPnl.add(buttonOK);
        bottomPnl.add(buttonCancel);

        cont.add(contentPane);
    }

    public JButton getButtonOK() {
        return buttonOK;
    }

    public JButton getButtonCancel() {
        return buttonCancel;
    }

    public JPanel getLabelPnl() {
        return labelPnl;
    }

    public JPanel getTextPnl() {
        return textPnl;
    }
}
