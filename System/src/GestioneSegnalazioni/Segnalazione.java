package GestioneSegnalazioni;

public class Segnalazione {
    private int ID_S;
    private String descrizione;
    private String Stato_S;

    private int ID_O;

    public Segnalazione(int ID_S, String descrizione, String stato_S, int ID_O) {
        this.ID_S = ID_S;
        this.descrizione = descrizione;
        Stato_S = stato_S;
        this.ID_O = ID_O;
    }

    public int getID_S() {
        return ID_S;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public String getStato_S() {
        return Stato_S;
    }

    public int getID_O() {
        return ID_O;
    }

    @Override
    public String toString() {
        return "Segnalazione{" +
                "ID_S=" + ID_S +
                ", descrizione='" + descrizione + '\'' +
                ", Stato_S='" + Stato_S + '\'' +
                ", ID_O=" + ID_O +
                '}';
    }
}
