package Autenticazione;
import Connectivity.DBMSInterface;
import GestioneOrdini.Ordine;
import Main.SchermataPrincipale;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import static java.time.temporal.ChronoUnit.DAYS;
import static java.time.temporal.ChronoUnit.WEEKS;


public class LoginControl {
	private final SchermataPrincipale s;
	private final LoginForm login;
	private JLabel errore;
	private final Utente utente;
	private final DBMSInterface db;
	private ResultSet res;

	public LoginControl(SchermataPrincipale sc, Utente u, DBMSInterface dbms, LoginForm login){
		this.s = sc;
		this.utente = u;
		this.db = dbms;
		this.login = login;
		gestisciAccesso();
		gestisciLogout();
	}
	private void gestisciAccesso(){
		JButton accedi = login.getAccedi();
		errore = login.getErrore();
		ActionListener accediLstnr = e -> {
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
							System.out.println("Sto loggando un farmacista");
							controllaOrdini();
							controllaOrdiniPeriodici();
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
		JButton logout = s.getLogout();
		JTextField username = login.getUsername();
		JTextField pass = login.getPassword();
		ActionListener logoutLstnr = e -> {
			switch (utente.getRuolo()) {
				case "Farmacista" -> {
					s.removeFarmPanel();
					s.getVisualizzaSegnalazioni().setVisible(true);
				}
				case "corriere" -> s.removeCorrierePanel();
				case "Addetto Azienda" -> {
					s.removeAddettoPanel();
					s.getAvviaSegnalazione().setVisible(true);
				}
			}
			username.setText(null);
			pass.setText(null);
			s.setVisible(false);
			login.setVisible(true);
		};
		logout.addActionListener(logoutLstnr);
	}
	private void controllaOrdini(){
		System.out.println("Controllo Ordini");
		ArrayList<Ordine> ordini = db.getOrdini(utente.getIndirizzoFarmacia());
		if (ordini != null){
			for (Ordine ordine : ordini) {
				String data = ordine.getDataConsegna();
				String[] dataSplit = data.split("-");
				LocalDate oggi = LocalDate.now();
				LocalDate consegna = LocalDate.of(Integer.parseInt(dataSplit[0]), Integer.parseInt(dataSplit[1]), Integer.parseInt(dataSplit[2]));
				System.out.println(DAYS.between(oggi, consegna) + " ID " + ordine.getID_O());
				if (DAYS.between(oggi, consegna) <= 2 && ordine.getStato().equals("In Lavorazione")) {
					db.setInConsegna(ordine.getID_O());
					for (int j = 0; j < ordine.getFarmaci().size(); j++) {
						db.scalaFarmaco(ordine.getFarmaci().get(j).getID(), ordine.getFarmaci().get(j).getQuantita());
					}
				}
			}
		}
	}
	private void controllaOrdiniPeriodici(){
		System.out.println("Controllo ordini periodici");
		ArrayList<Ordine> ordini = db.getOrdiniPeriodici(utente.getIndirizzoFarmacia());
		for (Ordine ordine : ordini) {
			int periodicita = ordine.getPeriodicita();
			String ultimoOrdine = ordine.getDataUltimoOrdine();
			String[] dataSplit = ultimoOrdine.split("-");
			LocalDate oggi = LocalDate.now();
			LocalDate ultimaConsegna = LocalDate.of(Integer.parseInt(dataSplit[0]), Integer.parseInt(dataSplit[1]), Integer.parseInt(dataSplit[2]));
			if (WEEKS.between(oggi, ultimaConsegna) >= periodicita && ultimaConsegna.isBefore(oggi)) {
				db.inviaOrdine(ordine.getFarmaci(), ordine.getIndirizzo());
				DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
				String newData = df.format(oggi);
				db.aggiornaOrdinePeriodico(newData, ordine.getID_O());
			}
		}
	}

}
