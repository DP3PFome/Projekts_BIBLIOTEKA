package lv.rvt.tools;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import lv.rvt.Book;

public class WorkWithFiles {

    // Jaunas grāmatas pievienošana
    public static void addBook(List<Book> bookList) {
        Scanner scanner = new Scanner(System.in);
        Colors Ui = new Colors();

        try (BufferedWriter writer = Helper.getWriter("data.csv", StandardOpenOption.APPEND)) {

            while (true) {
                System.out.print(Ui.PURPLE("Nosaukums: "));
                String name = scanner.nextLine();

                System.out.print(Ui.RED("Autors: "));
                String author = scanner.nextLine();

                System.out.print(Ui.GREEN("Gads: "));
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
                    System.out.println(Ui.RED("Nosaukums nedrīkst atkārtoties vai būt tukšs. Mēģini vēlreiz."));
                    continue;
                }

                // parbaude ID
                boolean duplicateId = false;
                for (Book book : bookList) {
                    if (book.getID().equals(id)) {
                        duplicateId = true;
                        break;
                    }
                }
                if (duplicateId || id.equals("0")|| id.isEmpty()) {
                    System.out.println(Ui.RED("ID nedrīkst atkārtoties vai būt 0. Mēģini vēlreiz."));
                    continue;
                }

                Book book = new Book(name, author, year, id);

                writer.newLine();
                writer.write(book.toCsvRow());

                System.out.println(Ui.GREEN("Dati veiksmīgi saglabāti failā."));
                break;
            }

        } catch (IOException e) {
            System.out.println(Ui.RED("Kļūda rakstot failā: ") + e.getMessage());
        }
    }

    // Grāmatu saraksta izvadīšana
    public static void print(List<Book> bookList) {
        Colors Ui = new Colors();

        System.out.printf("%-40s %-40s %-20s %-15s%n",
                Ui.PURPLE("Nosaukums"),
                Ui.RED("Autors"),
                Ui.GREEN("Gads"),
                Ui.BLUE("ID"));

        System.out.println(
                Ui.YELLOW("----------------------------------------------------------------------------------"));

        for (Book book : bookList) {
            System.out.printf("%-40s %-40s %-20s %-15s%n",
                    Ui.PURPLE(book.getNamae()),
                    Ui.RED(book.getAuthor()),
                    Ui.GREEN(book.getYear()),
                    Ui.BLUE(book.getID()));
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

        System.out.println(Ui.YELLOW("Grāmata ar ID: ") + Ui.BLUE(book.getID()) + Ui.YELLOW(" tika izdzēsta."));
        writer.close();
    }

    // Grāmatas meklēšana
    public static void Founding(List<Book> bookList) {
        Scanner scanner = new Scanner(System.in);
        Colors Ui = new Colors();

        System.out.print(Ui.CYAN("Ko vēlies meklēt? "));
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
            System.out.println(Ui.RED("Netika atrasts neviens rezultāts."));
        } else {
            print(bookList2);
        }
    }

    // Papildu funkcijas
    public static void otherFunction(List<Book> bookList) {
        Scanner scanner = new Scanner(System.in);
        Colors Ui = new Colors();

        System.out.println();
        System.out.println(Ui.YELLOW("0 - Atgriezties"));
        System.out.println(Ui.CYAN("1 - Grāmatu skaits"));
        System.out.println(Ui.GREEN("2 - Vidējais izdošanas gads"));
        System.out.print(Ui.PURPLE(": "));

        String commandNumber = scanner.nextLine();

        if (commandNumber.equals("0")) {
            return;
        } else if (commandNumber.equals("1")) {
            System.out.println(Ui.CYAN("Bibliotēkā ir: ") + Ui.GREEN(bookList.size() + " grāmatas"));
        } else if (commandNumber.equals("2")) {
            int vid = 0;
            for (Book book : bookList) {
                vid += Integer.parseInt(book.getYear());
            }
            vid = vid / bookList.size();
            System.out.println(Ui.GREEN("Vidējais izdošanas gads: ") + Ui.BLUE(String.valueOf(vid)));
        }
    }

    // Grāmatas iegāde
    public static void Bybook(boolean acauntStatus, List<Book> bookList) {
        Scanner scanner = new Scanner(System.in);
        Colors Ui = new Colors();

        System.out.print(Ui.CYAN("Kuru grāmatu vēlies iegādāties? "));
        String commandNumber = scanner.nextLine();

        if (acauntStatus = true) {
            Book foundBook = null;

            for (Book book : bookList) {
                if (book.getNamae().equals(commandNumber)) {
                    foundBook = book;
                    System.out.println(Ui.GREEN("Grāmata ") + Ui.PURPLE(commandNumber) + Ui.GREEN(" tika iegādāta."));
                    break;
                }
            }

            if (foundBook == null) {
                System.out.println(Ui.RED("Grāmata nav atrasta!"));
            }

        } else {
            System.out.println(Ui.RED("Lūdzu, piesakies savā kontā."));
        }
    }
}
