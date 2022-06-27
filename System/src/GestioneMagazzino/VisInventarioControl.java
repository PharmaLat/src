package GestioneMagazzino;

import Autenticazione.Utente;
import Connectivity.DBMSInterface;
import Main.SchermataPrincipale;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class VisInventarioControl {

    private SchermataPrincipale s;
    private Utente u;
    private DBMSInterface db;
    private VisualizzaInventario inventario;

    public VisInventarioControl(SchermataPrincipale s, Utente u, DBMSInterface db){
        this.s = s;
        this.u = u;
        this.db = db;
        visualizzaInventario();
    }

    private void visualizzaInventario(){
        JButton visualizza = s.getVisInventario();

        ActionListener visLstnr = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Visualizza inventario");
                inventario = new VisualizzaInventario();
                ArrayList<Farmaco> farmaci = new ArrayList<>();
                farmaci = db.getInventario(u.getID_Farmacia());
                //System.out.println("dim: "+farmaci.size());
                DefaultTableModel model = (DefaultTableModel) inventario.getTabella().getModel();
                String[] colonne = {"Nome", "Principio Attivo", "Scadenza", "Da Banco", "Quantita"};
                model.setColumnIdentifiers(colonne);
                String[] riga = new String[5];
                for (int i = 0; i < farmaci.size(); i++) {
                    riga[0] = farmaci.get(i).getNome();
                    riga[1] = farmaci.get(i).getPrincipioAttivo();
                    riga[2] = farmaci.get(i).getData();
                    riga[3] = farmaci.get(i).getDaBanco();
                    riga[4] = farmaci.get(i).getQuantita()+"";
                    model.addRow(riga);
                }
                ricerca();
            }
        };
        visualizza.addActionListener(visLstnr);
    }
    public void ricerca(){
        JButton cerca = inventario.getCercaButton();
        JRadioButton nome = inventario.getPerNomeRadioButton();
        JRadioButton prAtt = inventario.getPerPrincipioAttivoRadioButton();
        JTextField parola = inventario.getCampoRicerca();
        JLabel errore = inventario.getErrore();
        ActionListener cercaLstnr = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                errore.setVisible(false);
                ArrayList<Farmaco> farmaci;
                System.out.println("Cliccato cerca");
                if (!cerca.getText().equals("")){
                    if (nome.isSelected()){
                        farmaci = db.getRicerca(u.getID_Farmacia(), 0, parola.getText());
                    } else{
                        farmaci = db.getRicerca(u.getID_Farmacia(), 1, parola.getText());
                    }
                    if (farmaci != null){
                        DefaultTableModel model = (DefaultTableModel) inventario.getTabella().getModel();
                        model.setRowCount(0);
                        String[] colonne = {"Nome", "Principio Attivo", "Scadenza", "Da Banco", "Quantita"};
                        model.setColumnIdentifiers(colonne);
                        String[] riga = new String[5];
                        for (int i = 0; i < farmaci.size(); i++) {
                            riga[0] = farmaci.get(i).getNome();
                            riga[1] = farmaci.get(i).getPrincipioAttivo();
                            riga[2] = farmaci.get(i).getData();
                            riga[3] = farmaci.get(i).getDaBanco();
                            riga[4] = farmaci.get(i).getQuantita()+"";
                            model.addRow(riga);
                        }
                    }else errore.setVisible(true);

                }
            }
        };
        cerca.addActionListener(cercaLstnr);
    }

}
