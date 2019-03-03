import java.util.LinkedList;

public class FirstThread extends Thread {
    private int key;
    private String Adress;

    void setKey(int key) {
        this.key = key;
    }

    void setAdress(String adress) {
        Adress = adress;
    }

    @Override
    public void run() {
        Parse parse = new Parse();
        LinkedList<String> strings = new LinkedList<>();
        parse.parse(key,Adress,"http://www.1zoom.me/en/Anime/t2/", strings);
    }
}
