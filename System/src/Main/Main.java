package Main;

import Autenticazione.LoginControl;
import Autenticazione.Utente;
import Connectivity.DBMSInterface;
import GestioneMagazzino.CaricafarmaciControl;
import GestioneMagazzino.ScaricaFarmaci;
import GestioneMagazzino.ScaricafarmaciControl;
import GestioneMagazzino.VisInventarioControl;

import javax.swing.*;
import java.awt.*;

public class Main {
	public static void main(String[] args) {
		DBMSInterface db = new DBMSInterface();
		Utente utente = new Utente();
		SchermataPrincipale s = new SchermataPrincipale();
		LoginControl lc = new LoginControl(s, utente, db);
		VisInventarioControl vic = new VisInventarioControl(s, utente, db);
		CaricafarmaciControl cfc = new CaricafarmaciControl(utente, s, db);
		ScaricafarmaciControl sfc = new ScaricafarmaciControl(s, utente, db);


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