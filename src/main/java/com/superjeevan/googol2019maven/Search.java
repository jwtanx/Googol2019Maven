
package com.superjeevan.googol2019maven;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Search {

    public Search(String cmd) {
        
        Map<String,ArrayList<Integer>> lexicon = new HashMap<String, ArrayList<Integer>>();
        String query="";
        ArrayList<Integer> result = new ArrayList<Integer>();
        String[] ref;
        Scanner sc = new Scanner (System.in);
        ref = getLinks(1000, cmd);
        
        //Update hashMap and top-1m.csv file directory
        try{
            File hashMap = new File ("hashmap.csv");
            ObjectInputStream input = new ObjectInputStream (new FileInputStream (hashMap));
            
            lexicon = (HashMap) input.readObject();
            
            while(true){
                
                query = cmd;
                if (query.equals("end")) break;
                    
                String[] temp = query.split(" ");
                System.out.printf("Matches for \"%s\" found at: \n", query.toLowerCase());
                
                for (String segment : temp){
                    result = lexicon.get(segment.toLowerCase());

                    if (result != null){
                        for(int j : result)
                            System.out.printf("| %s\n", ref[j]);
                    }
                    else
                        System.out.println("No matches found");
                }
                break;
            }

            input.close();
    
        }catch (IOException e){
            //e.printStackTrace();
            System.out.println("File not Found");
        }catch (ClassNotFoundException e){
            //e.printStackTrace();

        }
        
        
    }
    public static String[] getLinks (int n, String cmd){
        
        System.out.println("Searching for \"" + cmd + "\"");
        
        String[] ref = new String[n];
        try{
                //Update top-1m.cvs location
                File file = new File ("top-1m.csv");
                Scanner inputStream = new Scanner (new FileInputStream (file));
            
                for (int i=0; i<n; i++){
                    String temp = inputStream.nextLine();
                    String[] split = temp.split(",");
                    ref[i] = "http://www."+split[1];
                }
                
                System.out.printf("Loaded %d websites\n", n);
                inputStream.close();
            
            }catch (FileNotFoundException e){
                System.out.println("Sorry! Your computer cannot handle it.");
            }
        
        return ref;
    }
    
    
}


