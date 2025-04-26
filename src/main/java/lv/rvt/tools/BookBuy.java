package lv.rvt.tools;

public class BookBuy {
    private String name;
    private String date;
    private String BookName;

    public BookBuy(String name, String date, String BookName ) {
        this.name = name;
        this.date = date;
        this.BookName = BookName;
    }
    
    public String toCsv() {
        return name + ", " + date + ", " + BookName;

    }

    
    public String getNamae() {
        return name;
    }

    public String getdate() {
        return date;
    }

    public String getBookName() {
        return BookName;
    }
}
