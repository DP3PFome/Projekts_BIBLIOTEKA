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

/*
DATABASE: 
NAME    AUTHOR  YEAR    ID 
 
https://github.com/rvt-student-demo/java-intro-VadimsMalickis/commit/b3d869dadbe28296b466a32d016fdd546f473e9a
*/

public class Main {

    public static void main(String[] args) throws Exception {
        String acauntStatus = "";

        Scanner scanner = new Scanner(System.in);
        Colors Ui = new Colors();

        // Attīra ekrānu
        for (int i = 0; i < 50; ++i) {
            System.out.println();
        }

        while (true) {
            System.out.println();

            // Izvēlne
            System.out.println("0 - Exit the program");
            System.out.println("1 - " + Ui.BLUE("Check") + " data");
            System.out.println("2 - " + Ui.YELLOW("Sort by"));
            System.out.println("3 - " + Ui.PURPLE("Search"));
            System.out.println("4 - " + Ui.GRAY("Other function"));
            System.out.println("5 - " + Ui.CYAN("Enter in account"));
            System.out.println("6 - Buy book");
            System.out.println("Ad - admin functions");

            System.out.print(": ");
            String commandNumber = scanner.nextLine();

            // Iziet no programmas
            if (commandNumber.equals("0")) {
                break;
            }

            // Nolasa grāmatu datus no faila
            BufferedReader reader = Helper.getReader("data.csv");
            List<Book> books = new ArrayList<>();

            String line;
            reader.readLine(); // izlaiž virsrakstu rindu
            while ((line = reader.readLine()) != null) {
                if (!line.isEmpty()) {
                    String[] parts = line.split(",");
                    if (parts.length == 4) {
                        books.add(new Book(parts[0].trim(), parts[1].trim(), parts[2].trim(), parts[3].trim()));
                    }
                }
            }

            reader.close();

            // Nolasa lietotāju datus no faila
            BufferedReader readers = Helper.getReader("peson.csv");
            List<User> users = new ArrayList<>();

            String lines;
            readers.readLine(); // izlaiž virsrakstu rindu
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
                // Parāda visus datus
                WorkWithFiles.print(books);

            } else if (commandNumber.equals("2")) {
                // Kārtošana
                System.out.println("Write down what kind of sorting you want(" +
                        Ui.PURPLE("Name") + "," +
                        Ui.RED("Author") + "," +
                        Ui.GREEN("Year") + "," +
                        Ui.BLUE("ID") + ")if you enter something else, the output is standard");
                System.out.println(": ");
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

            } else if (commandNumber.equals("3")) {
                // Meklēšana
                WorkWithFiles.Founding(books);

            } else if (commandNumber.equals("4")) {
                // Citas funkcijas
                WorkWithFiles.otherFunction(books);

            } else if (commandNumber.equals("5")) {
                // Lietotāja pieslēgšanās vai izveide
                System.out.println("0 - Create account");
                System.out.println("1 - login in");
                String chuse = scanner.nextLine();

                if (chuse.equals("0")) {
                    WorkWithUser.addUser(users);
                } else if (chuse.equals("1")) {
                    acauntStatus = WorkWithUser.EnterAC(users);
                }

            } else if (commandNumber.equals("6")) {
                // Pirkšana
                WorkWithFiles.Bybook(acauntStatus, books);

            } else if (commandNumber.equals("Ad")) {
                // Admin funkcijas
                System.out.print("Enter password: ");
                String pasword = scanner.nextLine();

                if (pasword.equals("zxc")) {
                    AdminMain.main(args);
                } else {
                    System.out.println(Ui.RED("Password is incorrect"));
                }

            } else {
                System.out.println(Ui.RED("Unknown command"));
            }
        }
    }
}
