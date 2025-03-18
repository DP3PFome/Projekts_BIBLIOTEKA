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

             
                writer.newLine();
                writer.write(all);
                
           
                

                System.out.println("Data successfully saved to file.");
                break;
            }
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }


//----------------------------------------------------------------------------------------------------------


    public static void print(List<String> names,List<String> authors, List<String> years,List<String> idList){
        System.out.println("Name    Authors     Years       ID      ");
        System.out.println("---------------------------------------------------------");
            for(int i=0; i < names.size(); i++ ){
                System.out.print(names.get(i));
                System.out.print("  ");
                System.out.print(authors.get(i));
                System.out.print("  ");
                System.out.print(years.get(i));
                System.out.print("  ");
                System.out.print(idList.get(i));
                System.out.println();
                
            }
        
    }

    // -------------------------------------------------------------------------



    public static void delete(List<String> names,List<String> authors, List<String> years,List<String> idList){
        
    }

}
