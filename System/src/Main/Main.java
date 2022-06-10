package Main;
import java.sql.*;

import Autenticazione.LoginControl;
import Autenticazione.LoginForm;
import Autenticazione.Utente;
import Connectivity.ConnectionClass;
public class Main {
    public static void main(String[] args) {
        ConnectionClass connClass = new ConnectionClass();
        Connection connAzienda = connClass.getConnectionAzienda();
        Connection connFarmacia = connClass.getConnectionFarmacia();
        LoginControl l = new LoginControl();
        while(true){
            if (l.isLogged()){
                System.out.println("sono loggato");
                break;
            }
        }
        System.out.println("fuori while");

    }
}

