package GestioneOrdini;

import Autenticazione.Utente;
import Connectivity.DBMSInterface;

public class EliminaOrdineControl {
    DBMSInterface db;

    public EliminaOrdineControl(DBMSInterface db, int id){
        this.db = db;

        System.out.println("NUOVO ELIMINA ORDINE CONTROL");
        db.eliminaOrdine(id);
    }
}
