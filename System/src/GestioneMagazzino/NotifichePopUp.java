package GestioneMagazzino;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class NotifichePopUp extends JDialog {

    private JPanel contentPane = new JPanel();
    private JPanel gridPnl;
    private JPanel flowPnl;

    public NotifichePopUp() {
        setContentPane(contentPane);
        setModal(true);
        pack();
        setVisible(true);
    }

    public void noNotifiche(){
        flowPnl = new JPanel(new FlowLayout());

    }

    public JPanel getGridPnl() {
        return gridPnl;
    }
}
