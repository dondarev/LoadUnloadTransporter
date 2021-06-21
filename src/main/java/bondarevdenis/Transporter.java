package bondarevdenis;

import java.util.concurrent.TimeUnit;

public class Transporter implements Runnable {
    private final GoldMain goldMain;
    private final Loader loader;
    private final Unloader unloader;

    public Transporter(  GoldMain goldMain, Loader loader, Unloader unloader) {
        this.goldMain = goldMain;
        this.loader = loader;
        this.unloader = unloader;
        new Thread(this).start();
    }

    public synchronized void transporting() throws InterruptedException {
        System.out.println("Transporter: transporting.");
        TimeUnit.SECONDS.sleep(5);
        notify();
    }


    public synchronized void giveGoldUnLoader() throws InterruptedException {
        System.out.println("Transporter: give gold uploader.");
        unloader.getGoldFromTransporter();
        wait();
    }

    public synchronized void getGoldFromLoader() throws InterruptedException {
        System.out.println("Transporter: get gold from loader.");
        loader.giveGoldTransporter();
        wait();
    }


    @Override
    public void run() {
        while (goldMain.needMoreGold()){
            try {
                getGoldFromLoader();
                giveGoldUnLoader();

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
