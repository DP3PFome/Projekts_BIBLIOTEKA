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
    // pievinošana jaunu gramatu
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

                if (bookList.contains(id) || id.equals("0")) {
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
    // printšana
    public static void print(List<Book> bookList) {
        Colors Ui = new Colors();//
        System.out.printf("%-40s %-40s %-20s %-15s%n",
                Ui.PURPLE("Name"),
                Ui.RED("Authors"),
                Ui.GREEN("Years"),
                Ui.BLUE("ID"));
        System.out.println(
                Ui.YELLOW("----------------------------------------------------------------------------------"));
        for (int i = 0; i < bookList.size(); i++) {
            System.out.printf("%-40s %-40s %-20s %-15s%n",
                    Ui.PURPLE(bookList.get(i).getNamae()),
                    Ui.RED(bookList.get(i).getAuthor()),
                    Ui.GREEN(bookList.get(i).getYear()),
                    Ui.BLUE(bookList.get(i).getID()));

        }

    }

    // -------------------------------------------------------------------------
    // delite metode
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
    // atbilde par meklešanu
    public static void Founding(List<Book> bookList) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("what you find");

        List<Book> bookList2 = new ArrayList<>();

        String found = scanner.nextLine();
        for (Book book : bookList) {
            if (book.getID().contains(found) ||
                    book.getNamae().contains(found) ||
                    book.getAuthor().contains(found) ||
                    book.getYear().contains(found)) {
                bookList2.add(book);
            }
        }
        if (bookList2.isEmpty()) {
            System.out.println("Dont Found");
        } else {
            print(bookList2);
        }

    }

    // ------------------------------------------------------------------------------------
    // papild funkcijas
    public static void otherFunction(List<Book> bookList) {
        Scanner scanner = new Scanner(System.in);
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println("0-go back");
        System.out.println("1 - How match books");
        System.out.println("2 - average year of production");

        System.out.print(": ");
        String commandNumber = scanner.nextLine();

        if (commandNumber.equals("0")) {

        } else if (commandNumber.equals("1")) {
            System.out.println("In Library: " + bookList.size() + " books");

        } else if (commandNumber.equals("2")) {

            int vid = 0;
            for (int i = 0; i < bookList.size(); i++) {
                vid = vid + Integer.parseInt(bookList.get(i).getYear());
            }
            vid = vid / bookList.size();
            System.out.println("average year of production: " + vid);

        }

    }

    public static void Bybook(User acauntStatus) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Witch book wont buy");
        System.out.print(": ");
        String commandNumber = scanner.nextLine();
        if (acauntStatus.getNick().equals("")) {
            System.out.println("Please enter in acaunt");
        } else if (acauntStatus.getPasword().equals("")) {
            System.out.println("Please enter in acaunt");
        } else {
            System.out.println("Your book " + commandNumber + " has Buy");
        }

    }

}
