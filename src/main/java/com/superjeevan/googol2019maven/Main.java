package com.superjeevan.googol2019maven;

import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {

    public static void main(String[] args) {

        Scanner s = new Scanner(System.in);

        LoadData loadUser = new LoadData();
        
        cls();
        System.out.println("\nGoogol by\n");
        
        System.out.print(" __..  ..__ .___.__    ..___.___.  ..__..  .\n");
        delay(5);
        System.out.print("(__ |  |[__)[__ [__)   |[__ [__ \\  /[__]|\\ |\n");
        delay(5);
        System.out.println(".__)|__||   [___|  \\\\__|[___[___ \\/ |  || \\|");
        delay(5);

        cls();
        System.out.println("\nGoogol by\n");
        
        System.out.print(" __..  ..__ .___.__    ..___.___.  ..__..  .\n");
        delay(3);
        System.out.print("(__ |  |[__)[__ [__)   |[__ [__ \\  /[__]|\\ |\n");
        delay(3);
        System.out.println(".__)|__||   [___|  \\\\__|[___[___ \\/ |  || \\|");
        delay(3);

        cls();
        System.out.println("\nGoogol by\n");

        System.out.print(" __..  ..__ .___.__    ..___.___.  ..__..  .\n");
        delay(2);
        System.out.print("(__ |  |[__)[__ [__)   |[__ [__ \\  /[__]|\\ |\n");
        delay(2);
        System.out.println(".__)|__||   [___|  \\\\__|[___[___ \\/ |  || \\|");
        delay(1);
        
        System.out.print("\n\nEnter your name to sign in: ");

        loadUser.Load();
        Command user = new Command(loadUser.getName(), loadUser.getNumOfSearch());
        user.cmd();

    }

    public static void cls() {
        try {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } catch (IOException io) {
            io.printStackTrace();
        } catch (InterruptedException ex) {
            Logger.getLogger(Command.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void delay(int n){
        try {
            Thread.sleep(n * 100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
