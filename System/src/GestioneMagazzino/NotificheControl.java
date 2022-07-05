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
        this.notifiche = new ArrayList<>();
        this.timer = new Timer();
        this.reminderCarico();
        this.controlloFarmaci();
        this.visualizzaNotifiche();
    }

    private void reminderCarico(){
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                LocalTime nowtime = LocalTime.now();
                //System.out.println("notifica  "+nowtime);
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
                //System.out.println("notifica  "+nowtime);
                if (nowtime.getDayOfMonth() == 30){
                    ArrayList<Farmaco> farmaciScaduti = db.getFarmaciInScadenza();
                    if (!farmaciScaduti.isEmpty()){
                        String testo = "Ci sono "+farmaciScaduti.size()+" in scadenza questo mese  \n ";
                        for (int i = 0; i < farmaciScaduti.size(); i++) {
                            testo += farmaciScaduti.get(i).getNome()+"  \n";
                        }
                        notifiche.add(new Notifica(testo));
                    }
                    timer.cancel();
                    timer.purge();
                }
            }
        }, 10*1000, 10*1000);
    }

    private void visualizzaNotifiche(){
        JButton notificheBtn = s.getNotifiche();
        ActionListener notificheLstnr = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                notif = new NotifichePopUp(notifiche);
            }
        };
        notificheBtn.addActionListener(notificheLstnr);
    }

    public ArrayList<Notifica> getNotifiche() {
        return notifiche;
    }
}
