package GestioneOrdini;

import Autenticazione.Utente;
import Connectivity.DBMSInterface;

public class EliminaOrdineControl {
    DBMSInterface db;

    public EliminaOrdineControl(DBMSInterface db, String id){
        this.db = db;

        System.out.println("NUOVO ELIMINA ORDINE CONTROL");
        System.out.println(id);
        db.eliminaOrdine(Integer.parseInt(id));
    }
}
