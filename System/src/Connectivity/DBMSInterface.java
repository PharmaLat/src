package Connectivity;

import GestioneMagazzino.Farmaco;

import javax.xml.stream.events.StartDocument;
import java.sql.*;
import java.util.ArrayList;

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

    public ArrayList<Farmaco> getInventario(int ID_FARM){
        Statement st;
        ResultSet res;
        String query = "SELECT * FROM farmaco WHERE ID_FARM = "+ID_FARM+";";
        ArrayList<Farmaco> farmaci = new ArrayList<>();
        try {
            st=connFarmacia.createStatement();
            res = st.executeQuery(query);
            if (!res.next()) {
                return null;
            }else {

                do {
                    Farmaco f = new Farmaco(res.getString("Nome_F"), res.getString("Principio_Attivo"), res.getString("Scadenza"), res.getInt("Da_Banco") == 1 ? "Si":"No", res.getInt("Quantità"));
                    f.setID(res.getInt("ID_F"));
                    farmaci.add(f);
                }while(res.next());

            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return farmaci;
    }

    public ArrayList<Farmaco> getRicerca(int ID_FARM, int filtro, String parola){
        ArrayList<Farmaco> farmaci = new ArrayList<>();
        Statement st;
        ResultSet res;
        String prAtt = "Principio_Attivo LIKE '%"+parola+"%'";
        String nome = "Nome_F LIKE '%"+parola+"%'";
        String query = "SELECT * FROM farmaco WHERE ID_FARM = "+ID_FARM+ " AND "+ (filtro==0 ? nome:prAtt);
        System.out.println(query);
        try {
            st=connFarmacia.createStatement();
            res = st.executeQuery(query);
            if (!res.next()) {
                return null;
            }else {
                do {
                    Farmaco f = new Farmaco(res.getString("Nome_F"), res.getString("Principio_Attivo"), res.getString("Scadenza"), res.getInt("Da_Banco") == 1 ? "Si":"No", res.getInt("Quantità"));
                    farmaci.add(f);
                }while(res.next());
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return farmaci;
    }

    public ArrayList<Farmaco> getFarmaciAcquistabili(){
        ArrayList<Farmaco> farmaci = new ArrayList<>();
        Statement st;
        ResultSet res;
        String query = "Select * FROM farmaco";
        try {
            st = connAzienda.createStatement();
            res = st.executeQuery(query);
            if (!res.next()) {
                return null;
            }else {
                do {
                    Farmaco f = new Farmaco(res.getString("Nome_F"), res.getString("Principio_Attivo"), res.getString("Scadenza"), res.getInt("Da_Banco") == 1 ? "Si":"No", res.getInt("Quantità"));
                    farmaci.add(f);
                }while(res.next());
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return farmaci;
    }

    public void inserisciFarmacoFarmacia(Farmaco f, int id_farm){
        Statement st;
        ResultSet res;
        String query = "INSERT INTO farmaco (Nome_F, Principio_Attivo, Scadenza, Da_Banco, Quantità, ID_FARM) VALUES ('"+f.getNome()+"', '"+f.getPrincipioAttivo()+"', '"+f.getData()+"', "+(f.getDaBanco().equals("Si")?1:0)+", "+f.getQuantità()+", "+ id_farm +");";
        System.out.println(query);
        try {
            st = connFarmacia.createStatement();
            st.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Farmaco> getfarmaciMagazzino() {
        ArrayList<Farmaco> farmaci = new ArrayList<>();
        Statement st;
        ResultSet res;
        String query = "Select * FROM farmaco";
        try {
            st = connFarmacia.createStatement();
            res = st.executeQuery(query);
            if (!res.next()) {
                return null;
            }else {
                do {
                    Farmaco f = new Farmaco(res.getString("Nome_F"), res.getString("Principio_Attivo"), res.getString("Scadenza"), res.getInt("Da_Banco") == 1 ? "Si":"No", res.getInt("Quantità"));
                    f.setID(res.getInt("ID_F"));
                    farmaci.add(f);
                }while(res.next());
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return farmaci;
    }

    public void scaricaFarmaci(int qtaint, Farmaco farmaco) {
        Statement st;
        int newQta = farmaco.getQuantità()-qtaint;
        String query;
        if (newQta == 0){
            query= "DELETE FROM farmaco WHERE ID_F = "+farmaco.getID()+";";
        }else query = "UPDATE farmaco SET Quantità = "+newQta+" WHERE ID_F = "+farmaco.getID()+";";
        System.out.println(query);

        try {
            st = connFarmacia.createStatement();
            st.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public ArrayList<>

}
