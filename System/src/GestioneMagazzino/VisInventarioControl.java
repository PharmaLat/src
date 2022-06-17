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
                String[] colonne = {"Nome", "Principio Attivo", "Scadenza", "Da Banco", "Quantità"};
                model.setColumnIdentifiers(colonne);
                String[] riga = new String[5];
                for (int i = 0; i < farmaci.size(); i++) {
                    riga[0] = farmaci.get(i).getNome();
                    riga[1] = farmaci.get(i).getPrincipioAttivo();
                    riga[2] = farmaci.get(i).getData();
                    riga[3] = farmaci.get(i).getDaBanco();
                    riga[4] = farmaci.get(i).getQuantità()+"";
                    model.addRow(riga);
                }
            }
        };
        visualizza.addActionListener(visLstnr);
    }

}
