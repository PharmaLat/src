package GestioneMagazzino;

import Autenticazione.Utente;
import Connectivity.DBMSInterface;
import Main.SchermataPrincipale;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CaricafarmaciControl {
	private Utente u;
	private SchermataPrincipale s;
	private DBMSInterface db;
	private CaricaFarmaci cf;
	ArrayList<Farmaco> farmaci;
	public CaricafarmaciControl(Utente u, SchermataPrincipale s, DBMSInterface db) {
		this.u = u;
		this.s = s;
		this.db = db;

		caricafarmaci();
	}

	private void caricafarmaci(){
		JButton carica = s.getCaricaFarmaci();
		ActionListener caricaLstnr1 = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Cliccato Carica farmaci");
				cf = new CaricaFarmaci();
				farmaci = db.getFarmaciAcquistabili();
				gestisciCarico();
			}
		};
		carica.addActionListener(caricaLstnr1);
	}

	private void gestisciCarico(){
		JButton carica = cf.getCarica();
		JTextField nome = cf.getNomeF();
		JTextField qta = cf.getQta();
		JTextField scadenza = cf.getScadenza();
		JLabel erroreNome = cf.getErroreNome();
		JLabel erroreData = cf.getErroreData();
		JLabel erroreCampo = cf.getErroreCampo();
		JLabel caricoRiuscito = cf.getCaricoRiuscito();
		ActionListener caricaLstnr = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Cliccato Conferma Carico");
				erroreNome.setVisible(false);
				erroreData.setVisible(false);
				erroreCampo.setVisible(false);
				caricoRiuscito.setVisible(false);
				boolean esiste = false;
				String prAtt = "";
				String daBanco = "";

				for (int i = 0; i < farmaci.size(); i++) {
					if (farmaci.get(i).getNome().equals(nome.getText())){
						esiste = true;
						prAtt = farmaci.get(i).getPrincipioAttivo();
						daBanco = farmaci.get(i).getDaBanco();
					}
				}

				if (nome.getText().equals("") || scadenza.getText().equals("") || qta.getText().equals("")){
					erroreCampo.setVisible(true);
				} else if (!esiste) {
					erroreNome.setVisible(true);
				} else if (!controllaData(scadenza.getText())) {
					erroreData.setVisible(true);
				}else{
					Farmaco f = new Farmaco(nome.getText(), prAtt,scadenza.getText()+"-01" , daBanco, Integer.parseInt(qta.getText()));
					db.inserisciFarmacoFarmacia(f, u.getID_Farmacia());
					caricoRiuscito.setVisible(true);
				}

			}
		};
		carica.addActionListener(caricaLstnr);
	}

	private boolean controllaData(String data){
		LocalDate oggi = LocalDate.now();
		int anno = oggi.getYear();
		int mese = oggi.getMonthValue();
		String[] scadenza = data.split("-");
		int annoScadenza = Integer.parseInt(scadenza[0]);
		int meseScadenza = Integer.parseInt(scadenza[1]);
		System.out.println(annoScadenza);
		System.out.println(meseScadenza);

		Pattern pattern = Pattern.compile("^[0-9]{4}-[0-9]{2}$");
		Matcher matcher = pattern.matcher(data);
		boolean matchFound = matcher.find();

		if (annoScadenza<anno || (annoScadenza==anno && meseScadenza<mese)){
			return false;
		}else if (!matchFound){
			return false;
		} else if (meseScadenza>12) {
			return false;
		}

		return true;
	}

}

