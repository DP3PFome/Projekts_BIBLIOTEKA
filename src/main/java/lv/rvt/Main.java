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

        StringBuilder content = new StringBuilder();

        String line;
        //new NAME[];
        //
        while ((line = reader.readLine()) != null) {
            content.append(line);
            content.append(System.lineSeparator());
        }
        String x =content.toString();
        
        System.out.println(x);



    }





}
