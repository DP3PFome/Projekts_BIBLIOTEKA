package lv.rvt;
import java.io.BufferedReader;
import java.util.*;
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
        Scanner scanner = new Scanner(System.in);
        
        while(true){
            System.out.println();
            System.out.println();
            
            System.out.println("If you wont get out program write  0");
            System.out.println("If you wont check data write  1");
            System.out.println("If you wont write in data write  2");
            System.out.println("If you wont delete in data write 3");
            
            System.out.print(": ");
            int x = scanner.nextInt();
            
            if(x == 0){
                break;
            }
            
            BufferedReader reader = Helper.getReader("data.csv");
            
            List<String> names = new ArrayList<>();
            List<String> authors = new ArrayList<>();
            List<String> years = new ArrayList<>();
            List<String> ID = new ArrayList<>();

            String line;
            reader.readLine(); 
            while ((line = reader.readLine()) !=null) {
                if (line != ""){
                String[] parts = line.split(",");
                if (parts.length == 4) {
                    names.add(parts[0].trim());
                    authors.add(parts[1].trim());
                    years.add(parts[2].trim());
                    ID.add(parts[3].trim());
                }
            }
            }

            if(x == 1){
                WorkWithFiles.print(names, authors, years, ID);
            }else if(x == 2){
                WorkWithFiles.write(names, ID);
            }
            
            reader.close();
        }       
            
    }
}
