package lv.rvt;
import java.io.BufferedReader;
import java.io.File;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import java.util.*;
import java.util.jar.Attributes.Name;

import javax.print.DocFlavor.STRING;

import lv.rvt.tools.Helper;

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

            

            System.out.println("Names: " + names);
            System.out.println("Authors: " + authors);
            System.out.println("Years: " + years);
            System.out.println("IDs: " + ID);

            
            
            
            /*String Name ="";
            String Author ="";
            String Years ="";
            String id ="";*/
            
            Scanner scanner = new Scanner(System.in);
            System.out.print("Name: ");
            String Name = scanner.nextLine();
            System.out.print("Author: ");
            String Author = scanner.nextLine();
            System.out.print("Years: ");
            String Years = scanner.nextLine();
            System.out.print("ID: ");
            String id = scanner.nextLine();
            
            String all = Name + ", " + Author ", " + Years + ", " + id; 
            
            BufferedWriter writer = Helper.getWriter(line, null);
           
            /*System.out.println("Names: " + Name);
            System.out.println("Authors: " + Author);
            System.out.println("Years: " + Years);
            System.out.println("IDs: " + id);*/
            
            
           
           
           
           
           
           
           
           
           
           
           
            
            
            
            reader.close();
}
}
