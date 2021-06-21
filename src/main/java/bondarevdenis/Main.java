package bondarevdenis;


public class Main {
    public static void main(String[] args) {
        GoldMain goldMain = new GoldMain();
        Loader loader = new Loader(goldMain);
        Unloader unloader = new Unloader(goldMain);
        Transporter transporter = new Transporter(goldMain,loader,unloader);
        loader.setTransporter(transporter);
        unloader.setTransporter(transporter);
    }
}
