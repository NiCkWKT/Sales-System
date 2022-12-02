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
        System.out.println("4. Exit this program");
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
        System.out.println("-----Operations for salesperson menu-----");
        System.out.println("What kinds of operation would you like to perform?");
        System.out.println("1. Search for parts");
        System.out.println("2. Sell a part");
        System.out.println("3. Return to the main menu");
    }

    public static void printManagerMenu() {
        System.out.println("-----Operations for manager menu-----");
        System.out.println("What kinds of operation would you like to perform?");
        System.out.println("1. List all salespersons");
        System.out.println("2. Count the no. of sales record of each salesperson under a specific range on years of experience");
        System.out.println("3. Show the total sales value of each manufacturer");
        System.out.println("4. Show the N most popular part");
        System.out.println("5. Return to the main menu");
    }

    public static void pressEnterToContinue() {
        System.out.print("Press ENTER to continue...");
        try {
            System.in.read();
        }
        catch(Exception e)
        {}
    }
}
