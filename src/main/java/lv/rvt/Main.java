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
        for (int i = 0; i < 50; ++i) {
            System.out.println();
        }
        while (true) {
            System.out.println();

            System.out.println();

            System.out.println("0 - Exit the program");
            System.out.println("1 - " + Ui.BLUE("Check") + " data");
            System.out.println("2 - " + Ui.YELLOW("sorting"));
            System.out.println("3 - " + Ui.PURPLE("founding"));
            System.out.println("4 - " + Ui.GRAY("Other function"));
            System.out.println("5 - " + Ui.CYAN("Enter in acaunt"));
            System.out.println("6 - by book");// ????????????????????????????????????????????????????????????????
            System.out.println("Ad - admin functions");

            System.out.print(": ");
            String commandNumber = scanner.nextLine();

            if (commandNumber.equals("0")) {
                break;
            }

            BufferedReader reader = Helper.getReader("data.csv");

            List<Book> books = new ArrayList<>();

            String line;
            reader.readLine();
            while ((line = reader.readLine()) != null) {
                if (line != "") {
                    String[] parts = line.split(",");
                    if (parts.length == 4) {
                        
                        books.add(new Book(parts[0].trim(), parts[1].trim(), parts[2].trim(), parts[3].trim()));
                    }
                }
            }

            if (commandNumber.equals("1")) {
                WorkWithFiles.print(books);
            } else if (commandNumber.equals("2")) {
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
            } else if (commandNumber.equals("3")) { //Founding
                WorkWithFiles.Founding(books);
            } else if (commandNumber.equals("4")) { //otherFunction
                WorkWithFiles.otherFunction(books);
            }else if(commandNumber.equals("5")) {  // Enter in acaunt

            } else if (commandNumber.equals("Ad")) {
                System.out.print("Ievadit parole: ");
                String pasword = scanner.nextLine();
                if (pasword.equals("zxc")) {
                    AdminMain.main(args);

                }

            } else {
                System.out.println(Ui.RED("Unknown command"));
            }

            reader.close();
        }

    }
}
