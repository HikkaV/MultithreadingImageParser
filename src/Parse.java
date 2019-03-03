import org.jetbrains.annotations.NotNull;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;


class Parse {

     void parse(int key, String Adress, String url, LinkedList<String> strings) {
        ExecutorService threadPool = Executors.newFixedThreadPool(5);
        WebDriver driver = new ChromeDriver();
        File inputdir = new File(Adress);
        int firstlen;
       try{  firstlen = inputdir.listFiles().length;}
       catch (NullPointerException e){
           System.out.println(e.getLocalizedMessage());
           firstlen=0;
       }

        int lastlen;
        int loadeddata = 0;
        int i = 0;

        k:
        do {
            i++;
            String new_url =String.format(url+"%d", i);
            driver.get(new_url);
            JavascriptExecutor jse = (JavascriptExecutor) driver;
            Object lastheight = jse.executeScript("return document.body.ScrollHeight");
            while (true) {


                jse.executeScript("window.scrollTo(0, document.body.scrollHeight);");
                //driver.navigate().refresh();
                Object newheight = jse.executeScript("return document.body.ScrollHeight");
                Document doc = Jsoup.parse(driver.getPageSource());

                Elements elements1 = doc.select("img");


                strings.addAll(elements1.stream().map(element -> element.attr("src"))
                        .filter(o -> o.contains("png") || o.contains("jpg")).collect(Collectors.toCollection(LinkedList::new)));

                strings.stream().distinct().limit(key - loadeddata).forEach(o -> threadPool.submit(() -> download(o, Adress)));
                strings = strings.stream().skip(key - loadeddata).collect(Collectors.toCollection(LinkedList::new));
                lastlen = Objects.requireNonNull(inputdir.listFiles()).length;
                loadeddata = lastlen - firstlen;

                if (loadeddata >= key) {
                    driver.close();
                    strings.clear();
                    break k;
                }

                if (newheight == lastheight) {
                    break;
                } else {
                    lastheight = newheight;
                }

            }
        } while (true);

    }

    void trimToKeySize(int key, int lastlen, String strPath){

        int counter = lastlen-key;
        int c =0;
        File file = new File(strPath);

        File [] filelist =file.listFiles();
        Collections.shuffle(Arrays.asList(filelist));
        for (File file1:
             filelist) {
            if (c == counter) {
                System.exit(1);
            }
            if (file1.delete()) {
                c++; }

        }



        }



    private static  void download(String strURL, String strPath) {


        try {
            URL connection = new URL(strURL);
            HttpURLConnection urlconn;
            urlconn = (HttpURLConnection) connection.openConnection();
            urlconn.setRequestMethod("GET");
            urlconn.connect();
            InputStream in = null;
            in = urlconn.getInputStream();
            String myRandomName = randomName() + ".jpg";
            File file1=null;
            if(System.getProperty("os.name").equals("Windows")){
            file1 = new File(strPath + "\\" + myRandomName);}
            else {
                file1 = new File(strPath+"/"+myRandomName);
            }
            if (!file1.exists()) {

                file1.createNewFile();
            }
            OutputStream writer = new FileOutputStream(file1);
            byte buffer[] = new byte[1];
            int c = in.read(buffer);
            while (c > 0) {
                writer.write(buffer, 0, c);
                c = in.read(buffer);
            }
            writer.flush();
            writer.close();
            in.close();
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }
    }


    @NotNull
    private static String randomName() {
        Random random = new Random();
        return (random.nextInt(200)) + (random.nextInt(100)) + (random.nextInt(1000)) + "";

    }
}





