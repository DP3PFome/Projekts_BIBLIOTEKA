package lv.rvt;

public class Book {
    private String id;
    private String name;
    private String author;
    private String year;

    public Book(String name, String author, String year,String id) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.year = year;
    }

    public String toCsvRow() {
        return name + ", " + author + ", " + year + ", " + id;

    }

    public String getID() {
        return id;
    }

    public String getNamae() {
        return name;
    }

    public String getAuthor() {
        return author;
    }

    public String getYear() {
        return year;
    }
}
