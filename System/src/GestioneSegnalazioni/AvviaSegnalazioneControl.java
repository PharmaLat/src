package GestioneSegnalazioni;

import Autenticazione.Utente;
import Connectivity.DBMSInterface;
import GestioneMagazzino.Farmaco;
import GestioneOrdini.Ordine;
import Main.SchermataPrincipale;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
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
		ActionListener avviaLstnr = e -> {
			System.out.println("Cliccato avvia Segnalazione");
			as = new AvviaSegnalazione();
			as.getFarmaciaLbl().setText(u.getNomeFarmacia());
			as.getLogoutButton().addActionListener(e1 -> {
				as.dispose();
			});
			JComboBox idOrdine = as.getIdOrdine();
			ArrayList<Ordine> listaOrdini = db.getOrdini(u.getIndirizzoFarmacia());
			if (listaOrdini != null){
				for (int i = 0; i < listaOrdini.size(); i++) {
					idOrdine.addItem(listaOrdini.get(i).getID_O());
				}
				inviaSegnalazione();
			}else {
				JOptionPane.showMessageDialog(as, "Non hai ancora effettuato ordini", "Nessun ordine", JOptionPane.INFORMATION_MESSAGE);
				as.dispose();
			}
		};
		avvia.addActionListener(avviaLstnr);
	}

	private void inviaSegnalazione(){
		JButton invia = as.getInviaSegnalazione();
		ActionListener inviaLstnr = e -> {
			JComboBox idOrdine = as.getIdOrdine();
			JTextArea descrizione = as.getDescrizione();
			//System.out.println("Id :" + idOrdine.getSelectedItem());
			//System.out.println("Descrizione: "+descrizione.getText());
			db.inviaSegnalazione(Integer.parseInt(idOrdine.getSelectedItem()+""), descrizione.getText());
			JOptionPane.showMessageDialog(as, "Segnalazione Inviata", "Conferma", JOptionPane.INFORMATION_MESSAGE);
			as.dispose();
		};
		invia.addActionListener(inviaLstnr);
	}
}
