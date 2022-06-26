package Connectivity;

import GestioneMagazzino.Farmaco;
import GestioneOrdini.Ordine;
import GestioneSegnalazioni.Segnalazione;

import javax.print.DocFlavor;
import javax.swing.plaf.nimbus.State;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
                    f.setID(res.getInt("ID_F"));
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



    public List<Map<Farmaco,String>> getOrdini(String indirizzo){

        List<Map<Farmaco,String>> listaOrdini  = new ArrayList<>();

        Statement st;
        ResultSet res;
        String query = "SELECT * FROM farmaco, comprende, ordine WHERE ordine.Indirizzo = '"+indirizzo+"' AND ordine.ID_O = comprende.ID_O AND comprende.ID_F = farmaco.ID_F;";
        System.out.println(query);
        try {
            st = connAzienda.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            res = st.executeQuery(query);
            if (!res.next()) {
                return null;
            }else {
                Map<Farmaco,String> ordine = new HashMap<>();
                int id_o = res.getInt("ID_O");
                //System.out.println("Primo id_o: "+id_o);
                int rows = 0;
                if (res.last()) {
                    rows = res.getRow();
                    res.beforeFirst();
                }
                //System.out.println("Numero di righe: "+rows);
                res.next();
                do {
                    if (id_o == res.getInt("ID_O")){
                        //System.out.println("Riga "+res.getRow());
                        //System.out.println("IF uguali: "+id_o+"="+res.getInt("ID_O"));
                        Farmaco f = new Farmaco(res.getString("Nome_F"), res.getString("Principio_Attivo"), res.getString("Scadenza"), res.getInt("Da_Banco") == 1 ? "Si":"No", res.getInt("Quantità"));
                        f.setID(res.getInt("ID_F"));
                        Integer qta_O = res.getInt("Quantità_O");
                        String info = qta_O+"-"+res.getInt("ID_O");
                        ordine.put(f, info);
                        //listaOrdini.add(ordine);
                        //System.out.println("Farmaco :"+f);
                    }else {
                        //System.out.println("Riga "+res.getRow());
                        //System.out.println("IF diversi: "+id_o+"!="+res.getInt("ID_O"));
                        listaOrdini.add(ordine);
                        id_o = res.getInt("ID_O");
                        //System.out.println("id_o ora è "+id_o);
                        ordine = new HashMap<>();
                        Farmaco f2 = new Farmaco(res.getString("Nome_F"), res.getString("Principio_Attivo"), res.getString("Scadenza"), res.getInt("Da_Banco") == 1 ? "Si":"No", res.getInt("Quantità"));
                        f2.setID(res.getInt("ID_F"));
                        Integer qta_O = res.getInt("Quantità_O");
                        String info = qta_O+"-"+res.getInt("ID_O");
                        ordine.put(f2, info);
                        //System.out.println("Farmaco2 :"+f2);
                        if (res.getRow() == rows){
                            listaOrdini.add(ordine);
                        }
                       // listaOrdini.add(ordine);
                    }
                }while(res.next());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listaOrdini;
    }

    public String getIndirizzo(int ID_F){
        Statement st;
        ResultSet res;
        String query = "SELECT * FROM farmacia WHERE ID_FARM = "+ID_F;
        String indirizzo = "";
        try {
            st = connFarmacia.createStatement();
            res = st.executeQuery(query);
            if (!res.next()) {
                return null;
            }else {
                do {
                    indirizzo = res.getString("Indirizzo");
                }while(res.next());
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return indirizzo;
    }

    public void inviaSegnalazione(int idOrdine, String descrizione) {
        Statement st;
        ResultSet res;
        String query = "INSERT INTO segnalazione(Descrizione, Stato_S) VALUES('"+descrizione+"', 'Aperta')";
        String query2 = "SELECT LAST_INSERT_ID()";
        try {
            st = connAzienda.createStatement();
            st.executeUpdate(query);
            System.out.println("query1: "+query);
            System.out.println("query2: "+query2);
            res = st.executeQuery(query2);
            res.next();
            int idsegnalazione = res.getInt("LAST_INSERT_ID()");
            System.out.println("last id: "+idsegnalazione);
            String query3 = "UPDATE ordine SET ID_S = "+idsegnalazione+" WHERE ID_O="+idOrdine;
            st.executeUpdate(query3);
            System.out.println(query3);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Segnalazione> getSegnalazioni(){
        ArrayList<Segnalazione> segnalazioni = new ArrayList<>();
        Statement st;
        ResultSet res;
        String query = "Select * FROM ordine, segnalazione WHERE segnalazione.Stato_S != 'Chiusa' AND ordine.ID_S = segnalazione.ID_S";
        try {
            st = connAzienda.createStatement();
            res = st.executeQuery(query);
            if (!res.next()) {
                return null;
            }else {
                do {
                    Segnalazione s = new Segnalazione(res.getInt("ID_S"), res.getString("Descrizione"), res.getString("Stato_S"), res.getInt("ID_O"));
                    segnalazioni.add(s);
                }while(res.next());
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return segnalazioni;

    }

    public ArrayList<Farmaco> getOrdine(int idOrdine){
        ArrayList<Farmaco> farmaci = new ArrayList<>();
        Statement st;
        ResultSet res;
        String query = "SELECT * FROM farmaco, comprende, ordine WHERE ordine.ID_O = "+idOrdine+" AND ordine.ID_O = comprende.ID_O AND comprende.ID_F = farmaco.ID_F;";

        try {
            st = connAzienda.createStatement();
            res = st.executeQuery(query);
            if (!res.next()) {
                return null;
            }else {
                do {
                    Farmaco f = new Farmaco(res.getString("Nome_F"), res.getString("Principio_Attivo"), res.getString("Scadenza"), res.getInt("Da_Banco") == 1 ? "Si":"No", res.getInt("Quantità_O"));
                    f.setID(res.getInt("ID_F"));
                    //System.out.println("Sto aggiungendo "+f);
                    farmaci.add(f);
                }while(res.next());
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return farmaci;
    }

    public ArrayList<Ordine> getConsegne(String data){
        ArrayList<Ordine> consegne = new ArrayList<>();
        Statement st;
        ResultSet res;
        String query = "SELECT * FROM ordine WHERE DataDiConsegna = '"+data+"' AND Stato_O <> 'In Lavorazione';";

        try {
            st = connAzienda.createStatement();
            res = st.executeQuery(query);
            if (!res.next()) {
                return null;
            }else {
                do {
                    Ordine o = new Ordine(res.getInt("ID_O"), res.getDate("DataDiConsegna"), res.getString("Stato_O"), res.getString("Indirizzo"));
                    consegne.add(o);
                }while(res.next());
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return consegne;
    }

    public void firmaConsegna(String firma, String idOrdine){
        Statement st;
        String query="UPDATE ordine SET Stato_O = 'Consegnato', Firma='"+firma+"' WHERE ID_O = "+idOrdine;
        System.out.println(query);
        try {
            st = connAzienda.createStatement();
            st.executeUpdate(query);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void inviaOrdine(ArrayList<Farmaco> farmaci, String indirizzo){
        Statement st;
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime consegna = now.plusWeeks(2);
        //System.out.println(dtf.format(consegna));
        String queryOrdine = "INSERT INTO ordine(DataDiConsegna, Indirizzo, Stato_O) VALUES ('"+dtf.format(consegna)+"', '"+indirizzo+"', 'In Lavorazione')";
        System.out.println(queryOrdine);
        //st.executeQuery(queryOrdine);
        ResultSet res;
        try {
            st = connAzienda.createStatement();
            st.executeUpdate(queryOrdine);
            String query2 = "SELECT LAST_INSERT_ID()";
            res = st.executeQuery(query2);
            res.next();

            int id_ordine = res.getInt("LAST_INSERT_ID()");
            for (int i = 0; i < farmaci.size(); i++) {
                String queryComprende = "INSERT INTO comprende (ID_O, ID_F, Quantità_O) VALUES ("+id_ordine+", "+farmaci.get(i).getID()+", "+farmaci.get(i).getQuantità()+")";
                System.out.println(queryComprende);
                st.executeUpdate(queryComprende);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public String getIndirizzoFromOrdine(int ID_O){
        Statement st;
        String query = "SELECT Indirizzo FROM ordine WHERE ID_O="+ID_O;
        String indirizzo="";
        ResultSet res;
        try {
            st = connAzienda.createStatement();
            res = st.executeQuery(query);
            if (!res.next()) {
                return null;
            }else {
                do {
                    indirizzo = res.getString("Indirizzo");
                }while(res.next());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return indirizzo;
    }

    public void modificaOrdine(ArrayList<Farmaco> farmaci, int idOrdine){
        Statement st;
        String query = "";
        try {
            st = connAzienda.createStatement();
            for (int i = 0; i < farmaci.size(); i++) {
                query = "UPDATE comprende SET Quantità_O = "+farmaci.get(i).getQuantità()+" WHERE ID_O="+idOrdine;
                System.out.println(query);
                st.executeUpdate(query);
            }
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    //da sistemare id_segnalazione non è is_segnalazione
    public void chiudiSegnalazione(int id_segnalazione, int id_addetto){
        Statement st;
        String query = "UPDATE segnalazione SET Stato_S = 'Chiusa' WHERE ID_S = "+id_segnalazione;
        System.out.println(query);
        try {
            st = connAzienda.createStatement();
            st.executeUpdate(query);
            int id_ordine = getIdOrdineFromIdSegnalazione(id_segnalazione);
            String query1 = "UPDATE ordine SET ID_A = "+id_addetto+" WHERE ID_O = "+id_ordine;
            st.executeUpdate(query1);
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int getIdOrdineFromIdSegnalazione(int idSegnalazione){
        int idOrdine = 0;
        Statement st;
        ResultSet res;
        String query = "SELECT * FROM ordine, segnalazione WHERE ordine.ID_S = segnalazione.ID_S";
        try {
            st = connAzienda.createStatement();
            res = st.executeQuery(query);
            if (!res.next()) {
                return 0;
            }else {
                do {
                    idOrdine = res.getInt("ID_O");
                }while(res.next());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return idOrdine;
    }

}
