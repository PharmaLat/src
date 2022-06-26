package Main;

import Autenticazione.LoginControl;
import Autenticazione.Utente;
import Connectivity.DBMSInterface;
import GestioneConsegne.GestioneConsegneControl;
import GestioneMagazzino.*;
import GestioneOrdini.OrdFarmaciControl;
import GestioneOrdini.OrdiniPeriodiciControl;
import GestioneOrdini.VisualizzaOrdiniControl;
import GestioneSegnalazioni.AvviaSegnalazioneControl;
import GestioneSegnalazioni.GestioneSegnalazioniControl;

public class Main {
	public static void main(String[] args) {
		DBMSInterface db = new DBMSInterface();
		Utente utente = new Utente();
		SchermataPrincipale s = new SchermataPrincipale();
		LoginControl lc = new LoginControl(s, utente, db);
		VisInventarioControl vic = new VisInventarioControl(s, utente, db);
		CaricafarmaciControl cfc = new CaricafarmaciControl(utente, s, db);
		ScaricafarmaciControl sfc = new ScaricafarmaciControl(s, utente, db);
		AvviaSegnalazioneControl asc = new AvviaSegnalazioneControl(s, utente, db);
		GestioneSegnalazioniControl vsc = new GestioneSegnalazioniControl(s, utente, db);
		OrdFarmaciControl ofc = new OrdFarmaciControl(s, utente, db);
		GestioneConsegneControl vcc = new GestioneConsegneControl(utente, s, db);
		VisualizzaOrdiniControl voc = new VisualizzaOrdiniControl(s, utente, db);
		NotificheControl nc = new NotificheControl(s, db, utente);
		OrdiniPeriodiciControl opc = new OrdiniPeriodiciControl(s, utente, db);

	}
}