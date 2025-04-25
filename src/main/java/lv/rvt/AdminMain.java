package lv.rvt;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

import lv.rvt.tools.Colors;
import lv.rvt.tools.Helper;
import lv.rvt.tools.User;
import lv.rvt.tools.WorkWithFiles;
import lv.rvt.tools.WorkWithUser;

public class AdminMain {
    public static void main(String[] args) throws Exception {

        Scanner scanner = new Scanner(System.in);
        Colors Ui = new Colors();

        // Attīra ekrānu
        for (int i = 0; i < 50; ++i) {
            System.out.println();
        }

        while (true) {
            System.out.println();

            // Admin izvēlne
            System.out.println("0 - Exit the admin function");
            System.out.println("1 - " + Ui.BLUE("Check") + " data");
            System.out.println("2 - " + Ui.GREEN("Write ") + "data");
            System.out.println("3 - " + Ui.RED("delete") + " data");
            System.out.println("4 - " + Ui.YELLOW("sorting"));
            System.out.println("5 - " + Ui.PURPLE("founding"));
            System.out.println("6 - " + Ui.GRAY("Other function"));
            System.out.println("7 - Check person");

            System.out.print(": ");
            String commandNumber = scanner.nextLine();

            if (commandNumber.equals("0")) {
                break; // Iziet no admin režīma
            }

            // Nolasa grāmatas no faila
            BufferedReader reader = Helper.getReader("data.csv");
            List<Book> books = new ArrayList<>();

            String line;
            reader.readLine(); // izlaiž virsrakstu
            while ((line = reader.readLine()) != null) {
                if (!line.isEmpty()) {
                    String[] parts = line.split(",");
                    if (parts.length == 4) {
                        books.add(new Book(parts[0].trim(), parts[1].trim(), parts[2].trim(), parts[3].trim()));
                    }
                }
            }

            reader.close();

            // Nolasa lietotājus no faila
            BufferedReader readers = Helper.getReader("peson.csv");
            List<User> users = new ArrayList<>();

            String lines;
            readers.readLine(); // izlaiž virsrakstu
            while ((lines = readers.readLine()) != null) {
                if (!lines.isEmpty()) {
                    String[] parts = lines.split(",");
                    if (parts.length == 2) {
                        users.add(new User(parts[0].trim(), parts[1].trim()));
                    }
                }
            }

            readers.close();

            // Komandu apstrāde
            if (commandNumber.equals("1")) {
                // Parāda grāmatas
                WorkWithFiles.print(books);

            } else if (commandNumber.equals("2")) {
                // Pievieno jaunu grāmatu
                WorkWithFiles.addBook(books);

            } else if (commandNumber.equals("3")) {
                // Dzēš grāmatu pēc ID
                WorkWithFiles.print(books);
                System.out.println("If wont exit write 0");
                System.out.print("Enter book ID which must be deleted: ");
                String bookId = scanner.nextLine();

                for (int i = 0; i < books.size(); i++) {
                    if (books.get(i).getID().equals(bookId)) {
                        WorkWithFiles.delete(books.get(i), books);
                        break;
                    }
                }

            } else if (commandNumber.equals("4")) {
                // Kārtošana
                System.out.print("Write down what kind of sorting you want(" +
                        Ui.PURPLE("Name") + "," +
                        Ui.RED("Author") + "," +
                        Ui.GREEN("Year") + "," +
                        Ui.BLUE("ID") + "): ");

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
                // Meklēšana
                WorkWithFiles.Founding(books);

            } else if (commandNumber.equals("6")) {
                // Citas funkcijas
                WorkWithFiles.otherFunction(books);

            } else if (commandNumber.equals("7")) {
                // Parāda personas datus
                WorkWithUser.print(users);

            } else {
                // Nepareiza komanda
                System.out.println(Ui.RED("Unknown command"));
            }
        }
    }
}
