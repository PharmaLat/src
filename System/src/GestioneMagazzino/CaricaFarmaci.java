package GestioneMagazzino;

import javax.swing.*;
import java.awt.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class CaricaFarmaci extends JFrame {

	private int height = 720;
	private int width = 1280;

	private JPanel framePnl;
	private JTextField nomeF;
	private JTextField qta;
	private JTextField scadenza;
	private JLabel qtaLbl;
	private JLabel nomeLbl;
	private JLabel scadenzaLbl;
	private JButton notificheButton;
	private JButton logoutButton;
	private JPanel headerPnl;
	private JPanel notifichePnl;
	private JPanel farmaciaPnl;
	private JLabel farmaciaLbl;
	private JPanel LogoutPanel;
	private JPanel mainPnl;
	private JButton carica;
	private JLabel erroreNome;
	private JLabel erroreData;
	private JLabel erroreCampo;
	private JLabel caricoRiuscito;
	private Container cont = this.getContentPane();

	public CaricaFarmaci(){
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setSize(width, height);
		this.setResizable(false);
		this.setTitle("Carico Farmaci");
		this.setVisible(true);
		initItems();
	}

	private void initItems(){
		framePnl = new JPanel();
		framePnl.setLayout(null);

		headerPnl = new JPanel();
		headerPnl.setLayout(new GridLayout());

		notifichePnl = new JPanel();
		notifichePnl.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		headerPnl.add(notifichePnl);
		notificheButton = new JButton();
		notificheButton.setText("Notifiche");
		notifichePnl.add(notificheButton);
		farmaciaPnl = new JPanel();
		farmaciaPnl.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		headerPnl.add(farmaciaPnl);
		farmaciaLbl = new JLabel();
		farmaciaLbl.setText("Farmacia");
		farmaciaPnl.add(farmaciaLbl);
		LogoutPanel = new JPanel();
		LogoutPanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
		headerPnl.add(LogoutPanel);
		headerPnl.setBounds(0, 0, width-15, 50);
		logoutButton = new JButton();
		logoutButton.setText("Indietro");
		LogoutPanel.add(logoutButton);

		mainPnl = new JPanel();
		mainPnl.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		mainPnl.setEnabled(true);
		mainPnl.setBounds(0,100,width, 50);


		nomeLbl = new JLabel();
		nomeLbl.setText("Nome");
		mainPnl.add(nomeLbl);
		nomeF = new JTextField();
		nomeF.setColumns(25);
		nomeF.setText("");
		mainPnl.add(nomeF);
		qtaLbl = new JLabel();
		qtaLbl.setText("Quantita");
		mainPnl.add(qtaLbl);
		qta = new JTextField();
		qta.setColumns(5);
		mainPnl.add(qta);
		scadenzaLbl = new JLabel();
		scadenzaLbl.setText("Scadenza (yyyy-mm)");
		mainPnl.add(scadenzaLbl);
		scadenza = new JTextField();
		scadenza.setColumns(10);

		mainPnl.add(scadenza);

		carica = new JButton("Conferma carico");
		carica.setBounds(1100, 600, 150, 25);
		framePnl.add(carica);

		erroreNome = new JLabel("Non esiste un farmaco con quel nome");
		erroreNome.setForeground(Color.red);
		erroreNome.setBounds(400, 200, 300, 20);
		erroreNome.setVisible(false);
		framePnl.add(erroreNome);

		erroreData = new JLabel("Inserisci una data valida");
		erroreData.setForeground(Color.red);
		erroreData.setBounds(400, 230, 200, 20);
		erroreData.setVisible(false);
		framePnl.add(erroreData);

		erroreCampo = new JLabel("Compila tutti i campi");
		erroreCampo.setForeground(Color.red);
		erroreCampo.setBounds(400, 260, 200, 20);
		erroreCampo.setVisible(false);
		framePnl.add(erroreCampo);

		caricoRiuscito = new JLabel("Carico effettuato correttamente");
		caricoRiuscito.setForeground(Color.green);
		caricoRiuscito.setBounds(400, 290, 200, 20);
		caricoRiuscito.setVisible(false);
		framePnl.add(caricoRiuscito);

		framePnl.add(headerPnl, BorderLayout.NORTH);
		framePnl.add(mainPnl, BorderLayout.CENTER);
		cont.add(framePnl);
	}
	public JTextField getNomeF() {
		return nomeF;
	}
	public JTextField getQta() {
		return qta;
	}
	public JTextField getScadenza() {
		return scadenza;
	}
	public JButton getNotificheButton() {
		return notificheButton;
	}
	public JButton getLogoutButton() {
		return logoutButton;
	}

	public JButton getCarica(){return carica;};

	public JLabel getErroreNome() {
		return erroreNome;
	}
	public JLabel getErroreData() {
		return erroreData;
	}
	public JLabel getErroreCampo() {
		return erroreCampo;
	}
	public JLabel getCaricoRiuscito() {
		return caricoRiuscito;
	}
}
