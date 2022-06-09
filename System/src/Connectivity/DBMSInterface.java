package Connectivity;

import Autenticazione.LoginControl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBMSInterface {
    static ConnectionClass connClass = new ConnectionClass();
    static Connection connAzienda = connClass.getConnectionAzienda();
    static Connection connFarmacia = connClass.getConnectionFarmacia();

    //Controllo credenziali nel DB Azienda
    public static boolean checkCredentialsAzienda(String user, String pass){
        Statement st;
        ResultSet results;
        String query="SELECT * FROM impiegatoazienda WHERE Username = '"+user+"' AND Password='"+pass+"';";
        try {
            st = connAzienda.createStatement();
            results = st.executeQuery(query);
            //Controllo se la query ha dato risultati
            if (!results.next()) {
                return false;
            } else {
                do {
                    LoginControl.setDatiUtente(results.getString("Nome_I"), results.getString("Cognome_I"), results.getString("Ruolo"), -1);
                    return true;
                } while (results.next());
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean checkCredentialsFarmacia(String user, String pass){
        Statement st;
        ResultSet results;
        String query="SELECT * FROM farmacista WHERE Username = '"+user+"' AND Password='"+pass+"';";

        try {
            st = connFarmacia.createStatement();
            results = st.executeQuery(query);
            //Controllo se la query ha dato risultati
            if (!results.next()) {
                return false;
            } else {
                do {
                    LoginControl.setDatiUtente(results.getString("Nome"), results.getString("Cognome"), "Farmacista", Integer.parseInt(results.getString("ID_FARM")) );
                    return true;
                } while (results.next());
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

}
