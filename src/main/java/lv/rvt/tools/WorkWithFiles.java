package lv.rvt.tools;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.Scanner;

public class WorkWithFiles {

    public static void write(List<String> names, List<String> idList) {
        Scanner scanner = new Scanner(System.in);

        try (BufferedWriter writer = Helper.getWriter("data.csv", StandardOpenOption.APPEND)) {
            while (true) {
                System.out.print("Name: ");
                String name = scanner.nextLine();
                System.out.print("Author: ");
                String author = scanner.nextLine();
                System.out.print("Years: ");
                String years = scanner.nextLine();
                System.out.print("ID: ");
                String id = scanner.nextLine();

                
                if (names.contains(name)) {
                    System.out.println("Name can't be the same. Try again.");
                    continue;
                }
                
                if (idList.contains(id)) {
                    System.out.println("ID can't be the same. Try again.");
                    continue;
                }
                
                String all = name + ", " + author + ", " + years + ", " + id;

                // Запись в файл
                writer.newLine();
                writer.write(all);
                writer.flush();
                
                // Добавление нового name и id в списки
                names.add(name);
                idList.add(id);

                System.out.println("Data successfully saved to file.");
                break;
            }
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }
}
