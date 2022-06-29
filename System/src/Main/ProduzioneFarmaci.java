package Main;

import Connectivity.DBMSInterface;
import GestioneMagazzino.Notifica;

import java.sql.DriverManager;
import java.time.LocalTime;
import java.util.Timer;
import java.util.TimerTask;

public class ProduzioneFarmaci {
    private int qtaProdotta;
    private Timer timer;
    private DBMSInterface db;
    private int tempo;
    public ProduzioneFarmaci(int qtaProdotta, DBMSInterface db, int tempo){
        this.tempo = tempo;
        this.timer = new Timer();
        this.db = db;
        this.qtaProdotta = qtaProdotta;
        produci();
    }

    private void produci(){
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                db.caricaFarmaciAzienda(qtaProdotta);
                System.out.println("Caricati");
            }
        }, tempo*1000, tempo*1000);
    }

    public int getQtaProdotta() {
        return qtaProdotta;
    }

    public int getTempo() {
        return tempo;
    }
}
