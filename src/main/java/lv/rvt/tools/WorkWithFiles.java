package lv.rvt.tools;

import java.io.BufferedWriter;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.Scanner;
import java.util.jar.Attributes.Name;

public class WorkWithFiles {



public static BufferedWriter write(List<String> names,List<String> idList){
        Scanner scanner = new Scanner(System.in);
        String all="";
        while (true) {
            System.out.print("Name: ");
            String Name = scanner.nextLine();
            System.out.print("Author: ");
            String Author = scanner.nextLine();
            System.out.print("Years: ");
            String Years = scanner.nextLine();
            System.out.print("ID: ");
            String id = scanner.nextLine();
            
            for(int i = 0; i< names.size();i++) {
                if (names.get(i).equals(Name)) {
                    System.out.println("Name cant be same");
                    continue;
                }   
                if (idList.get(i).equals(id)){ // FIx
                    System.out.println("ID cant be same");
                    continue;
                
                }   
                all = Name + ", " + Author +", " + Years + ", " + id; 
                break;
            }
            
        }        
            BufferedWriter writer = Helper.getWriter("data.csv", StandardOpenOption.APPEND);
            
            writer.newLine();
            writer.write(all);          

         
            writer.close();
        }
    }