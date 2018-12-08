import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Debag {



    public static String userInputs() throws IOException {
        Scanner sc = new Scanner(System.in);
        boolean run = true;
        String input = null;
        while (run) {
            //System.out.print("Введите число: ");
            input = sc.nextLine();
            if (input.equals("exit"))
                System.exit(-1);
            try {
                Document doc = Jsoup.connect("https://sinoptik.ua/погода-"+input).get();
                run = false;
            } catch (IOException e) {
                System.out.println("Ошибка: " + e.getLocalizedMessage());
                Debag.maybe(input);
            }
        }
        return input;

    }

    public static void maybe(String input) throws IOException {
        List<String> data = new ArrayList<>();
        Document doc = Jsoup.connect("https://www.google.com.ua/search?q=" + input).get(); // соединение с сервером
        System.out.println(doc.title()); // заголовок страницы

        Elements elements = doc.getElementsByAttributeValue("class", "med"); //анализ считаного документа
        elements.forEach(element -> {
                    String who = element.child(0).text();
                    data.add(who);
                }
        );
        data.forEach(System.out::println);
    }
}





