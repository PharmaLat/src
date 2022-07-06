package GestioneConsegne;

import Autenticazione.Utente;
import Connectivity.DBMSInterface;
import GestioneOrdini.Ordine;
import Main.SchermataPrincipale;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class GestioneConsegneControl {

    private Utente u;
    private SchermataPrincipale s;
    private VisualizzaConsegne vc;
    private DBMSInterface db;
    private ConfermaConsegna cc;

    public GestioneConsegneControl(Utente u, SchermataPrincipale s, DBMSInterface db) {
        this.u = u;
        this.s = s;
        this.db = db;
        visualizzaConsegne();
    }

    private void visualizzaConsegne(){
        JButton visualizza = s.getVisConsegne();
        ActionListener visConsegneLstnr = e -> {
            System.out.println("Cliccato visualizza consegne");
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDateTime now = LocalDateTime.now();
            System.out.println("Controllo consegne : oggi "+dtf.format(now));
            ArrayList<Ordine> consegne = db.getConsegne(dtf.format(now));
            vc = new VisualizzaConsegne();
            vc.getFarmaciaLbl().setText("Pharmalat");
            vc.getLogoutButton().addActionListener(e1 -> vc.dispose());
            if (consegne != null){
                JPanel consegnePnl = vc.getConsegnePnl();
                consegnePnl.setLayout(new GridLayout(consegne.size(), 1, 20, 10));
                JPanel flow1 = new JPanel();
                flow1.setLayout(new BoxLayout(flow1, BoxLayout.Y_AXIS));

                for (int i = 0; i < consegne.size(); i++) {
                    JPanel grid = new JPanel(new GridLayout(1, 3, 20, 10));
                    int idOrdine = consegne.get(i).getID_O();
                    JLabel ordine = new JLabel("Ordine N. "+ idOrdine);
                    JLabel indirizzo = new JLabel(consegne.get(i).getIndirizzo());
                    JButton confermaConsegna = new JButton("Conferma Consegna");

                    grid.add(ordine);
                    confermaConsegna.setActionCommand(idOrdine+"");
                    grid.add(indirizzo);
                    grid.add(confermaConsegna);

                    confermaConsegna.addActionListener(confermaLstnr);

                    flow1.add(grid);
                }
                consegnePnl.add(flow1);
            }else {
                JOptionPane.showMessageDialog(vc, "Non ci sono consegne oggi", "Errore", JOptionPane.ERROR_MESSAGE);
                vc.dispose();
            }
        };
        visualizza.addActionListener(visConsegneLstnr);
    }

    ActionListener confermaLstnr = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            cc = new ConfermaConsegna();
            cc.setTitle("Conferma consegna Ordine N. "+e.getActionCommand());
            JButton conferma = cc.getConferma();
            conferma.addActionListener(e1 -> {
                System.out.println("Cliccato invia dentro conferma consegna");
                JTextField firma = cc.getFirmaText();
                db.firmaConsegna(firma.getText(), e.getActionCommand());
            });
        }
    };

}
