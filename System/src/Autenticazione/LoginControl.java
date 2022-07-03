package Autenticazione;
import Connectivity.DBMSInterface;
import Main.SchermataPrincipale;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginControl {
	private SchermataPrincipale s;
	private LoginForm login;

	private JButton accedi;
	private JLabel errore;
	private Utente utente;
	private DBMSInterface db;
	private ResultSet res;
	private JButton logout;
	public LoginControl(SchermataPrincipale sc, Utente u, DBMSInterface dbms, LoginForm login){
		this.s = sc;
		this.utente = u;
		this.db = dbms;
		this.login = login;
		gestisciAccesso();
		gestisciLogout();
	}

	private void gestisciAccesso(){

		accedi = login.getAccedi();
		errore = login.getErrore();
		ActionListener accediLstnr = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JTextField username = login.getUsername();
				JTextField pass = login.getPassword();
				//System.out.println(username.getText()+" "+ pass.getText());
				if (username.getText().endsWith(".imp")){
					res = db.checkCredentialsAzienda(username.getText(), pass.getText());
					checkResults(res, "azienda");

				}else if (username.getText().endsWith(".farm")){
					res = db.checkCredentialsFarmacia(username.getText(), pass.getText());
					checkResults(res, "farmacia");
				}else {
					errore.setVisible(true);
				}
			}
		};
		accedi.addActionListener(accediLstnr);
	}

	private void checkResults(ResultSet r, String ruolo) {
		if (r != null){
			try {
				if (!r.next()) {
					errore.setVisible(true);
				} else {
					do {
						if (ruolo.equals("azienda")){
							utente.setNome(res.getString("Nome_I"));
							utente.setCognome(res.getString("Cognome_I"));
							utente.setRuolo(res.getString("Ruolo"));
							utente.setID(res.getInt("ID_I"));
							if (res.getString("Ruolo").equals("corriere")){
								s.initCorriere();
								s.getFarmaciaLbl().setText("PharmaLat");
							} else if (res.getString("Ruolo").equalsIgnoreCase("Addetto Azienda")) {
								s.initAddetto();
								s.getFarmaciaLbl().setText("PharmaLat");
							}
						} else if (ruolo.equals("farmacia")) {
							utente.setNome(res.getString("Nome"));
							utente.setCognome(res.getString("Cognome"));
							utente.setRuolo("Farmacista");
							utente.setID_Farmacia(Integer.parseInt(res.getString("ID_FARM")));
							utente.setIndirizzoFarmacia(db.getIndirizzo(res.getInt("ID_FARM")));
							s.initFarmacista();
							String nome = db.getNomeFarmacia(Integer.parseInt(res.getString("ID_FARM")));
							utente.setNomeFarmacia(nome);
							s.getFarmaciaLbl().setText(nome);
						}
						s.setVisible(true);
						login.setVisible(false);
					} while (r.next());
				}
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}
	}
	private void gestisciLogout(){
		logout = s.getLogout();
		JTextField username = login.getUsername();
		JTextField pass = login.getPassword();
		ActionListener logoutLstnr = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				switch (utente.getRuolo()){
					case "Farmacista":
						s.removeFarmPanel();
						s.getVisualizzaSegnalazioni().setVisible(true);
						break;
					case "corriere":
						s.removeCorrierePanel();
						break;
					case "Addetto Azienda":
						s.removeAddettoPanel();
						s.getAvviaSegnalazione().setVisible(true);
						break;
				}
				username.setText(null);
				pass.setText(null);
				s.setVisible(false);
				login.setVisible(true);
			}
		};
		logout.addActionListener(logoutLstnr);
	}

	public LoginForm getLogin() {
		return login;
	}
}
