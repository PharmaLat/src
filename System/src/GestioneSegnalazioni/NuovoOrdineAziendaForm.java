package GestioneSegnalazioni;

import javax.swing.*;
import java.awt.*;

public class NuovoOrdineAziendaForm extends JDialog {

    private Container cont = this.getContentPane();
    private JPanel contentPane = new JPanel();
    private JButton buttonOK;
    private JButton buttonCancel;
    private JPanel labelPnl;
    private JPanel textPnl;
    private JPanel mainPnl;
    private JPanel bottomPnl;
    private JPanel okCancelPnl;

    public NuovoOrdineAziendaForm(){
        setContentPane(cont);
        setVisible(true);
        pack();
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
        bottomPnl.add(buttonOK);
        bottomPnl.add(buttonCancel);

        cont.add(contentPane);
    }

    public JPanel getLabelPnl() {
        return labelPnl;
    }

    public JPanel getTextPnl() {
        return textPnl;
    }
}
