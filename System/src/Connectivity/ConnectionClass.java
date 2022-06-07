package Connectivity;

import java.sql.Connection;
import java.sql.DriverManager;
public class ConnectionClass {
    public Connection getConnection(){
        Connection conn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost/dbazienda","root", "");
            System.out.println("Database is connected !");

        }
        catch(Exception e) {
            System.out.print("Do not connect to DB - Error:"+e);
        }

        return conn;
    }
}
