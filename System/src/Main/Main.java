package Main;
import java.sql.*;

import Autenticazione.LoginForm;
import Connectivity.ConnectionClass;
public class Main {
    public static void main(String[] args) {
        ConnectionClass connClass = new ConnectionClass();
        Connection conn = connClass.getConnection();

        String sql = "SELECT * FROM farmaco";

        try{
            Statement statement = conn.createStatement();
            ResultSet results = statement.executeQuery(sql);
            while (results.next()){
                System.out.println(results.getString(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        LoginForm l = new LoginForm();

    }
}

