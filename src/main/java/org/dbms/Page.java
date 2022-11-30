package org.dbms;

import java.util.Scanner;

public class Page {
    public static int takeChoiceInput(int min, int max) {
        String input;
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.print("Enter Your Choice: ");
            input = sc.next();

            if (!input.matches(".*[a-zA-Z]+.*") && Integer.parseInt(input) >= min && Integer.parseInt(input) <= max) {
                return Integer.parseInt(input);
            }
            else {
                System.out.printf("\nInvalid input\n");
            }
        }
    }

    public static String takeStringInput() {
        Scanner sc = new Scanner(System.in);
        return sc.next();
    }

    public static void printMainMenu() {
        System.out.println("Welcome to sales system!\n");
        System.out.println("-----Main menu-----");
        System.out.println("What kinds of operation would you like to perform?");
        System.out.println("1. Operations for administrator");
        System.out.println("2. Operations for salesperson");
        System.out.println("3. Operations for manager");
        System.out.println("4. Exit this program\n");
    }

    public static void printAdminMenu() {
        System.out.println("-----Operations for administrator menu-----");
        System.out.println("What kinds of operation would you like to perform?");
        System.out.println("1. Create all tables");
        System.out.println("2. Delete all tables");
        System.out.println("3. Load from datafile");
        System.out.println("4. Show content of a table");
        System.out.println("5. Return to the main menu");
    }

    public static void printSalesMenu() {
        // TODO
    }

    public static void printManagerMenu() {
        // TODO
    }

    public static void pressEnterToContinue() {
        System.out.println("Press ENTER to continue...");
        try {
            System.in.read();
        }
        catch(Exception e)
        {}
    }
}
