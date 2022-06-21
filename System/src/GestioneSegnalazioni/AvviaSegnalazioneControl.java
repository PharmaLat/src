package GestioneSegnalazioni;

import Autenticazione.Utente;
import Connectivity.DBMSInterface;
import Main.SchermataPrincipale;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AvviaSegnalazioneControl {
	private SchermataPrincipale s;
	private Utente u;
	private DBMSInterface db;
	private AvviaSegnalazione as;

	public AvviaSegnalazioneControl(SchermataPrincipale s, Utente u, DBMSInterface db) {
		this.s = s;
		this.u = u;
		this.db = db;
		gestisciAvviaSeg();
	}

	private void gestisciAvviaSeg(){
		JButton avvia = s.getAvviaSegnalazione();
		ActionListener avviaLstnr = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Cliccato avvia Segnalazione");
				as = new AvviaSegnalazione();
			}
		};
		avvia.addActionListener(avviaLstnr);

	}
}
