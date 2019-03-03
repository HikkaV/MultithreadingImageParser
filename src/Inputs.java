import javax.swing.*;
import java.io.File;
import java.util.Arrays;

class Inputs {
    private  int quantity;

    int  getQuantity() {
        return quantity;
    }

    void setQuantity(int quantity) {
      this.quantity = quantity;
    }

    private static int counter =0;

    static String inputAdress(String message) {
        String b = "";
        boolean matcher;
        counter++;
        String z = message;
        File files [] = File.listRoots();
        StringBuilder builder = new StringBuilder();
        for (File f:files
             ) {
            builder.append(f.toString()).append("\n");
        }
        do {
            b = JOptionPane.showInputDialog(z);
            if (b == null) {
                JOptionPane.showMessageDialog(null, "Выход из программы");
                System.exit(1);
            }
            if (System.getProperty("os.name").equals("Windows")){
            matcher = b.matches(
                    "^(([a-zA-Z]\\:)|(\\\\))(\\\\{1}|((\\\\{1})[^\\\\]([^/:*?<>\"|]*))+)$");}
            else {
                matcher = b.matches("^(/[^/ ]*)+/?$");}

            if (!Arrays.toString(files).contains(b.charAt(0)+"") ){
                matcher=false;



                    JOptionPane.showMessageDialog(null, "Введите существующий 'hard drive'" + "\nСписок существующих дисков : "+builder);

            }
            if (counter==2){
                if(!new File(b+"\\chromedriver.exe").exists()){
                    matcher = false;
                    JOptionPane.showMessageDialog(null, "В папке " + b.charAt(0)+":\\"+" не найден файл 'chromedriver.exe'");

                }
            }
            z = "Введите путь в правильном формате";

        }
        while (!matcher  );

        return b;
    }
    static int picInput() {
        String b;
        int a = 1;
        boolean flag = true;
        do {
            b = JOptionPane.showInputDialog("Введите примерное количество картинок");
            if (b == null) {
                JOptionPane.showMessageDialog(null, "Выход из программы");
                System.exit(1);
            }
            try {
                a = Integer.parseInt(b);
                flag = false;
            } catch (NumberFormatException e) {
                System.out.println(e.getLocalizedMessage());

            }
            if (a <= 0) {
                flag = true;
                JOptionPane.showMessageDialog(null, "Количество картинок не может быть отрицательным числом или нулем");
            }
        }
        while (flag);
        return a;

    }

}
