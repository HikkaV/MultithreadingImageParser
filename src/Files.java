import javax.swing.*;
import java.io.File;

class FilesActions {

        static void makeFile(String strPath) {
            File theDir = new File(strPath);
            if (!theDir.exists() || !theDir.isDirectory()) {
                JOptionPane.showMessageDialog(null, "Создаем папку: " + theDir.getName());
                boolean result = false;

                try {
                    theDir.mkdir();
                    result = true;
                } catch (SecurityException se) {
                    //handle it
                }
                if (result) {
                    JOptionPane.showMessageDialog(null, "Папка создана");
                }
            }

        }


    }

