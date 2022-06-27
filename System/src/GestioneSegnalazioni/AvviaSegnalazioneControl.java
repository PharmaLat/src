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
				JComboBox idOrdine;
				as = new AvviaSegnalazione();
				List<Map<Farmaco,String>> listaOrdini;
				/*listaOrdini = db.getOrdini(u.getIndirizzoFarmacia());
				idOrdine = as.getIdOrdine();
				for (int i = 0; i < listaOrdini.size(); i++) {
					Map.Entry<Farmaco,String> entry = listaOrdini.get(i).entrySet().iterator().next();
					String[] info = entry.getValue().split("-");
					idOrdine.addItem(info[1]);
				}*/
				inviaSegnalazione();
			}
		};
		avvia.addActionListener(avviaLstnr);
	}

	private void inviaSegnalazione(){
		JButton invia = as.getInviaSegnalazione();
		ActionListener inviaLstnr = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JComboBox idOrdine = as.getIdOrdine();
				JTextArea descrizione = as.getDescrizione();
				//System.out.println("Id :" + idOrdine.getSelectedItem());
				//System.out.println("Descrizione: "+descrizione.getText());
				db.inviaSegnalazione(Integer.parseInt((String)idOrdine.getSelectedItem()), descrizione.getText());
			}
		};
		invia.addActionListener(inviaLstnr);
	}
}
