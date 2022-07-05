package Autenticazione;

public class Utente {
    private String nome;
    private String cognome;
    private String ruolo;
    private int ID_Farmacia;
    private int ID;
    private String nomeFarmacia;
    private String indirizzoFarmacia;

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getCognome() {
        return cognome;
    }
    public void setCognome(String cognome) {
        this.cognome = cognome;
    }
    public String getRuolo() {
        return ruolo;
    }
    public void setRuolo(String ruolo) {
        this.ruolo = ruolo;
    }
    public int getID_Farmacia() {
        return ID_Farmacia;
    }
    public void setID_Farmacia(int ID_Farmacia) {
        this.ID_Farmacia = ID_Farmacia;
    }
    public String getNomeFarmacia() {
        return nomeFarmacia;
    }
    public void setNomeFarmacia(String nomeFarmacia) {
        this.nomeFarmacia = nomeFarmacia;
    }
    public String getIndirizzoFarmacia() {
        return indirizzoFarmacia;
    }
    public void setIndirizzoFarmacia(String indirizzoFarmacia) {
        this.indirizzoFarmacia = indirizzoFarmacia;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void cancellaDati(){
        this.setNome("");
        this.setCognome("");
        this.setRuolo("");
        this.setID_Farmacia(999999);
        this.setIndirizzoFarmacia("");
        this.setNomeFarmacia("");
    }

}
