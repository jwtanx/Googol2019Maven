package com.superjeevan.googol2019maven;

import java.io.IOException;
import java.util.Random;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DiceGame {

    private Scanner s = new Scanner(System.in);
    private Random r = new Random();

    public DiceGame() {

        // Instruction
        System.out.println("This is a 2 players dice game where each player will roll the dice twice.");
        System.out.println("The player with the highest score wins the game. However, the game rules are special.");
        System.out.println("If the first & second dice value are both odd, +5 to the score.");
        System.out.println("If the first & second dice value are both 6, -5 to the score.");
        System.out.println("If the first & second dice value are 3, the player will get to roll one more time.");
        System.out.println("Have fun & good luck :) ");
        System.out.println("Hit 'Enter' to start the game.");

        s.nextLine();
        cls();
        start();

    }

    public void start() {

        int sum1 = 0, sum2 = 0;
        
        // Player 1 round 1
        System.out.print("\nPlayer 1, please press enter to roll dice.");
        promptEnterKey();
        int d11 = r.nextInt(6) + 1;
        sum1 += d11;
        System.out.printf("Player 1, first dice: %d\n", d11);

        // Player 2 round 1
        System.out.print("\nPlayer 2, please press enter to roll dice.");
        promptEnterKey();
        int d21 = r.nextInt(6) + 1;
        sum2 += d21;
        System.out.printf("Player 2, first dice: %d\n", d21);

        // Player 1 round 2
        System.out.print("\nPlayer 1, please press enter to roll dice.");
        promptEnterKey();
        int d12 = r.nextInt(6) + 1;
        sum1 += d12;
        System.out.printf("Player 1, second dice: %d\n", d12);

        // Player 2 round 2
        System.out.print("\nPlayer 2, please press enter to roll dice.");
        promptEnterKey();
        int d22 = r.nextInt(6) + 1;
        sum2 += d22;
        System.out.printf("Player 2, second dice: %d\n", d22);

        // Calculating
        if (d11 % 2 != 0 && d12 % 2 != 0) {
            sum1 += 5;
        } else if (d11 == 6 && d12 == 6) {
            sum1 -= 5;
        } else if (d11 == 3 && d12 == 3) {
            System.out.println("\nPlayer 1, please roll one more time(Press enter)");
            promptEnterKey();
            Random rnd13 = new Random();
            int d13 = rnd13.nextInt(6) + 1;
            System.out.printf("You rolled %d", d13);
            sum1 += d13;
        }
        if (d21 % 2 != 0 && d22 % 2 != 0) {
            sum2 += 5;
        } else if (d21 == 6 && d22 == 6) {
            sum2 -= 5;
        } else if (d21 == 3 && d22 == 3) {
            System.out.println("\nPlayer 2, please roll one more time(Press enter)");
            promptEnterKey();
            Random rnd23 = new Random();
            int d23 = rnd23.nextInt(6) + 1;
            System.out.printf("You rolled %d", d23);
            sum2 += d23;
        }
        System.out.printf("\nPlayer 1's point is %d\n", sum1);
        System.out.printf("Player 2's point is %d\n", sum2);

        if (sum2 > sum1) {
            System.out.println("\nPlayer 2 wins");
        } else if (sum1 == sum2) {
            System.out.println("\nDraw");
        } else {
            System.out.println("\nPlayer 1 wins");
        }

    }

    public boolean promptEnterKey() {
        s.nextLine();
        return true;
    }

    public void cls() {
        try {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } catch (IOException io) {
            io.printStackTrace();
        } catch (InterruptedException ex) {
            Logger.getLogger(Command.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
