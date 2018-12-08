import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class test {
    public static void main(String[] args) throws IOException {
        do {
            List<Article> data = new ArrayList<>();
            System.out.println("Введите город в котором вы хотите узнать погоду");
            //String city = new Scanner(System.in).nextLine();
            // String city = "винница";


            //Document doc = Jsoup.connect("https://sinoptik.ua/погода-"+city).get(); // соединение с сервером
            Document doc = Jsoup.connect("https://sinoptik.ua/погода-" + Debag.userInputs()).get(); // соединение с сервером
            System.out.println(doc.title()); // заголовок страницы

            //Elements elements = doc.getElementsByAttributeValue("class", "main loaded"); //анализ считаного документа
            Elements elements = doc.getElementsByAttributeValue("class", "tabs"); //анализ считаного документа
            elements.forEach(element -> {
                        // Element a7 = element.child(0);
                        // String title = element.attr("span");
                    /*String who = element.child(4).text();
                    String day = element.child(1).text()+" " + element.child(2).text() + " "+element.child(0).text();
                    data.add(new Article(day, who));
                    */
                        for (int i = 1; i <= 14; i++) {
                            String who = element.child(i).text();
                            String day = element.child(2).text() + " " + element.child(0).text();
                            data.add(new Article(day, who));
                        }
                    }
            );
            data.forEach(System.out::println);

            // Elements elements = doc.select("img");
            //elements.forEach(o -> System.out.println(o.attr("src")));

//    Elements elements1 = doc.getElementsByAttributeValueContaining("class","day-link");
            //  List<Element> elms = doc.select("main loaded");
        } while (true);

    }
}
