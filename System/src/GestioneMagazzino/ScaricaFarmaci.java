package GestioneMagazzino;

import javax.swing.*;
import java.awt.*;

public class ScaricaFarmaci extends JFrame{

	private int height = 720;
	private int width = 1280;

	private JPanel framePnl;
	private JComboBox nomeF;
	private JTextField qta;
	private JLabel qtaLbl;
	private JLabel nomeLbl;
	private JButton notificheButton;
	private JButton logoutButton;
	private JPanel headerPnl;
	private JPanel notifichePnl;
	private JPanel farmaciaPnl;
	private JLabel farmaciaLbl;
	private JPanel LogoutPanel;
	private JPanel mainPnl;
	private JButton scarica;
	private JLabel erroreNome;
	private JLabel erroreQta;
	private JLabel erroreCampo;
	private JLabel caricoRiuscito;
	private Container cont = this.getContentPane();

	public ScaricaFarmaci(String[] farmaci){
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setSize(width, height);
		this.setResizable(false);
		this.setTitle("Scarico Farmaci");
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		initItems(farmaci);
	}

	private void initItems(String[] farmaci){
		framePnl = new JPanel();
		framePnl.setLayout(null);

		headerPnl = new JPanel();
		headerPnl.setLayout(new GridLayout());

		notifichePnl = new JPanel();
		notifichePnl.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		headerPnl.add(notifichePnl);
		farmaciaPnl = new JPanel();
		farmaciaPnl.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		headerPnl.add(farmaciaPnl);
		farmaciaLbl = new JLabel();
		farmaciaLbl.setText("Farmacia");
		farmaciaLbl.setFont(new Font("Calibri", Font.PLAIN, 23));
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
		nomeF = new JComboBox(farmaci);
		mainPnl.add(nomeF);
		qtaLbl = new JLabel();
		qtaLbl.setText("Quantita");
		mainPnl.add(qtaLbl);
		qta = new JTextField();
		qta.setColumns(5);
		mainPnl.add(qta);

		scarica = new JButton("Conferma scarico");
		scarica.setBounds(1100, 600, 150, 25);
		framePnl.add(scarica);

		erroreNome = new JLabel("Non esiste un farmaco con quel nome");
		erroreNome.setForeground(Color.red);
		erroreNome.setBounds(400, 200, 300, 20);
		erroreNome.setVisible(false);
		framePnl.add(erroreNome);

		erroreQta = new JLabel("Quantita inserita maggiore di quella presente in magazzino");
		erroreQta.setForeground(Color.red);
		erroreQta.setBounds(400, 230, 250, 20);
		erroreQta.setVisible(false);
		framePnl.add(erroreQta);

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
	public JComboBox getNomeF() {
		return nomeF;
	}
	public JTextField getQta() {
		return qta;
	}
	public JButton getNotificheButton() {
		return notificheButton;
	}
	public JButton getLogoutButton() {
		return logoutButton;
	}

	public JButton getScarica(){return scarica;};

	public JLabel getErroreNome() {
		return erroreNome;
	}
	public JLabel geterroreQta() {
		return erroreQta;
	}
	public JLabel getErroreCampo() {
		return erroreCampo;
	}
	public JLabel getCaricoRiuscito() {
		return caricoRiuscito;
	}

	public JLabel getFarmaciaLbl() {
		return farmaciaLbl;
	}
}
