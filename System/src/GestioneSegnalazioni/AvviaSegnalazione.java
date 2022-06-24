package GestioneSegnalazioni;

import javax.swing.*;
import java.awt.*;

public class AvviaSegnalazione extends JFrame {

	private int width = 1280;
	private int height = 720;

	private Container cont = this.getContentPane();
	private JPanel framePnl;
	private JPanel headerPnl;
	private JPanel mainPnl;
	private JPanel notifichePnl;
	private JPanel farmaciaPnl;
	private JPanel LogoutPanel;
	private JLabel farmaciaLbl;
	private JButton notificheButton;
	private JButton logoutButton;
	private JComboBox idOrdine;
	private JLabel idOrdineLbl;
	private JLabel descrizioneLbl;
	private JTextArea descrizione;
	private JButton inviaSegnalazione;

	public AvviaSegnalazione(){
		this.setTitle("Avvia Segnalazione");
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setSize(width, height);
		this.setResizable(false);
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

		idOrdineLbl = new JLabel("Id ordine:");
		idOrdineLbl.setBounds(200, 100, 75, 20);
		idOrdine = new JComboBox();
		idOrdine.setBounds(275, 100, 50, 20);
		descrizione = new JTextArea();
		descrizione.setBounds(200, 150, 650, 200);
		descrizioneLbl = new JLabel("Descrizione del problema:");
		descrizioneLbl.setBounds(200, 125, 200, 25);
		inviaSegnalazione = new JButton("Invia Segnalazione");
		inviaSegnalazione.setBounds(800, 600, 200, 25);

		framePnl.add(inviaSegnalazione);
		framePnl.add(descrizione);
		framePnl.add(descrizioneLbl);
		framePnl.add(idOrdineLbl);
		framePnl.add(idOrdine);
		framePnl.add(headerPnl, BorderLayout.NORTH);
		framePnl.add(mainPnl, BorderLayout.CENTER);
		cont.add(framePnl);
	}
	public JComboBox getIdOrdine() {
		return idOrdine;
	}
	public JTextArea getDescrizione() {
		return descrizione;
	}
	public JButton getInviaSegnalazione() {
		return inviaSegnalazione;
	}
}
