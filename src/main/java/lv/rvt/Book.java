package lv.rvt;

public class Book {
    private String id;
    private String name;
    private String author;
    private String year;

    public Book(String id, String name, String author, String year) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.year = year;
    }
    public String toCsvRow() {
        return name + ", " + author + ", " + year + ", " + id;

    }
}
