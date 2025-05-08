package lv.rvt.tools;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import lv.rvt.Book;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class WorkWithFiles {

    // Jaunas grāmatas pievienošana
    public static void addBook(List<Book> bookList) {
        Scanner scanner = new Scanner(System.in);
        Colors Ui = new Colors();

        try (BufferedWriter writer = Helper.getWriter("data.csv", StandardOpenOption.APPEND)) {

            while (true) {
                System.out.print(Ui.PURPLE("Nmae: "));
                String name = scanner.nextLine();

                System.out.print(Ui.RED("Autor: "));
                String author = scanner.nextLine();

                System.out.print(Ui.GREEN("Year: "));
                String year = scanner.nextLine();

                System.out.print(Ui.BLUE("ID: "));
                String id = scanner.nextLine();
                // parbaude nosaukums

                boolean duplicateName = false;
                for (Book book : bookList) {
                    if (book.getNamae().equals(name)) {
                        duplicateName = true;
                        break;
                    }
                }
                if (duplicateName || name.isEmpty()) {
                    System.out.println(Ui.RED("Name cannot be repeated or empty. Please try again."));
                    continue;
                }

                // parbaude ID
                if (id.matches("\\d+")) {
                    
                    boolean duplicateId = false;
                    for (Book book : bookList) {
                        if (book.getID().equals(id)) {
                            duplicateId = true;
                            break;
                        }
                    }
                    if (duplicateId || id.equals("0") || id.isEmpty()) {
                        System.out.println(Ui.RED("ID cannot be repeated or be 0. Try again."));
                        continue;
                    }
                } else {
                    System.out.println("The ID must consist of numbers only.");
                    continue;
                }
// parbaude Gads
                if (year.matches("\\d+")) {
                    Book book = new Book(name, author, year, id);
    
                    writer.newLine();
                    writer.write(book.toCsvRow());
    
                    System.out.println(Ui.GREEN("Data successfully saved to file."));
                    break;
                }else{
                    System.out.println("The year must consist of numbers only.");
                    continue;

                }
            }

        } catch (IOException e) {
            System.out.println(Ui.RED("Error writing to file: ") + e.getMessage());
        }
    }

    // Grāmatu saraksta izvadīšana
    public static void print(List<Book> bookList) {
        Colors Ui = new Colors();

        System.out.printf("%-4s %-40s %-4s %-40s %-4s %-20s %-4s %-20s %-4s%n",
        Ui.YELLOW("|"),
                Ui.PURPLE("Nmae"),
                Ui.YELLOW("|"),
                Ui.RED("Author"),
                Ui.YELLOW("|"),
                Ui.GREEN("Year"),
                Ui.YELLOW("|"),
                Ui.BLUE("ID"),
                Ui.YELLOW("|"));

        System.out.println(
                Ui.YELLOW("|---------------------------------|---------------------------------|-------------|-------------|"));

        for (Book book : bookList) {
            System.out.printf("%-4s %-40s %-4s %-40s %-4s %-20s %-4s %-20s %-4s%n",
            Ui.YELLOW("|"),
                    Ui.PURPLE(book.getNamae()),
                    Ui.YELLOW("|"),
                    Ui.RED(book.getAuthor()),
                    Ui.YELLOW("|"),
                    Ui.GREEN(book.getYear()),
                    Ui.YELLOW("|"),
                    Ui.BLUE(book.getID()),
                    Ui.YELLOW("|"));
        }
    }

    // Grāmatas dzēšana
    public static void delete(Book book, List<Book> bookList) throws Exception {
        Colors Ui = new Colors();
        bookList.removeIf(p -> p.equals(book));

        BufferedWriter writer = Helper.getWriter("data.csv", StandardOpenOption.TRUNCATE_EXISTING);
        writer.write("NAME, AUTHOR, YEAR, ID ");
        writer.newLine();

        for (Book p : bookList) {
            writer.write(p.toCsvRow());
            writer.newLine();
        }

        System.out.println(Ui.YELLOW("Book with ID: ") + Ui.BLUE(book.getID()) + Ui.YELLOW(" was deleted."));
        writer.close();
    }

    // Grāmatas meklēšana
    public static void Founding(List<Book> bookList) {
        Scanner scanner = new Scanner(System.in);
        Colors Ui = new Colors();

        System.out.print(Ui.CYAN("What do you want to search for? "));
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
            System.out.println(Ui.RED("No results were found."));
        } else {
            print(bookList2);
        }
    }

    // Papildu funkcijas
    public static void otherFunction(List<Book> bookList) {
        Scanner scanner = new Scanner(System.in);
        Colors Ui = new Colors();

        System.out.println();
        System.out.println(Ui.YELLOW("0 - Return"));
        System.out.println(Ui.CYAN("1 - Number of books"));
        System.out.println(Ui.GREEN("2 - Average year of publication"));
        System.out.print(Ui.PURPLE(": "));

        String commandNumber = scanner.nextLine();

        if (commandNumber.equals("0")) {
            return;
        } else if (commandNumber.equals("1")) {
            System.out.println(Ui.CYAN("The library has: ") + Ui.GREEN(bookList.size() + " grāmatas"));
        } else if (commandNumber.equals("2")) {
            int vid = 0;
            for (Book book : bookList) {
                vid += Integer.parseInt(book.getYear());
            }
            vid = vid / bookList.size();
            System.out.println(Ui.GREEN("Average year of publication: ") + Ui.BLUE(String.valueOf(vid)));
        }
    }

    // Grāmatas iegāde
    // Grāmatas iegāde
    public static void Bybook(String acauntStatus, List<Book> bookList) {

        Scanner scanner = new Scanner(System.in);
        Colors Ui = new Colors();

        System.out.print(Ui.CYAN("Which book do you want to buy? "));
        String commandNumber = scanner.nextLine();

        if (!acauntStatus.isEmpty() && acauntStatus != null) {
            Book foundBook = null;

            for (Book book : bookList) {
                if (book.getNamae().equals(commandNumber)) {
                    foundBook = book;
                    System.out.println(Ui.GREEN("Book ") + Ui.PURPLE(commandNumber) + Ui.GREEN(" was purchased."));

                    // Saglabājam vēsturi
                    try (BufferedWriter writer = Helper.getWriter("history.csv", StandardOpenOption.APPEND)) {
                        LocalDateTime now = LocalDateTime.now();
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                        String formattedNow = now.format(formatter);

                        // Šeit izveidojam ierakstu priekš vēstures
                        BookBuy bookBuy = new BookBuy(acauntStatus, formattedNow, commandNumber);
                        System.out.println(formattedNow);
                        writer.newLine();
                        writer.write(bookBuy.toCsv());

                    } catch (IOException e) {
                        System.out.println(Ui.RED("Error saving history: ") + e.getMessage());
                    }

                    break;
                }
            }

            if (foundBook == null) {
                System.out.println(Ui.RED("Book not found!"));
            }

        } else {
            System.out.println(Ui.RED("Please log in to your account."));
        }
    }
// Vesture pirksanu printesana
    public static void PrintHistory(List<BookBuy> PrintHistory) {

        Colors Ui = new Colors();

        System.out.printf("%-40s %-40s %-20s%n",
                Ui.PURPLE("User"),
                Ui.RED("Date"),
                Ui.GREEN("Book name"));

        System.out.println(
                Ui.YELLOW("----------------------------------------------------"));

        for (BookBuy book : PrintHistory) {
            System.out.printf("%-40s %-40s %-20s%n",
                    Ui.PURPLE(book.getNamae()),
                    Ui.RED(book.getdate()),
                    Ui.GREEN(book.getBookName()));
        }
    }

}
