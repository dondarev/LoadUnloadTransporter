package bondarevdenis;

import java.util.concurrent.TimeUnit;

public class Loader implements Runnable{
    private final GoldMain goldMain;
    private Transporter transporter;

    public Loader(GoldMain goldMain) {
        this.goldMain = goldMain;
        new Thread(this).start();
    }

    public synchronized void load() throws InterruptedException {
        wait();
        System.out.println("Loader: load.");
        goldMain.getGold(1000);
        TimeUnit.SECONDS.sleep(5);
        transporter.transporting();
    }
    public synchronized void giveGoldTransporter() throws InterruptedException {
        notify();
    }

    public void setTransporter(Transporter transporter){
        this.transporter = transporter;
    }

    @Override
    public void run() {
        try {
            while (goldMain.needMoreGold()) {
               load();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
