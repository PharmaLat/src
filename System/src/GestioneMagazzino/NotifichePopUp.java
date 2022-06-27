package GestioneMagazzino;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class NotifichePopUp extends JDialog {

    private JPanel contentPane = new JPanel();
    private JPanel gridPnl;
    private JPanel flowPnl;

    public NotifichePopUp(ArrayList<Notifica> notifiche) {
        setContentPane(contentPane);
        if (notifiche.isEmpty()){
            System.out.println("Notifiche vuote");
            noNotifiche();
        }else{
            notifiche(notifiche);
        }
        this.setLocationRelativeTo(null);
        setModal(true);
        pack();
        setVisible(true);
    }

    public void svuota(){
        contentPane.removeAll();
        contentPane.revalidate();
    }
    public void noNotifiche(){
        flowPnl = new JPanel(new FlowLayout());
        JLabel vuoto = new JLabel("Non ci sono notifiche");
        flowPnl.add(vuoto);
        contentPane.add(flowPnl);
    }

    public void notifiche(ArrayList<Notifica> notifiche){
        JPanel gridPnl = new JPanel(new GridLayout(notifiche.size(), 1));
        for (int i = 0; i < notifiche.size(); i++) {
            JLabel notif = new JLabel(notifiche.get(i).getTesto());
            gridPnl.add(notif);
        }
        contentPane.add(gridPnl);
    }

    public JPanel getGridPnl() {
        return gridPnl;
    }
}
