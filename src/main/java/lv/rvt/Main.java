package lv.rvt;
import java.io.BufferedReader;
import java.io.File;

import java.util.*;

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

        StringBuilder content = new StringBuilder();
        
        String line;
        
        while ((line = reader.readLine()) != null) {
            content.append(line);
            content.append(System.lineSeparator());
        }
    
        System.out.println(
            content.toString()
        );



    }





}
