package com.superjeevan.googol2019maven;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class WeatherUpdate {

    private File filename = new File("WeatherData.dat");
    private String WeatherSearch;
    private String location = "";
    private Scanner s = new Scanner(System.in);
    private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

    public WeatherUpdate() {

        System.out.println("This weather is provided by WeatherAtlas (https://www.weather-my.com)");

        if (checkConnection()) {
            System.out.println("Last Update: " + sdf.format(filename.lastModified()));
            System.out.print("Enter specific location [Full city name/state name]: ");
            String place = s.nextLine();
            checkPrevUpdate(place);

        } else {
            System.out.println("Last Update: " + sdf.format(filename.lastModified()));
            System.out.println("Please connect to the Internet in order to update.");
        }

    }

    public void checkPrevUpdate(String WeatherFromCmd) {

        String[] temp = WeatherFromCmd.split(" ");

        for (String a : temp) {
            location += a.substring(0, 1).toUpperCase() + a.substring(1).toLowerCase() + " ";
        }

        WeatherSearch = WeatherFromCmd.toLowerCase().replaceAll(" ", "-");

        update();

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

    public void update() {

        try {
            String url;
            String list[] = new String[10];

            try {
                //Construct the link
                url = "https://www.weather-my.com/en/malaysia/" + WeatherSearch + "-long-term-weather-forecast";
                //Get Request
                Document doc = Jsoup.connect(url).userAgent("Mozilla").get();
                int cnt;

                // Date and Day
                Elements date = doc.select("div.text-center.text-center-not-xs.col-xs-4.col-height.col-middle.lineheight115");
                String temp;
                cnt = 0;
                for (Element dateNday : date) {

                    if (cnt == 9) {
                        break;
                    }

                    temp = dateNday.getElementsByTag("div").text();
                    list[cnt] = temp.substring(0, 3) + " " + temp.substring(3);
                    cnt++;

                }

                // Weather condition
                Elements weather = doc.select("span.font_150_rem.strong.block.font_rem");
                cnt = 0;
                for (Element weatherDat : weather) {

                    if (cnt == 9) {
                        break;
                    }

                    list[cnt] += " : " + weatherDat.getElementsByTag("span").text();
                    cnt++;

                }

                // UpperBoundery Temperature
                Elements highTemperature = doc.select("li.font_200_rem.text-danger");
                cnt = 0;
                for (Element highTempDat : highTemperature) {

                    if (cnt == 9) {
                        break;
                    }

                    list[cnt] += " (MAX: " + highTempDat.getElementsByTag("li").text();
                    cnt++;

                }

                // LowerBoundery Temperature
                Elements lowTemperature = doc.select("li.font_150_rem.text-primary");
                cnt = 0;
                for (Element lowTempDat : lowTemperature) {

                    if (cnt == 9) {
                        break;
                    }

                    list[cnt] += " & MIN: " + lowTempDat.getElementsByTag("li").text() + ")";
                    cnt++;

                }

            } catch (Exception e) {
                System.err.println("Error in updating. Please try again later.");
//                    System.out.println("Cannot find exchange rate for " + currency);
            }

            try {
                ObjectOutputStream writer = new ObjectOutputStream(new FileOutputStream(filename));

                writer.writeUTF("Weather condition in " + location + ": ");

                for (int i = 0; i < 9; i++) {
                    writer.writeUTF(list[i]);
                }
                Date t = new Date();
                System.out.println("Successfully update these weeks weather at " + t);
                writer.close();

            } catch (NullPointerException nu) {
                System.out.println("Sorry. The weather forecast in " + location + "is not provided.");
            } catch (Exception i) {
                i.printStackTrace();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
