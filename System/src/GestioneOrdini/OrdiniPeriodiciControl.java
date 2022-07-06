package GestioneOrdini;

import Autenticazione.Utente;
import Connectivity.DBMSInterface;
import Main.SchermataPrincipale;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


public class OrdiniPeriodiciControl {
    private final SchermataPrincipale s;
    private final Utente utente;
    private final DBMSInterface db;
    private SchermataOrdiniPeriodici schermataOrdiniPeriodici;
    private ArrayList<Ordine> ordiniPeriodici = new ArrayList<>();

    public OrdiniPeriodiciControl(SchermataPrincipale sc, Utente u, DBMSInterface db){
        this.s = sc;
        this.utente = u;
        this.db = db;
        visualizzaOrdini();
    }
    public void visualizzaOrdini(){
        JButton visualizza = s.getVisOrdiniPeriodici();
        ActionListener visualizzaPeriodiciListener = e -> {
            ordiniPeriodici = db.getOrdiniPeriodici(utente.getIndirizzoFarmacia());
            schermataOrdiniPeriodici  = new SchermataOrdiniPeriodici();
            schermataOrdiniPeriodici.getFarmaciaLbl().setText(utente.getNomeFarmacia());
            schermataOrdiniPeriodici.getLogoutButton().addActionListener(e1 -> schermataOrdiniPeriodici.dispose());
            if (ordiniPeriodici != null){
                formVisualizzaOrdini();
            }else {
                JOptionPane.showMessageDialog(schermataOrdiniPeriodici, "Non ci sono ordini periodici", "Nessun ordine", JOptionPane.INFORMATION_MESSAGE);
                schermataOrdiniPeriodici.dispose();
            }
        };
        visualizza.addActionListener(visualizzaPeriodiciListener);
    }

    public void formVisualizzaOrdini(){

        schermataOrdiniPeriodici.getLblCenter1().setLayout(new GridLayout(this.ordiniPeriodici.size(), 1, 20, 5 ));
        schermataOrdiniPeriodici.getLblCenter2().setLayout(new GridLayout(this.ordiniPeriodici.size(), 1,20, 5 ));
        schermataOrdiniPeriodici.getLblCenter3().setLayout(new GridLayout(this.ordiniPeriodici.size(), 1, 20, 5 ));
        schermataOrdiniPeriodici.getLblCenter4().setLayout(new GridLayout(this.ordiniPeriodici.size(), 1, 20, 5 ));
        schermataOrdiniPeriodici.getBtnCenter1().setLayout(new GridLayout(this.ordiniPeriodici.size(), 1, 20, 5 ));

        for (Ordine ordine : this.ordiniPeriodici) {

            JButton btnM = new JButton("Modifica");
            btnM.addActionListener(modificaListener);
            btnM.setActionCommand(String.valueOf(ordine.getID_O()));

            JLabel labelNome = new JLabel(ordine.getFarmaci().get(0).getNome());
            labelNome.setPreferredSize(new Dimension(150, 50));
            JLabel labelQuantita = new JLabel(String.valueOf(ordine.getFarmaci().get(0).getQuantita()));
            labelQuantita.setPreferredSize(new Dimension(50, 50));
            JLabel labelPA = new JLabel(ordine.getFarmaci().get(0).getPrincipioAttivo());
            labelPA.setPreferredSize(new Dimension(150, 50));

            schermataOrdiniPeriodici.getLblCenter1().add(labelNome);
            schermataOrdiniPeriodici.getLblCenter2().add(labelQuantita);
            schermataOrdiniPeriodici.getLblCenter3().add(labelPA);
            schermataOrdiniPeriodici.getLblCenter4().add(new JLabel(ordine.getDataUltimoOrdine()));
            schermataOrdiniPeriodici.getBtnCenter1().add(btnM);

        }
    }

    ActionListener modificaListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {

            JButton btn = (JButton) e.getSource();

            for(int i = 0; i < ordiniPeriodici.size(); i++){
                if(btn.getActionCommand().equals(String.valueOf(ordiniPeriodici.get(i).getID_O()))){
                    new ModificaPeriodicoControl(schermataOrdiniPeriodici, utente, db,  ordiniPeriodici, i);
                    break;
                }
            }
        }
    };

}
