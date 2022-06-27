package GestioneMagazzino;

import Autenticazione.Utente;
import Connectivity.DBMSInterface;
import Main.SchermataPrincipale;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;

public class ScaricafarmaciControl {

	private SchermataPrincipale s;
	private Utente u;
	private ScaricaFarmaci sf;
	private DBMSInterface db;
	private ArrayList<Farmaco> farmaci;
	public ScaricafarmaciControl(SchermataPrincipale s, Utente u, DBMSInterface db) {
		this.s = s;
		this.u = u;
		this.db = db;
		scaricaFarmaci();
	}

	private void scaricaFarmaci(){
		JButton scarica = s.getScaricaFarmaci();
		ActionListener scaricaLstnr = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Cliccato Scarica Farmaci");
				farmaci = db.getInventario(u.getID_Farmacia());
				ArrayList<String> nomi = new ArrayList<>();
				for (int i = 0; i < farmaci.size(); i++) {
					if (!nomi.contains(farmaci.get(i).getNome())){
						nomi.add(farmaci.get(i).getNome());
					}
				}
				String[] nomiS = new String[nomi.size()];
				for (int i = 0; i < nomi.size(); i++) {
					nomiS[i] = nomi.get(i).toString();
				}
				sf = new ScaricaFarmaci(nomiS);
				gestisciScarico();
			}
		};
		scarica.addActionListener(scaricaLstnr);
	}

	private void gestisciScarico(){
		JButton scarica = sf.getScarica();
		JComboBox nome = sf.getNomeF();
		JTextField qta = sf.getQta();
		JLabel erroreCampo = sf.getErroreCampo();
		JLabel erroreNome = sf.getErroreNome();
		JLabel erroreQta = sf.geterroreQta();
		JButton indietro = sf.getLogoutButton();
		indietro.addActionListener(e1 -> {
			sf.dispose();
		});
		sf.getFarmaciaLbl().setText(u.getNomeFarmacia());
		ActionListener scaricaLStnr = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				erroreCampo.setVisible(false);
				erroreNome.setVisible(false);
				erroreQta.setVisible(false);
				System.out.println("Cliccato Conferma Scarico");
				ArrayList<Farmaco> farmaciUguali = new ArrayList<>();
				boolean esiste = false;
				System.out.println("Nome selezionato: "+nome.getSelectedItem());
				if (qta.getText().equals("")){
					erroreCampo.setVisible(true);
				} else{
					for (int i = 0; i < farmaci.size(); i++) {
						if (farmaci.get(i).getNome().equals((String) nome.getSelectedItem())){
							farmaciUguali.add(farmaci.get(i));
						}
					}
					int minIndex = 0;
					int sumQta = 0;
					if (!farmaciUguali.isEmpty() && farmaciUguali.size()>1){
						System.out.println("Farmaci ordinati: ");
						farmaciUguali.sort(Comparator.comparing(Farmaco::getData));

						for (int i = 0; i < farmaciUguali.size(); i++) {
							System.out.println(farmaciUguali.get(i));
							sumQta += farmaciUguali.get(i).getQuantita();
						}
						System.out.println("Somma qta: "+sumQta);
					}else {
						System.out.println("C'è solo un farmaco con quel nome: "+farmaciUguali.get(minIndex));
					}


					int qtaint = Integer.parseInt(qta.getText());

					if (qtaint < farmaciUguali.get(minIndex).getQuantita()){
						db.scaricaFarmaci(qtaint, farmaciUguali.get(minIndex));
					} else if (qtaint > sumQta) {
						erroreQta.setVisible(true);
					}else {
						int x = 0;
						while(qtaint>0){
							if (qtaint>farmaciUguali.get(x).getQuantita()){
								db.scaricaFarmaci(farmaciUguali.get(x).getQuantita(), farmaciUguali.get(x));
								qtaint = qtaint-farmaciUguali.get(x).getQuantita();
								x++;
							}else {
								db.scaricaFarmaci(qtaint, farmaciUguali.get(x));
								qtaint = 0;
							}

						}
					}

				}

			}
		};
		scarica.addActionListener(scaricaLStnr);
	}

}
