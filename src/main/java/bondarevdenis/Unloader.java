package bondarevdenis;

import java.util.concurrent.TimeUnit;

public class Unloader implements Runnable{
    private final GoldMain goldMain;

    private Transporter transporter;


    public Unloader(GoldMain goldMain) {
        this.goldMain = goldMain;
        new Thread(this).start();
    }

    public synchronized void getGoldFromTransporter(){
        notify();
    }

    public synchronized void unload() throws InterruptedException {
        wait();
        System.out.println("Unloader: unload.");
        TimeUnit.SECONDS.sleep(5);
        transporter.transporting();
    }

    public void setTransporter(Transporter transporter){
        this.transporter = transporter;
    }

    @Override
    public void run() {
        while (goldMain.needMoreGold()) {
            try {
                unload();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
