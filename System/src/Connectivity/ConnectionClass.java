package Connectivity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionClass {
    public Connection getConnectionAzienda() throws Exception{
        Connection conn = null;
        Class.forName("com.mysql.cj.jdbc.Driver");
        conn = DriverManager.getConnection("jdbc:mysql://localhost/dbazienda","root", "");
        System.out.println("Database Azienda is connected !");
        return conn;
    }

    public Connection getConnectionFarmacia() throws Exception {
        Connection conn2 = null;
        Class.forName("com.mysql.cj.jdbc.Driver");
        conn2 = DriverManager.getConnection("jdbc:mysql://localhost/dbfarmacia","root", "");
        System.out.println("Database Farmacia is connected !");
        return conn2;
    }
}
