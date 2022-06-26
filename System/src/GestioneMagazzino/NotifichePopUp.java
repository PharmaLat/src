package GestioneMagazzino;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class NotifichePopUp extends JDialog {

    private JPanel contentPane = new JPanel();
    JPanel grid;
    private JButton buttonOK;
    private JButton buttonCancel;

    public NotifichePopUp() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK = new JButton("OK");
        contentPane.add(buttonOK);

        /*setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });*/

        setVisible(true);
    }


    /*public JPanel getGridPnl() {
        return gridPnl;
    }*/
}
