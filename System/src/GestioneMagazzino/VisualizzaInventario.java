package GestioneMagazzino;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class VisualizzaInventario extends JFrame {
    private int width = 1280;
    private int heigth = 720;

    private JPanel panel1;
    private JTextField campoRicerca;
    private JRadioButton perNomeRadioButton;
    private JRadioButton perPrincipioAttivoRadioButton;
    private JButton cercaButton;
    private JTable tabella;
    private Container cont = this.getContentPane();
    private JLabel errore = new JLabel("Nessun elemento trovato");
    private JPanel headerPnl;
    private JPanel farmaciaPnl;
    private JPanel notifichePnl;
    private JPanel LogoutPanel;
    private JLabel farmaciaLbl;
    private JButton notificheButton;
    private JButton logoutButton;

    public VisualizzaInventario() {
        this.setTitle("Inventario");
        this.setSize(1280, 720);
        this.setResizable(false);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        initItems();
    }

    private void initItems() {

        headerPnl = new JPanel();
        headerPnl.setLayout(new GridLayout());

        notifichePnl = new JPanel();
        notifichePnl.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
        headerPnl.add(notifichePnl);
        farmaciaPnl = new JPanel();
        farmaciaPnl.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        headerPnl.add(farmaciaPnl);
        farmaciaLbl = new JLabel();
        farmaciaLbl.setFont(new Font("Calibri", Font.PLAIN, 23));
        farmaciaPnl.add(farmaciaLbl);
        LogoutPanel = new JPanel();
        LogoutPanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
        headerPnl.add(LogoutPanel);
        headerPnl.setBounds(0, 0, width-15, 50);
        logoutButton = new JButton();
        logoutButton.setText("Indietro");
        LogoutPanel.add(logoutButton);

        cont.add(headerPnl, BorderLayout.NORTH);

        panel1 = new JPanel();
        panel1.setLayout(new BorderLayout(0, 0));

        final JPanel panel2 = new JPanel();
        panel2.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));

        panel1.add(panel2, BorderLayout.NORTH);

        campoRicerca = new JTextField();
        campoRicerca.setColumns(10);
        panel2.add(campoRicerca);

        perNomeRadioButton = new JRadioButton("Per nome", true);
        panel2.add(perNomeRadioButton);

        perPrincipioAttivoRadioButton = new JRadioButton("Per principio attivo");
        panel2.add(perPrincipioAttivoRadioButton);

        ButtonGroup bg = new ButtonGroup();
        bg.add(perNomeRadioButton);
        bg.add(perPrincipioAttivoRadioButton);

        cercaButton = new JButton();
        cercaButton.setText("Cerca");
        panel2.add(cercaButton);


        errore.setForeground(Color.red);
        errore.setVisible(false);
        panel2.add(errore);

        tabella = new JTable();
        tabella.setShowGrid(true);
        tabella.setShowHorizontalLines(true);
        JScrollPane scrollPane = new JScrollPane(tabella);

        JPanel panel3 = new JPanel();
        panel3.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        panel3.add(scrollPane);

        panel1.add(panel3, BorderLayout.CENTER);
        cont.add(panel1);
    }

        //ciao gabri adesso inizierò a scrivere un poema cosi non potrai più fare un cazzo, io sono valeria polizzi ho 21 anni e vengo da palermo. ho i capelli marroni e gli occhi marroni, sno alta 1.55 e sono figlia di crstina e giuseppe.
        //ho una sorella di nome miriam, ha 26 anni e lavora. Io studio ingegneria informatica a+presso luniversiha di pa,ero  qhdjqwhjgqe


    public JTable getTabella() {
        return tabella;
    }

    public JTextField getCampoRicerca() {
        return campoRicerca;
    }

    public JRadioButton getPerNomeRadioButton() {
        return perNomeRadioButton;
    }

    public JRadioButton getPerPrincipioAttivoRadioButton() {
        return perPrincipioAttivoRadioButton;
    }

    public JButton getCercaButton() {
        return cercaButton;
    }

    public JLabel getErrore(){return errore;}

    public JLabel getFarmaciaLbl() {
        return farmaciaLbl;
    }
    public JButton getLogoutButton() {
        return logoutButton;
    }
}

