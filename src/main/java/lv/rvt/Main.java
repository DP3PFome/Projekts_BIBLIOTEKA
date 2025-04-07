package lv.rvt;

import java.io.BufferedReader;
import java.io.File;
import java.time.Year;
import java.util.*;

import lv.rvt.tools.Colors;
import lv.rvt.tools.Helper;
import lv.rvt.tools.WorkWithFiles;

/*
DATABASE: 
NAME    AUTHOR  YEAR    ID 
 
https://github.com/rvt-student-demo/java-intro-VadimsMalickis/commit/b3d869dadbe28296b466a32d016fdd546f473e9a
 */

public class Main {

    public static void main(String[] args) throws Exception {

        Scanner scanner = new Scanner(System.in);
        Colors Ui = new Colors();

        while (true) {
            System.out.println();
            System.out.println();

            System.out.println("0 - Exit the program");
            System.out.println("1 - Check data");
            System.out.println("2 - Write data");
            System.out.println("3 - " + Ui.RED("delete") + " data");
            System.out.println("4 - sorting");
            System.out.println("5 - founding");

            System.out.print(": ");
            String commandNumber = scanner.nextLine(); // "3"

            if (commandNumber.equals("0")) {
                break;
            }

            BufferedReader reader = Helper.getReader("data.csv");

            List<String> names = new ArrayList<>();
            List<String> authors = new ArrayList<>();
            List<String> years = new ArrayList<>();
            List<String> ID = new ArrayList<>();
            List<Book> books = new ArrayList<>();

            String line;
            reader.readLine();
            while ((line = reader.readLine()) != null) {
                if (line != "") {
                    String[] parts = line.split(",");
                    if (parts.length == 4) {
                        names.add(parts[0].trim());
                        authors.add(parts[1].trim());
                        years.add(parts[2].trim());
                        ID.add(parts[3].trim());
                        books.add(new Book(parts[0].trim(), parts[1].trim(), parts[2].trim(), parts[3].trim()));
                    }
                }
            }

            if (commandNumber.equals("1")) {
                WorkWithFiles.print(books);
            } else if (commandNumber.equals("2")) {
                WorkWithFiles.addBook(books);
            } else if (commandNumber.equals("3")) {
                WorkWithFiles.print(books);
                System.out.print("Enter book ID which must be deleted: ");
                String bookId = scanner.nextLine();
                for (int i = 0; i < books.size(); i++) {
                    if (books.get(i).getID().equals(bookId)) {
                        WorkWithFiles.delete(books.get(i), books);
                        break;
                    }
                }
            } else if (commandNumber.equals("4")) {
                System.out.print("Write down what kind of sorting you want(" + Ui.PURPLE("Name") + ","
                        + Ui.RED("Author") + "," + Ui.GREEN("Year") + "," + Ui.BLUE("ID") + "): ");
                String typesort = scanner.nextLine();
                if (typesort.equals("ID")) {
                    books.sort(Comparator.comparing(Book::getID));
                } else if (typesort.equals("Name")) {
                    books.sort(Comparator.comparing(Book::getNamae));
                } else if (typesort.equals("Year")) {
                    books.sort(Comparator.comparing(Book::getYear));
                } else if (typesort.equals("Author")) {
                    books.sort(Comparator.comparing(Book::getAuthor));
                }
                WorkWithFiles.print(books);
            } else if (commandNumber.equals("5")) {
                WorkWithFiles.Founding(books);
            } else {
                System.out.println(Ui.RED("Unknown command"));
            }

            reader.close();
        }

    }
}
