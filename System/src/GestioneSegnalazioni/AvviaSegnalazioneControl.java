package GestioneSegnalazioni;

import Autenticazione.Utente;
import Connectivity.DBMSInterface;
import GestioneMagazzino.Farmaco;
import Main.SchermataPrincipale;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Map;

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
				List<Map<Farmaco,Integer>> listaOrdini;
				listaOrdini = db.getOrdini(u.getIndirizzoFarmacia());
				for (int i = 0; i < listaOrdini.size(); i++) {
					System.out.println("Ordine "+i);
					for (Map.Entry<Farmaco, Integer> entry : listaOrdini.get(i).entrySet()) {
						System.out.println(entry.getKey()+ " " + entry.getValue());
					}
				}
			}
		};
		avvia.addActionListener(avviaLstnr);

	}
}
