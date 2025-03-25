package lv.rvt.tools;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.Scanner;

import lv.rvt.Book;

public class WorkWithFiles {

    public static final String RESET = "\u001B[0m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE = "\u001B[34m";
    public static final String PURPLE = "\u001B[35m";
    public static final String CYAN = "\u001B[36m";

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
                
                Book book = new Book(id, name, author, year);

             
                writer.newLine();
                writer.write(book.toCsvRow());
                
           
                

                System.out.println("Data successfully saved to file.");
                break;
            }
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }


//----------------------------------------------------------------------------------------------------------


    public static void print(List<String> names,List<String> authors, List<String> years,List<String> idList){
        System.out.println(PURPLE+" Name"+RESET+"    "+RED+"Authors"+RESET+"     "+GREEN+"Years"+RESET+"       "+BLUE+"ID"+RESET+"      ");
        System.out.println("---------------------------------------------------------");
            for(int i=0; i < names.size(); i++ ){
                System.out.print(PURPLE+names.get(i)+RESET);
                System.out.print(RED +"  "+RESET);
                System.out.print(RED+authors.get(i)+RESET);
                System.out.print("  ");
                System.out.print(GREEN+years.get(i)+RESET);
                System.out.print( "  ");
                System.out.print(BLUE+idList.get(i)+RESET);
                System.out.println();
                
            }
        
    }

    // -------------------------------------------------------------------------



    public static void delete(int lineToReplace) {
        System.out.println(lineToReplace);
        String filePath = "data.csv"; 
        String newContent = "";
    
        try {
            List<String> lines = Files.readAllLines(Paths.get(filePath));
           
                lineToReplace = lineToReplace + 1;
           
    
            if (lineToReplace >= 0 && lineToReplace < lines.size()) {
                lines.set(lineToReplace, newContent); 
                Files.write(Paths.get(filePath), lines, StandardOpenOption.TRUNCATE_EXISTING);
                System.out.println("Data deleted successfully.");
            } else {
                System.out.println("Error: Line not found.");
            }
        } catch (IOException e) {
            System.out.println("Error reading or writing to file: " + e.getMessage());
        }
    }


}
