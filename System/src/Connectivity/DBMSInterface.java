package Connectivity;

import Autenticazione.LoginForm;
import GestioneMagazzino.Farmaco;
import GestioneOrdini.Ordine;
import GestioneSegnalazioni.Segnalazione;
import Main.SchermataPrincipale;
import javax.swing.*;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class DBMSInterface {
    ConnectionClass connClass = new ConnectionClass();
    Connection connAzienda;
    Connection connFarmacia;
    public DBMSInterface(LoginForm login, SchermataPrincipale s){
        connetti(login);
    }
    private void connetti(LoginForm login){
        try {
            connAzienda = connClass.getConnectionAzienda();
            connFarmacia = connClass.getConnectionFarmacia();
        }catch (Exception e){
            JOptionPane.showMessageDialog(login, "Problema con la connessione al DB, Ritenta", "Errore", JOptionPane.ERROR_MESSAGE);
            connetti(login);
        }
    }
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
                    Farmaco f = new Farmaco(res.getInt("ID_F"), res.getString("Nome_F"), res.getString("Principio_Attivo"), res.getString("Scadenza"), res.getInt("Da_Banco") == 1 ? "Si":"No", res.getInt("Quantita"));
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
                    Farmaco f = new Farmaco(res.getInt("ID_F"), res.getString("Nome_F"), res.getString("Principio_Attivo"), res.getString("Scadenza"), res.getInt("Da_Banco") == 1 ? "Si":"No", res.getInt("Quantita"));
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
                    Farmaco f = new Farmaco(res.getInt("ID_F"), res.getString("Nome_F"), res.getString("Principio_Attivo"), res.getString("Scadenza"), res.getInt("Da_Banco") == 1 ? "Si":"No", res.getInt("Quantita"));
                    farmaci.add(f);
                }while(res.next());
            }

        } catch (Exception e) {
            System.out.println("GETFARMACIAQCUISTABILI");
        }
        return farmaci;
    }

    public void inserisciFarmacoFarmacia(Farmaco f, int id_farm){
        Statement st;
        String query = "INSERT INTO farmaco (Nome_F, Principio_Attivo, Scadenza, Da_Banco, Quantita, ID_FARM) VALUES ('"+f.getNome()+"', '"+f.getPrincipioAttivo()+"', '"+f.getData()+"', "+(f.getDaBanco().equals("Si")?1:0)+", "+f.getQuantita()+", "+ id_farm +");";
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
                    Farmaco f = new Farmaco(res.getInt("ID_F"), res.getString("Nome_F"), res.getString("Principio_Attivo"), res.getString("Scadenza"), res.getInt("Da_Banco") == 1 ? "Si":"No", res.getInt("Quantita"));
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
        int newQta = farmaco.getQuantita()-qtaint;
        String query;
        if (newQta == 0){
            query= "DELETE FROM farmaco WHERE ID_F = "+farmaco.getID()+";";
        }else query = "UPDATE farmaco SET Quantita = "+newQta+" WHERE ID_F = "+farmaco.getID()+";";
        System.out.println(query);

        try {
            st = connFarmacia.createStatement();
            st.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public ArrayList<Ordine> getOrdini(String indirizzo){
        ArrayList<Ordine> ordini = new ArrayList<>();
        Statement st;
        ResultSet res;
        try {
            st = connAzienda.createStatement();
            String query1 = "SELECT DISTINCT(ordine.ID_O), DataDiConsegna, Stato_O FROM ordine, comprende, farmaco WHERE Indirizzo = '"+indirizzo+"' AND ordine.ID_O = comprende.ID_O AND comprende.ID_F = farmaco.ID_F";
            res = st.executeQuery(query1);
            if (!res.next()) {
                return null;
            }else {
                Ordine o;
                do {
                    int idOrdine = res.getInt("ID_O");
                    System.out.println("ID ORDINE "+ idOrdine);
                    String data = res.getString("DataDiConsegna");
                    o = new Ordine(idOrdine, data, res.getString("Stato_O"), indirizzo);
                    ordini.add(o);
                }while(res.next());

                for (int i = 0; i < ordini.size(); i++) {
                    String query2 = "SELECT farmaco.ID_F, farmaco.Nome_F, farmaco.Principio_Attivo, farmaco.Da_Banco, comprende.Quantita_O, farmaco.Scadenza"+
                            " FROM ordine, comprende, farmaco WHERE ordine.ID_O = "+ordini.get(i).getID_O()+" AND ordine.ID_O = comprende.ID_O AND comprende.ID_F = farmaco.ID_F";
                    ResultSet res2;
                    res2 = st.executeQuery(query2);
                    if (!res2.next()){
                        return null;
                    }else {
                        ArrayList<Farmaco> farmaci = new ArrayList<>();
                        do {
                            int id = res2.getInt("ID_F");
                            String nome = res2.getString("Nome_F");
                            String prAtt = res2.getString("Principio_Attivo");
                            String daBanco = res2.getInt("Da_Banco") == 1 ? "Si":"No";
                            int qta = res2.getInt("Quantita_O");
                            String scadenza = res2.getString("Scadenza");
                            Farmaco f = new Farmaco(id, nome, prAtt, scadenza, daBanco, qta);
                            farmaci.add(f);
                        }while(res2.next());
                        ordini.get(i).setFarmaci(farmaci);
                    }
                }


            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ordini;
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
        String query = "Select * FROM ordine, segnalazione WHERE segnalazione.Stato_S = 'Aperta' AND ordine.ID_S = segnalazione.ID_S";
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
                    Farmaco f = new Farmaco(res.getInt("ID_F"), res.getString("Nome_F"), res.getString("Principio_Attivo"), res.getString("Scadenza"), res.getInt("Da_Banco") == 1 ? "Si":"No", res.getInt("Quantita_O"));
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
                    Ordine o = new Ordine(res.getInt("ID_O"), res.getString("DataDiConsegna"), res.getString("Stato_O"), res.getString("Indirizzo"));
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
                String queryComprende = "INSERT INTO comprende (ID_O, ID_F, Quantita_O) VALUES ("+id_ordine+", "+farmaci.get(i).getID()+", "+farmaci.get(i).getQuantita()+")";
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
        String query;
        try {
            st = connAzienda.createStatement();
            for (int i = 0; i < farmaci.size(); i++) {
                query = "UPDATE comprende SET Quantita_O = "+farmaci.get(i).getQuantita()+" WHERE ID_O="+idOrdine;
                System.out.println(query);
                st.executeUpdate(query);
            }
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    //da sistemare id_segnalazione non è is_segnalazione
    public void chiudiSegnalazione(int id_ordine, int id_addetto){
        Statement st;
        int id_Segnalazione = getIdSegnalazioneFromIdOrdine(id_ordine);
        String query = "UPDATE segnalazione SET Stato_S = 'Chiusa' WHERE ID_S = "+id_Segnalazione;
        System.out.println(query);
        try {
            st = connAzienda.createStatement();
            st.executeUpdate(query);
            String query1 = "UPDATE ordine SET ID_A = "+id_addetto+" WHERE ID_O = "+id_ordine;
            System.out.println(query1);
            st.executeUpdate(query1);
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public int getIdSegnalazioneFromIdOrdine(int idOrdine){
        int idSegnalazione = 0;
        Statement st;
        ResultSet res;
        String query = "SELECT * FROM ordine WHERE ordine.ID_O = "+idOrdine;
        try {
            st = connAzienda.createStatement();
            res = st.executeQuery(query);
            if (!res.next()) {
                return 0;
            }else {
                do {
                    idSegnalazione = res.getInt("ID_S");
                }while(res.next());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return idSegnalazione;
    }
    public int getIdOrdineFromIdSegnalazione(int idSegnalazione){
        int idOrdine = 0;
        Statement st;
        ResultSet res;
        String query = "SELECT * FROM ordine, segnalazione WHERE ordine.ID_S = "+idSegnalazione;
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

    public int getNumConsegneInArrivo(){
        int consegne = 0;
        Statement st;
        ResultSet res;
        LocalDate oggi = LocalDate.now();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        String query = "SELECT COUNT(*) FROM ordine WHERE DataDiConsegna = '"+dtf.format(oggi)+"';";
        System.out.println(query);
        try {
            st = connAzienda.createStatement();
            res = st.executeQuery(query);
            if (!res.next()) {
                return 0;
            }else {
                do {
                    consegne = res.getInt("COUNT(*)");
                }while(res.next());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return consegne;
    }

    public ArrayList<Farmaco> getFarmaciInScadenza(){
        ArrayList<Farmaco> farmaci = new ArrayList<>();
        Statement st;
        ResultSet res;
        LocalDate oggi = LocalDate.now();
        String data=oggi.getYear()+"-"+oggi.getMonthValue()+"-01";
        String query = "SELECT * FROM farmaco WHERE Scadenza ='"+data+"'";
        System.out.println(query);
        try {
            st = connFarmacia.createStatement();
            res = st.executeQuery(query);
            if (!res.next()) {
                return null;
            }else {
                do {
                    Farmaco f = new Farmaco(res.getInt("ID_F"), res.getString("Nome_F"), res.getString("Principio_Attivo"), res.getString("Scadenza"), res.getInt("Da_Banco") == 1 ? "Si":"No", res.getInt("Quantita"));
                    farmaci.add(f);
                }while(res.next());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return farmaci;
    }

    public void eliminaOrdine(int idOrdine){
        Statement st;
        String query = "DELETE FROM ordine WHERE ID_O = "+idOrdine;
        String query2 = "DELETE FROM comprende WHERE ID_O = "+idOrdine;
        System.out.println(query);
        try {
            st = connAzienda.createStatement();
            st.executeUpdate(query2);
            st.executeUpdate(query);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void modificaOrdine(int idOrdine, ArrayList<Farmaco> farmaci){
        Statement st;
        String query;

        try {
            st = connAzienda.createStatement();

            for (int i = 0; i < farmaci.size(); i++) {
                query= "UPDATE comprende SET Quantita_O = "+farmaci.get(i).getQuantita()+" WHERE ID_O = "+idOrdine+" AND ID_F= "+farmaci.get(i).getID();
                System.out.println(query);
                st.executeUpdate(query);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Ordine> getOrdiniPeriodici(String indirizzo){
        ArrayList<Ordine> ordini = new ArrayList<>();
        Statement st;
        ResultSet res;
        try {
            st = connAzienda.createStatement();
            String query1 = "SELECT DISTINCT (ordineperiodico.ID_O), Periodicita, DataUltimoOrdine, Quantita_O FROM ordineperiodico, comprendeperiodico, farmaco WHERE Indirizzo = '"+indirizzo+"' AND ordineperiodico.ID_O = comprendeperiodico.ID_O AND comprendeperiodico.ID_F = farmaco.ID_F";
            res = st.executeQuery(query1);
            if (!res.next()) {
                return null;
            }else {
                Ordine o;
                do {
                    int idOrdine = res.getInt("ID_O");
                    System.out.println("ID ORDINE "+ idOrdine);
                    String data = res.getString("DataUltimoOrdine");
                    int periodicita = res.getInt("Periodicita");
                    o = new Ordine(idOrdine, indirizzo, periodicita, data);
                    ordini.add(o);
                }while(res.next());

                for (int i = 0; i < ordini.size(); i++) {
                    String query2 = "SELECT farmaco.ID_F, farmaco.Nome_F, farmaco.Principio_Attivo, farmaco.Da_Banco, comprendeperiodico.Quantita_O, farmaco.Scadenza"+
                            " FROM ordineperiodico, comprendeperiodico, farmaco WHERE ordineperiodico.ID_O = "+ordini.get(i).getID_O()+" AND ordineperiodico.ID_O = comprendeperiodico.ID_O AND comprendeperiodico.ID_F = farmaco.ID_F";
                    ResultSet res2;
                    res2 = st.executeQuery(query2);
                    if (!res2.next()){
                        return null;
                    }else {
                        ArrayList<Farmaco> farmaci = new ArrayList<>();
                        do {
                            int id = res2.getInt("ID_F");
                            String nome = res2.getString("Nome_F");
                            String prAtt = res2.getString("Principio_Attivo");
                            String daBanco = res2.getInt("Da_Banco") == 1 ? "Si":"No";
                            int qta = res2.getInt("Quantita_O");
                            String scadenza = res2.getString("Scadenza");
                            Farmaco f = new Farmaco(id, nome, prAtt, scadenza, daBanco, qta);
                            farmaci.add(f);
                        }while(res2.next());
                        ordini.get(i).setFarmaci(farmaci);
                    }
                }


            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ordini;
    }

    public void modificaOrdinePeriodico(int idOrdine, int periodicita){

    }

    public String getNomeFarmacia(int ID_FARM){
        String nome = "";
        Statement st;
        ResultSet res;
        String query = "SELECT * FROM farmacia WHERE ID_FARM = "+ID_FARM;
        try {
            st = connFarmacia.createStatement();
            res = st.executeQuery(query);
            res.next();
            nome = res.getString("Nome");
        }catch (SQLException e){
            e.printStackTrace();
        }
        return nome;
    }

    public void caricaFarmaciAzienda(int qtaprodotta){
        Statement st;
        ResultSet res;
        String query = "UPDATE farmaco SET Quantita = Quantita + "+qtaprodotta;
        try {
            st = connAzienda.createStatement();
            st.executeUpdate(query);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public int getQuantitaAqcuistabile(int ID_F){
        Statement st;
        ResultSet res;
        int qta = 0;
        int qtaAcqu = 0;
        String query = "SELECT * FROM farmaco WHERE ID_F = "+ID_F;
        try {
            st = connAzienda.createStatement();
            res = st.executeQuery(query);
            if (!res.next()) {
                return 0;
            }else {
                do {
                    qta = res.getInt("Quantita");
                }while(res.next());
            }

            String query2 = "SELECT SUM(comprende.Quantita_O) FROM farmaco, comprende, ordine WHERE farmaco.ID_F = comprende.ID_F AND comprende.ID_O = ordine.ID_O AND ordine.Stato_O = 'In Lavorazione' AND comprende.ID_F ="+ID_F;
            ResultSet res2 = st.executeQuery(query2);
            if (!res2.next()) {
                return 0;
            }else {
                do {
                    qtaAcqu = res2.getInt("SUM(comprende.Quantita_O)");
                }while(res2.next());
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
        qta = qta - qtaAcqu;
        return qta;
    }

    public void inviaPreOrdine(ArrayList<Farmaco> farmaci, String indirizzo, String data){
        Statement st;
        //System.out.println(dtf.format(consegna));
        String queryOrdine = "INSERT INTO ordine(DataDiConsegna, Indirizzo, Stato_O) VALUES ('"+data+"', '"+indirizzo+"', 'In pre-ordine')";
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
                String queryComprende = "INSERT INTO comprende (ID_O, ID_F, Quantita_O) VALUES ("+id_ordine+", "+farmaci.get(i).getID()+", "+farmaci.get(i).getQuantita()+")";
                System.out.println(queryComprende);
                st.executeUpdate(queryComprende);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setInConsegna(int ID_O){
        Statement st;
        String query = "UPDATE ordine SET Stato_O = 'In Consegna' WHERE ID_O = "+ID_O;
        try{
            st = connAzienda.createStatement();
            st.executeUpdate(query);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void aggiornaOrdinePeriodico(String data, int ID_O){
        Statement st;
        String query = "UPDATE ordineperiodico SET DataUltimoOrdine = "+data+" WHERE ID_O = "+ID_O;
        System.out.println(query);
        try{
            st = connAzienda.createStatement();
            st.executeUpdate(query);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void scalaFarmaco(int ID, int qta){
        Statement st;
        String query = "UPDATE farmaco SET Quantita = Quantita - "+qta+ " WHERE ID_F = "+ID;
        try{
            st = connAzienda.createStatement();
            st.executeUpdate(query);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
