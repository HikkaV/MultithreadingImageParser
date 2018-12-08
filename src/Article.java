public class Article {
    private String day;
    private String name;

    public Article(String day, String name) {
        this.day = day;
        this.name = name;
    }

    @Override
    public String toString() {
        return day +" " +
                 name ;
    }

}
