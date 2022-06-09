package Autenticazione;
public class Utente {
    private static String nome;
    private static String cognome;
    private static String ruolo;
    private static int ID_Farmacia;

    public static void UtenteImpiegato(String nome1, String cognome1, String ruolo1) {
        nome = nome1;
        cognome = cognome1;
        ruolo = ruolo1;
    }

    public static void Utentefarmacista(String nome1, String cognome1, String ruolo1, int id_farm) {
        nome = nome1;
        cognome = cognome1;
        ruolo = ruolo1;
        ID_Farmacia=id_farm;
    }

    public String getNome() {
        return nome;
    }




}
