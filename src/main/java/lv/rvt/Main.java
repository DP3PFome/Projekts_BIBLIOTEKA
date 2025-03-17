package lv.rvt;
import java.io.BufferedReader;
import java.io.File;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.StandardOpenOption;
import java.util.*;
import java.util.jar.Attributes.Name;

import javax.print.DocFlavor.STRING;

import lv.rvt.tools.Helper;
import lv.rvt.tools.WorkWithFiles;

/*
DATABASE: 
NAME    AUTHOR  YEAR    ID 
  
 


 */






public class Main 
{
    public static void main( String[] args ) throws Exception
    {
        
            BufferedReader reader = Helper.getReader("data.csv");

            List<String> names = new ArrayList<>();
            List<String> authors = new ArrayList<>();
            List<String> years = new ArrayList<>();
            List<String> ID = new ArrayList<>();

            String line;
            reader.readLine(); 
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 4) {
                    names.add(parts[0].trim());
                    authors.add(parts[1].trim());
                    years.add(parts[2].trim());
                    ID.add(parts[3].trim());
                }
            }

    /*       
System.out.println("Name    Authors     Years       ID      ");
System.out.println("---------------------------------------------------------");
            for(int i=0; i < names.size(); i++ ){
                System.out.print(names[i]);
            }
        
            System.out.println("Names: " + names);
            System.out.println("Authors: " + authors);
            System.out.println("Years: " + years);
            System.out.println("IDs: " + ID);


*/
            WorkWithFiles.write(names, ID);
            
            
/*
            
            Scanner scanner = new Scanner(System.in);
            System.out.print("Name: ");
            String Name = scanner.nextLine();
            System.out.print("Author: ");
            String Author = scanner.nextLine();
            System.out.print("Years: ");
            String Years = scanner.nextLine();
            System.out.print("ID: ");
            String id = scanner.nextLine();
            
            String all = Name + ", " + Author +", " + Years + ", " + id; 
            
            BufferedWriter writer = Helper.getWriter("data.csv", StandardOpenOption.APPEND);
           
      
            writer.newLine();
            writer.write(all);
            writer.close();
           
           */
           
           
           
           
           
           
            
            
            
            
            
            reader.close();
}
}
