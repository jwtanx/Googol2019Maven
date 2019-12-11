package com.superjeevan.googol2019maven;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

public class RateLoad {

    private File filename;

    public RateLoad() {
        filename = new File("ExchangeRate.dat");
    }

    public double load(String CUR) {

        Date d1 = new Date(filename.lastModified());

        if (checkIfNeedUpdate(d1)) {
            System.out.println("Upon checking, your exchange rate data are outdated. Update will be proceeded.");
            if (checkConnection()) {
                System.out.println("Updating data now...");
                RateUpdate up = new RateUpdate();
            } else {
                System.out.println("Could not update at the momment. Please connect to Internet to update.");
            }
        }

        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename));

            HashMap<String, Double> exchangeRate = (HashMap<String, Double>) in.readObject();

            for (String i : exchangeRate.keySet()) {
                if (i.equalsIgnoreCase(CUR)) {
                    return exchangeRate.get(i);
                }
            }

            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
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

        return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS) > 5;

    }

}
