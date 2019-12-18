package com.superjeevan.googol2019maven;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class WeatherDisplay {

    private File filename = new File("WeatherData.txt");
    private Scanner s = new Scanner(System.in);
    private DateFormat sd = new SimpleDateFormat("dd.MMM");

    public WeatherDisplay(String cmd) {
        System.out.println("\nThis weather is provided by WeatherAtlas (https://www.weather-my.com)");
        lookFile(cmd);
    }

    public void lookFile(String cmd) {

        Date d1 = new Date(filename.lastModified());

        if (checkIfNeedUpdate(d1)) {
            System.out.println("Upon checking, your weather data are outdated. Update will be proceeded.");
            if (checkConnection()) {
                System.out.println("Updating data now...");
                WeatherUpdate up = new WeatherUpdate();
            } else {
                System.out.println("Could not update at the moment. Please connect to Internet to update.");
            }
        } else {
            
            if (cmd.toLowerCase().contains("today")) {
                
                Date todayDate = new Date();
                displayExactWeather(sd.format(todayDate));
                
            } else if (cmd.toLowerCase().contains("tomorrow")) {
                
                Date todayDate = new Date();

                Calendar c = Calendar.getInstance();
                //Setting the date to the given date
                c.setTime(todayDate);

                //Number of Days to add
                c.add(Calendar.DAY_OF_MONTH, 1);
                //Date after adding the days to the given date
                Date tomorrow = c.getTime();

                displayExactWeather(sd.format(tomorrow));
                
            } else if (cmd.toLowerCase().contains("yesterday")) {
                
                Date todayDate = new Date();

                Calendar c = Calendar.getInstance();
                //Setting the date to the given date
                c.setTime(todayDate);

                //Number of Days to add
                c.add(Calendar.DAY_OF_MONTH, -1);
                //Date after adding the days to the given date
                Date yesterday = c.getTime();

                displayExactWeather(sd.format(yesterday));
                
            } else if (cmd.contains("/")){
                
                cmd = cmd.toLowerCase();
                cmd = cmd.replaceAll("/", "");
                String findDate[] = cmd.split(" ");
                Date dateToSearch = new Date();
                String tempDate = "";
                
                for(int i = 0; i < findDate.length; i++){
                    
                    if(isDate(findDate[i])){
                        
                        if(findDate[i].substring(2, 4).equals("01")) tempDate = findDate[i].substring(0, 2) + ".JAN";
                        if(findDate[i].substring(2, 4).equals("02")) tempDate = findDate[i].substring(0, 2) + ".FEB";
                        if(findDate[i].substring(2, 4).equals("03")) tempDate = findDate[i].substring(0, 2) + ".MAR";
                        if(findDate[i].substring(2, 4).equals("04")) tempDate = findDate[i].substring(0, 2) + ".APR";
                        if(findDate[i].substring(2, 4).equals("05")) tempDate = findDate[i].substring(0, 2) + ".MAY";
                        if(findDate[i].substring(2, 4).equals("06")) tempDate = findDate[i].substring(0, 2) + ".JUN";
                        if(findDate[i].substring(2, 4).equals("07")) tempDate = findDate[i].substring(0, 2) + ".JUL";
                        if(findDate[i].substring(2, 4).equals("08")) tempDate = findDate[i].substring(0, 2) + ".AUG";
                        if(findDate[i].substring(2, 4).equals("09")) tempDate = findDate[i].substring(0, 2) + ".SEP";
                        if(findDate[i].substring(2, 4).equals("10")) tempDate = findDate[i].substring(0, 2) + ".OCT";
                        if(findDate[i].substring(2, 4).equals("11")) tempDate = findDate[i].substring(0, 2) + ".NOV";
                        if(findDate[i].substring(2, 4).equals("12")) tempDate = findDate[i].substring(0, 2) + ".DEC";
                        
                        try{
                            dateToSearch = sd.parse(tempDate);
                        } catch (ParseException pe){
                            System.out.println("Error parsing your date. Please try \"Show 30/12 weather\"");
                        }
                        break;
                        
                    }
                }
                
                displayExactWeather(sd.format(dateToSearch));
                
            } else {
                display();
            }
            
        }
    }

    public void display() {
        try {
            Scanner in = new Scanner(new FileInputStream(filename));

            for(int i = 0; i < 10; i++){
                System.out.println(in.nextLine());
            }
            
            in.close();
        
        } catch (FileNotFoundException fnf) {
            System.err.println("Weather data not found. Update to check the weather.");
        } catch (IOException ie) {
            System.err.println("Error reading data. Please try again.");
        } 

    }

    public boolean checkConnection() {

        try {
            Process process = java.lang.Runtime.getRuntime().exec("ping www.google.com");
            int x = process.waitFor();
            if (x == 0) {
                return true;
            }
            return false;

        } catch (Exception e) {

        }
        return false;
    }

    public boolean checkIfNeedUpdate(Date lastModified) {

        Date today = new Date();
        long diff = today.getTime() - lastModified.getTime();

        return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS) > 8;

    }
    
    public void displayExactWeather(String weatherStr) {
        
        try {
            Date dayToSearch = sd.parse(weatherStr);
            
            Scanner sc = new Scanner(new FileInputStream(filename));
            int line = 9;
            
            // Location
            System.out.println(sc.nextLine());
            boolean gotDate = false;
            for(int i = 0; i < line; i++){
                
                String[] dateStr = sc.nextLine().split(" : ");
                Date dateOnLine = sd.parse(dateStr[0].substring(4));
                
                if(dayToSearch.equals(dateOnLine)){
                    System.out.println(dateStr[0] + " : " + dateStr[1]);
                    gotDate = true;
                    break;
                } else {
                    if(gotDate == false && i == 8){
                        System.out.println("Not available. Kindly wait for the server to update the data in the future.");
                    }
                }
                
            }

            sc.close();
            
            
        } catch (FileNotFoundException fnf) {
            System.err.println("File not found!");
        } catch (ParseException pe) {

        } catch (IOException ioe){
            
        }
    }
    public boolean isDate(String strNum) {

        if (strNum == null) {
            return false;
        }
        try {
            int d = Integer.parseInt(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
}
