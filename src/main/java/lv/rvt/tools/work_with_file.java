package lv.rvt.tools;

import java.io.BufferedWriter;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.Scanner;
import java.util.jar.Attributes.Name;

public class work_with_file {



public static BufferedWriter write(List<String> names,List<String> authors,List<String> years,List<String> ID){
        Scanner scanner = new Scanner(System.in);
        for (true) {
            System.out.print("Name: ");
            String Name = scanner.nextLine();
            System.out.print("Author: ");
            String Author = scanner.nextLine();
            System.out.print("Years: ");
            String Years = scanner.nextLine();
            System.out.print("ID: ");
            String id = scanner.nextLine();
            
            for(int i = 0; i<len(names);i++){
                if (names[i]==Name){
                    System.out.println("Name cant be same");
                    continue;
            }   
            for(int i = 0; i<len(ID);i++){
                if (ID[i]==id){
                    System.out.println("ID cant be same");
                    continue;
            }   
            break;
            }
            
        }        
        String all = Name + ", " + Author +", " + Years + ", " + id; 
            BufferedWriter writer = Helper.getWriter("data.csv", StandardOpenOption.APPEND);
            writer.newLine();
            writer.write(all);          

         
            writer.close();
}
}

}