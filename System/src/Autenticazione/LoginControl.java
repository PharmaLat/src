package Autenticazione;

import Connectivity.DBMSInterface;
import Main.SchermataPrincipale;

public class LoginControl {
    private static LoginForm login;
    private static boolean logged;
    private static String[] datiUtente = new String[4];
    public LoginControl(){
        login=new LoginForm();
    }
    public static void sendCredentials(String username, String password){
        if (username.endsWith(".farm")){
            if (!DBMSInterface.checkCredentialsFarmacia(username, password)){
                login.getErrore().setVisible(true);
            }else {
                Utente.Utentefarmacista(datiUtente[0], datiUtente[1], datiUtente[2], Integer.parseInt(datiUtente[3]));
                SchermataPrincipale s = new SchermataPrincipale();
                login.setVisible(false);
            }
        } else if (username.endsWith(".imp")) {
            if (!DBMSInterface.checkCredentialsAzienda(username, password)){
                login.getErrore().setVisible(true);
            }else {
                Utente.UtenteImpiegato(datiUtente[0], datiUtente[1], datiUtente[2]);
                SchermataPrincipale s = new SchermataPrincipale();
                login.setVisible(false);
            }
        }else{
            login.getErrore().setVisible(true);
        }

    }

    public static void setDatiUtente(String nome, String cognome, String ruolo, int id_farm){
        datiUtente[0]= nome;
        datiUtente[1]= cognome;
        datiUtente[2]= ruolo;
        datiUtente[3]= Integer.toString(id_farm);
    }
    public boolean isLogged(){return logged;}

}
