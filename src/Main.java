import javax.swing.*;
import java.io.File;

public class Main {

    private static FirstThread firstThread = new FirstThread();
    private static SecondThread secondThread = new SecondThread();
    private static Inputs inputs =new Inputs();
    public static void main(String[] args) {

    Parse parse = new Parse();

       JOptionPane.showMessageDialog(null, "Pics parser v.2.0 made by HikkaV " +
                "\nFunctions and features : \n1. Remembering the path to the 'chromedrive.exe' " +
                "\n2. Creating a new folder for keeping pics, if it doesn't exist " +
                "\n3. Currently parsing only two websites , multithreading  is used" +
                "\n4. Supported languages: Russian");

      //  InfWindow infWindow = new InfWindow();
        //infWindow.setVisible(true);
        int key = Inputs.picInput();
        inputs.setQuantity(key);
        String message = "Введите путь к папке для скачивания файлов";
        String Adress = Inputs.inputAdress(message);
        FilesActions.makeFile(Adress);
        int firstlen= new File(Adress).listFiles().length;
        System.out.println(Adress);
        firstThread.setAdress(Adress);
        firstThread.setKey(inputs.getQuantity());
        secondThread.setAdress(Adress);
        secondThread.setKey(inputs.getQuantity());
        firstThread.start();
        secondThread.start();
        try {
            firstThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            secondThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (!secondThread.isAlive() && !firstThread.isAlive()){
            JOptionPane.showMessageDialog(null, "Было скачано " + key+" картинок в папку : \n " + Adress);
            int lastlen =  new File(Adress).listFiles().length - firstlen;
            parse.trimToKeySize(key, lastlen, Adress );
       }
    }

}
