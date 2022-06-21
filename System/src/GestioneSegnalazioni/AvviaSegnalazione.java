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



		framePnl.add(headerPnl, BorderLayout.NORTH);
		framePnl.add(mainPnl, BorderLayout.CENTER);
		cont.add(framePnl);
	}

}
