package bondarevdenis;

public class GoldMain {
    public int Gold = 10000;

    public void getGold(int value) {
        Gold = Gold-value;
    }

    public boolean needMoreGold() {
        if (Gold>0){
            return true;
        }else {
            System.out.println("Gold the end.");
            return false;
        }
    }


}
