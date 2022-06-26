package Main;

import Autenticazione.LoginControl;
import Autenticazione.Utente;
import Connectivity.DBMSInterface;
import GestioneConsegne.GestioneConsegneControl;
import GestioneMagazzino.*;
import GestioneOrdini.OrdFarmaciControl;
import GestioneOrdini.VisualizzaOrdiniControl;
import GestioneSegnalazioni.AvviaSegnalazioneControl;
import GestioneSegnalazioni.GestioneSegnalazioniControl;
import GestioneSegnalazioni.VisualizzaSegnalazioni;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

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

		/**     cose che vedo se rimane tempo
		Container c = s.getContainerPane();

		JPanel panel = new JPanel(new FlowLayout());
		JButton a = new JButton("aadfwadf");
		JButton b = new JButton("awdadaw");
		JButton d = new JButton("awdadaaww");

		panel.add(a);
		panel.add(b);
		panel.add(d);

		c.add(panel);
		**/

	}
}