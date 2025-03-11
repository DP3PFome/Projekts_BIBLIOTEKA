package lv.rvt;
import java.io.BufferedReader;
import java.io.File;

import java.util.*;

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


    




            reader.close();
}
}
