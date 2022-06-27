package Main;

import GestioneMagazzino.Notifica;

import java.time.LocalTime;
import java.util.Timer;
import java.util.TimerTask;

public class ProduzioneFarmaci {
    private int qtaProdotta;
    private Timer timer;
    public ProduzioneFarmaci(int qtaProdotta){
        this.qtaProdotta = qtaProdotta;
        produci();
    }

    private void produci(){
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {

            }
        }, 60*60*1000, 60*60*1000);
    }


}
