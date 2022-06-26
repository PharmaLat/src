package GestioneMagazzino;

import Autenticazione.Utente;
import Connectivity.DBMSInterface;
import Main.SchermataPrincipale;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class NotificheControl {

    private Timer timer;
    private SchermataPrincipale s;
    private DBMSInterface db;
    private Utente u;
    private NotifichePopUp notif;
    private ArrayList<Notifica> notifiche;
    public NotificheControl(SchermataPrincipale s, DBMSInterface db, Utente u){
        this.s = s;
        this.db = db;
        this.u = u;
        notifiche = new ArrayList<>();
        timer = new Timer();
        reminderCarico();
        controlloFarmaci();
        visualizzaNotifiche();
    }

    private void reminderCarico(){
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                LocalTime nowtime = LocalTime.now();
                System.out.println("notifica  "+nowtime);
                LocalTime inizio = LocalTime.of(20, 0, 0);
                LocalTime fine = LocalTime.of(21, 0, 0);
                if (nowtime.isAfter(inizio) && nowtime.isBefore(fine)){
                    int consegne = db.getNumConsegneInArrivo();
                    if (consegne > 0){
                        notifiche.add(new Notifica("Ci sono "+consegne+" consegne previste per oggi, ricordati di effettuare il carico dei farmaci"));
                        //System.out.println("è l'ora del controllo");
                    }
                }
            }
        }, 60*60*1000, 60*60*1000);
    }

    private void controlloFarmaci(){
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                LocalDate nowtime = LocalDate.now();
                System.out.println("notifica  "+nowtime);
                if (nowtime.getDayOfMonth() == 1){
                    ArrayList<Farmaco> farmaciScaduti = db.getFarmaciInScadenza();
                    if (!farmaciScaduti.isEmpty()){
                        String testo = "Ci sono "+farmaciScaduti.size()+" in scadenza questo mese\n ";
                        for (int i = 0; i < farmaciScaduti.size(); i++) {
                            testo += farmaciScaduti.get(i)+"\n";
                        }
                        notifiche.add(new Notifica(testo));
                    }
                }
            }
        }, 60*60*1000, 60*60*1000);
    }

    private void visualizzaNotifiche(){
        JButton notificheBtn = s.getNotifiche();
        ActionListener notificheLstnr = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                notif = new NotifichePopUp();
                //JPanel grid = notif.getGridPnl();
                if (notifiche.isEmpty()){
                    System.out.println("Notifiche vuote");
                    JLabel vuoto = new JLabel("Non ci sono notifiche");
                    //grid.add(vuoto);
                }else{
                    for (int i = 0; i < notifiche.size(); i++) {
                        JLabel not = new JLabel(notifiche.get(i).getTesto());
                        //grid.add(not);
                    }
                }

            }
        };
        notificheBtn.addActionListener(notificheLstnr);
    }

}
