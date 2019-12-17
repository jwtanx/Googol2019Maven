
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
import java.util.Date;
import java.util.Scanner;

public class Appointment{

    private Scanner s = new Scanner(System.in);
    private File appointment = new File("Appointment.txt");
    private DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

    public Appointment(String cmd) {
        if(cmd.toLowerCase().contains("show ") || cmd.toLowerCase().contains("list ")) {
            displayAppointment();

        } else if (cmd.toLowerCase().contains("set ")) {
            askStartEndNCreateAppointment();

        } else if (cmd.toLowerCase().contains("delete ")) {
            System.err.print("Are you sure you want to delete your appointment(s) (Y/N): ");
            char choice = s.next().charAt(0);
            
            if(choice == 'y' || choice == 'Y'){
                deleteAppointment();
            } else {
                System.err.println("Action cancelled.");
            }
            
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
                do{
                    System.out.print("\nEnter start appointment date in format (eg. 30/12/2019 18:56)\n[Include any letter to quit]: ");
                    start = s.nextLine();

                    if (sdf.parse(start).before(now)) {
                        System.err.println("Forget the past. Let's move forward.");
                    } else {
                        break;
                    }
                    
                } while(!sdf.parse(start).after(now));
                
                // Checking if end appointment is after start appointment
                do{
                    System.out.print("\nEnter end appointment date in format (eg. 31/12/2019 00:56)\n[Include any letter to quit]: ");
                    end = s.nextLine();
                    
                    if (sdf.parse(end).before(sdf.parse(start))) {
                        System.err.println("You cannot do things in reverse time. That's illegal.");
                    } else {
                        break;
                    }
                    
                } while(!sdf.parse(end).after(sdf.parse(start)));

                if (search(start, end)) {
                    System.out.print("Time available! Insert appointment detail: ");
                    String detail = s.nextLine();
                    setAppointment(sdf.format(sdf.parse(start)) + " -> " + sdf.format(sdf.parse(end)) + " -> " + detail);
                    System.out.println("Appointment saved!");
                    break;
                } else {
                    System.err.println("Please choose other time.");
                }
            } while (true);
        } catch (ParseException pe) {
//            System.err.println("Parsing error. System restarted.");
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
                String[] StartNEnd = new String[2];
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
    
    public void displayAppointment(){
        
        try{
            Scanner sc = new Scanner(new FileInputStream(appointment));
            
            // Skipping the default appointment
            sc.nextLine();
            
            while(sc.hasNextLine()){
                
                System.out.println(sc.nextLine());
                
            }
            
            sc.close();
        } catch(FileNotFoundException fnf){
            System.err.println("No appointment created.");
        }
        
    }
    
    public void deleteAppointment(){
        
        if(appointment.delete()){
            System.err.println("Appointments deleted successfully.");
        } else {
            System.err.println("Unknown error. Cannot be deleted.");
        }
        
    }
    

}
