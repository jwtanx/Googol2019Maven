package com.superjeevan.googol2019maven;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

public class Appointment {

    private Scanner s = new Scanner(System.in);
    private String name;
    private File appointment = new File(name + "_Appointment.txt");
    private DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
    private DateFormat sd = new SimpleDateFormat("dd/MM/yyyy");

    public Appointment(String cmd, String name) {
        this.name = name;
        appointment = new File(name + "_Appointment.txt");

        if (cmd.toLowerCase().contains("show ") || cmd.toLowerCase().contains("list ")) {
            if (cmd.toLowerCase().contains("today")) {
                
                Date todayDate = new Date();
                displayExactAppointment(sd.format(todayDate));
                
            } else if (cmd.toLowerCase().contains("tomorrow")) {
                
                Date todayDate = new Date();

                Calendar c = Calendar.getInstance();
                //Setting the date to the given date
                c.setTime(todayDate);

                //Number of Days to add
                c.add(Calendar.DAY_OF_MONTH, 1);
                //Date after adding the days to the given date
                Date tomorrow = c.getTime();

                displayExactAppointment(sd.format(tomorrow));
                
            } else if (cmd.toLowerCase().contains("yesterday")) {
                
                Date todayDate = new Date();

                Calendar c = Calendar.getInstance();
                //Setting the date to the given date
                c.setTime(todayDate);

                //Number of Days to add
                c.add(Calendar.DAY_OF_MONTH, -1);
                //Date after adding the days to the given date
                Date yesterday = c.getTime();

                displayExactAppointment(sd.format(yesterday));
                
            } else if (cmd.contains("/")){
                
                cmd = cmd.toLowerCase();
                cmd = cmd.replaceAll("/", "");
                String findDate[] = cmd.split(" ");
                Date dateToSearch = new Date();
                
                for(int i = 0; i < findDate.length; i++){
                    
                    if(isDate(findDate[i])){
                        
                        String tempDate = findDate[i].substring(0, 2) + "/" + findDate[i].substring(2, 4) + "/" + findDate[i].substring(4, 8);
                        
                        try{
                            dateToSearch = sd.parse(tempDate);
                        } catch (ParseException pe){
                            System.out.println("Error parsing your date. Please try \"List 30/12/2019 appointment\"");
                        }
                        break;
                        
                    }
                }
                
                displayExactAppointment(sd.format(dateToSearch));
                
            } else {
                
                displayAppointment();
                
            }

        } else if (cmd.toLowerCase().contains("set ")) {
            askStartEndNCreateAppointment();

        } else if (cmd.toLowerCase().contains("delete ")) {
            System.err.print("Are you sure you want to delete your appointment(s) (Y/N): ");
            char choice = s.next().charAt(0);

            if (choice == 'y' || choice == 'Y') {
                deleteAppointment();
            } else {
                System.err.println("Action cancelled.");
            }

        } else {

            if (cmd.contains("list")) {
                displayAppointment();
            } else {
                System.out.print("Set, list or delete: ");
                String appointmentChoice = s.nextLine();

                if (appointmentChoice.equalsIgnoreCase("set")) {
                    askStartEndNCreateAppointment();
                } else if (appointmentChoice.equalsIgnoreCase("list")) {
                    displayAppointment();
                } else if (appointmentChoice.equalsIgnoreCase("delete")) {
                    deleteAppointment();
                }
            }
        }
    }

    public void askStartEndNCreateAppointment() {

        if (!appointment.exists()) {
            try {
                PrintWriter p = new PrintWriter(new FileOutputStream(appointment));
                // To avoid parsing error.
                p.println("01/01/1970 00:00 -> 01/01/1970 00:00 -> Default");

                p.close();
            } catch (IOException ioe) {
                System.err.println("Problem creating appointment file");
            }
        }

        String start = "";
        String end = "";
        try {
            do {
                Date now = new Date();

                // Checking if starting appoint before current exact time
                do {
                    System.out.print("\nEnter start appointment date in format (eg. 30/12/2019 18:56)\n[Include any letter to quit]: ");
                    start = s.nextLine();

                    if (sdf.parse(start).before(now)) {
                        System.err.println("Forget the past. Let's move forward.");
                    } else {
                        break;
                    }

                } while (!sdf.parse(start).after(now));

                // Checking if end appointment is after start appointment
                do {
                    System.out.print("\nEnter end appointment date in format (eg. 31/12/2019 00:56)\n[Include any letter to quit]: ");
                    end = s.nextLine();

                    if (sdf.parse(end).before(sdf.parse(start))) {
                        System.err.println("You cannot do things in reverse time. That's illegal.");
                    } else {
                        break;
                    }

                } while (!sdf.parse(end).after(sdf.parse(start)));

                if (search(start, end)) {
                    System.out.print("\nTime available! Insert appointment detail: ");
                    String detail = s.nextLine();
                    setAppointment(sdf.format(sdf.parse(start)) + " -> " + sdf.format(sdf.parse(end)) + " -> " + detail);
                    System.out.println("Appointment saved!");
                    break;
                } else {
                    System.err.println("Please choose other time.");
                }
            } while (true);
        } catch (ParseException pe) {
            System.err.println("Kindly make sure all the details are input. Please try again.");
        }
    }

    public void setAppointment(String appointmentStr) {

        try {
            PrintWriter pw = new PrintWriter(new FileOutputStream(appointment, true));

            pw.println(appointmentStr);

            pw.close();
        } catch (IOException io) {
            System.err.println("Problem with saving appointment.");
        }
    }

    public boolean search(String dateStartTime, String dateEndTime) {

        try {
            Date newAppointmentStart = sdf.parse(dateStartTime);
            Date newAppointmentEnd = sdf.parse(dateEndTime);

            Scanner sc = new Scanner(new FileInputStream(appointment));
            int line = 0;

            while (sc.hasNextLine()) {
                line++;
                sc.nextLine();
            }

            sc.close();

            sc = new Scanner(new FileInputStream(appointment));

            for (int i = 0; i < line; i++) {
                String[] StartNEnd;
                StartNEnd = sc.nextLine().split(" -> ");
                Date oldAppointmentStart = sdf.parse(StartNEnd[0]);
                Date oldAppointmentEnd = sdf.parse(StartNEnd[1]);

                if (newAppointmentStart.before(oldAppointmentStart) && newAppointmentEnd.before(oldAppointmentStart)) {
                    if (i == line - 1) {
                        return true;
                    }
                } else if (newAppointmentStart.after(oldAppointmentEnd) && newAppointmentEnd.after(oldAppointmentEnd)) {
                    if (i == line - 1) {
                        return true;
                    }
                } else {
                    System.err.println("Appointment clashes: " + "[" + StartNEnd[0] + " -> " + StartNEnd[1] + "] - " + StartNEnd[2]);
                    return false;
                }
            }

            sc.close();

        } catch (ParseException pe) {
            System.err.println("Parsing error. Please contact me GitHub@ Google-X. Thank you.");
        } catch (FileNotFoundException ex) {
            System.err.println("File not found! ");
        }

        return true;
    }

    public void displayAppointment() {

        try {
            Scanner sc = new Scanner(new FileInputStream(appointment));

            // Skipping the default appointment
            sc.nextLine();

            while (sc.hasNextLine()) {

                System.out.println(sc.nextLine());

            }

            sc.close();
        } catch (FileNotFoundException fnf) {
            System.err.println("No appointment created.");
        }

    }

    public void displayExactAppointment(String appstr) {
        
        try {
            Date app = sd.parse(appstr);
            
            Scanner sc = new Scanner(new FileInputStream(appointment));
            int line = 0;

            while (sc.hasNextLine()) {
                line++;
                sc.nextLine();
            }

            sc.close();
            
            sc = new Scanner(new FileInputStream(appointment));
            boolean gotAppointment = false;
            for(int i = 0; i < line; i++) {
                
                
                String[] StartNEnd;
                StartNEnd = sc.nextLine().split(" -> ");
                Date oldAppointmentStart = sd.parse(StartNEnd[0]);
                Date oldAppointmentEnd = sd.parse(StartNEnd[1]);

                if (app.equals(oldAppointmentStart) || app.equals(oldAppointmentEnd)) {
                    System.out.println("\nAppointment detail: " + StartNEnd[2]);
                    System.out.println("Time: [" + StartNEnd[0] + " -> " + StartNEnd[1] + "]");
                    gotAppointment = true;
                }

                else if (app.after(oldAppointmentStart) && app.before(oldAppointmentEnd)) {
                    System.out.println("\nAppointment detail: " + StartNEnd[2]);
                    System.out.println("Time: [" + StartNEnd[0] + " -> " + StartNEnd[1] + "]");
                    gotAppointment = true;
                } 
                
                else if (i == line - 1){
                    if(gotAppointment == false){
                        System.out.println("No appointment available on " + sd.format(app));
                    }
                }

            }

            sc.close();
        } catch (FileNotFoundException fnf) {
            System.err.println("File not found!");
        } catch (ParseException pe) {

        }

    }

    public void deleteAppointment() {

        if (appointment.delete()) {
            System.err.println("Appointments deleted successfully.");
        } else {
            System.err.println("Unknown error. Cannot be deleted.");
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
