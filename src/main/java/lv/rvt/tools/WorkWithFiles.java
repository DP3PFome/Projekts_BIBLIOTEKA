package lv.rvt.tools;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import lv.rvt.Book;

public class WorkWithFiles {

    public static void addBook(List<Book> bookList) {
        Scanner scanner = new Scanner(System.in);
        Colors Ui = new Colors();
        try (BufferedWriter writer = Helper.getWriter("data.csv", StandardOpenOption.APPEND)) {
            while (true) {
                System.out.print(Ui.PURPLE("Name: "));
                String name = scanner.nextLine();
                System.out.print(Ui.RED("Author: "));
                String author = scanner.nextLine();
                System.out.print(Ui.GREEN("Years: "));
                String year = scanner.nextLine();
                System.out.print(Ui.BLUE("ID: "));
                String id = scanner.nextLine();

                if (bookList.contains(name)) {
                    System.out.println("Name can't be the same. Try again.");
                    continue;
                }

                if (bookList.contains(id)) {
                    System.out.println("ID can't be the same. Try again.");
                    continue;
                }

                Book book = new Book(name, author, year, id);

                writer.newLine();
                writer.write(book.toCsvRow());

                System.out.println("Data successfully saved to file.");
                break;
            }
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    // ----------------------------------------------------------------------------------------------------------

    public static void print(List<Book> bookList) {
        Colors Ui = new Colors();//
        System.out.println(Ui.PURPLE("Name") + "    " + Ui.RED("Authors") + "     " + Ui.GREEN("Years") + "       "
                + Ui.BLUE("ID")
                + "      ");
        System.out.println(Ui.YELLOW("---------------------------------------------------------"));
        for (int i = 0; i < bookList.size(); i++) {
            System.out.print(Ui.PURPLE(bookList.get(i).getNamae()));
            System.out.print("  ");
            System.out.print(Ui.RED(bookList.get(i).getAuthor()));
            System.out.print("  ");
            System.out.print(Ui.GREEN(bookList.get(i).getYear()));
            System.out.print("  ");
            System.out.print(Ui.BLUE(bookList.get(i).getID()));
            System.out.println();

        }

    }

    // -------------------------------------------------------------------------

    public static void delete(Book book, List<Book> bookList) throws Exception {

        bookList.removeIf(p -> p.equals(book));

        // StandardOpenOption.TRUNCATE_EXISTING - izdzēš visu saturu
        BufferedWriter writer = Helper.getWriter("data.csv", StandardOpenOption.TRUNCATE_EXISTING);

        writer.write("NAME, AUTHOR, YEAR, ID "); // Header row
        writer.newLine();

        // Pārrakstam failu ar jaunajiem datiem
        for (Book p : bookList) {
            writer.write(p.toCsvRow());
            writer.newLine();
        }
        System.out.println("Book with ID: " + book.getID() + " was removed");
        writer.close();
    }

    // -----------------------------------------------------------------------------------
    public static void Founding(List<Book> bookList) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("what you find");

        List<Book> bookList2 = new ArrayList<>();

        String found = scanner.nextLine();
        if (bookList.contains(found)) {
            for (int i = 0; i < bookList.size(); i++) {
                if (bookList.get(i).getID().contains(found)) {
                    bookList2.add(bookList.get(i));

                }

            }
            print(bookList2);
        } else {
            System.out.println("Dont Found");

        }
    }

}
