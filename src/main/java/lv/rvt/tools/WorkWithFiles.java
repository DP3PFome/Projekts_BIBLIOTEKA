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

    public static void addBook() {
        Scanner scanner = new Scanner(System.in);

        try (BufferedWriter writer = Helper.getWriter("data.csv", StandardOpenOption.APPEND)) {
            while (true) {
                System.out.print("Name: ");
                String name = scanner.nextLine();
                System.out.print("Author: ");
                String author = scanner.nextLine();
                System.out.print("Years: ");
                String year = scanner.nextLine();
                System.out.print("ID: ");
                String id = scanner.nextLine();

                if (name.contains(name)) {
                    System.out.println("Name can't be the same. Try again.");
                    continue;
                }

                if (id.contains(id)) {
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
            System.out.print(Ui.PURPLE(bookList.get(i).getNmae()));
            System.out.print("  ");
            System.out.print(Ui.RED(bookList.get(i).getAuthor()));
            System.out.print("  ");
            System.out.print(Ui.GREEN(bookList.get(i).getYear()));
            System.out.print("  ");
            System.out.print(Ui.BLUE(bookList.get(i).getID()));
            System.out.println();
            

        }
        

    }

    public static ArrayList<Book> getBookList() throws Exception {
        BufferedReader reader = Helper.getReader("data.csv");

        ArrayList<Book> personList = new ArrayList<>();
        String line;

        reader.readLine(); // Ignorējam titul rindiņu
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(",");

            String name = parts[0];
            String author = String.valueOf(parts[1]);
            String year = String.valueOf(parts[2]);
            String id = String.valueOf(parts[3]);

            Book person = new Book(name, author, year, id);
            personList.add(person);
        }
        return personList;
    }

    // -------------------------------------------------------------------------

    public static void delete(Book book) throws Exception {
        ArrayList<Book> bookList = getBookList();
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
        writer.close();
    }
}
