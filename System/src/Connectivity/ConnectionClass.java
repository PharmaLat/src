package Connectivity;

import java.sql.Connection;
import java.sql.DriverManager;
public class ConnectionClass {
    public Connection getConnectionAzienda(){
        Connection conn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost/dbazienda","root", "");
            System.out.println("Database Azienda is connected !");

        }
        catch(Exception e) {
            System.out.print("Do not connect to DB Azienda - Error:"+e);
        }

        return conn;
    }

    public Connection getConnectionFarmacia(){
        Connection conn2 = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn2 = DriverManager.getConnection("jdbc:mysql://localhost/dbfarmacia","root", "");
            System.out.println("Database Farmacia is connected !");

        }
        catch(Exception e) {
            System.out.print("Do not connect to DB Farmacia - Error:"+e);
        }

        return conn2;
    }
}
