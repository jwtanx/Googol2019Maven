package com.superjeevan.googol2019maven;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Date;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class WeatherDisplay {

    private File filename = new File("WeatherData.dat");
    private Scanner s = new Scanner(System.in);

    public WeatherDisplay() {
        System.out.println("\nThis weather is provided by WeatherAtlas (https://www.weather-my.com)");
        lookFile();
    }

    public void lookFile() {

        Date d1 = new Date(filename.lastModified());

        if (checkIfNeedUpdate(d1)) {
            System.out.println("Upon checking, your weather data are outdated. Update will be proceeded.");
            if (checkConnection()) {
                System.out.println("Updating data now...");
                WeatherUpdate up = new WeatherUpdate();
            } else {
                System.out.println("Could not update at the momment. Please connect to Internet to update.");
            }
        } else {
            display();
        }
    }

    public void display() {
        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename));

            try {
                System.out.println();
                while (true) {
                    System.out.println(in.readUTF());
                }
            } catch (EOFException EOF) {

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

}
