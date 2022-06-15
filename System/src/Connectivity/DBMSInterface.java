package Connectivity;

import Autenticazione.LoginControl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class DBMSInterface {
    ConnectionClass connClass = new ConnectionClass();
    Connection connAzienda = connClass.getConnectionAzienda();
    Connection connFarmacia = connClass.getConnectionFarmacia();

    //Controllo credenziali nel DB Azienda
    public ResultSet checkCredentialsAzienda(String user, String pass){
        Statement st;
        ResultSet results;
        String query="SELECT * FROM impiegatoazienda WHERE Username = '"+user+"' AND Password='"+pass+"';";
        try {
            st = connAzienda.createStatement();
            results = st.executeQuery(query);

            return results;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public ResultSet checkCredentialsFarmacia(String user, String pass){
        Statement st;
        ResultSet results;
        String query="SELECT * FROM farmacista WHERE Username = '"+user+"' AND Password='"+pass+"';";

        try {
            st = connFarmacia.createStatement();
            results = st.executeQuery(query);
            //Controllo se la query ha dato risultati
            return results;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
