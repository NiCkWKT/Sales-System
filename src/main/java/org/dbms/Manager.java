package org.dbms;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Manager extends Operation {
    private static final String LIST_SALES_ASC = "SELECT s_id AS ID, s_name AS Name, s_phone_number AS 'Mobile Phone', experience AS 'Years Of Experience' FROM salesperson ORDER BY experience";
    private static final String LIST_SALES_DESC = "SELECT s_id AS ID, s_name AS Name, s_phone_number AS 'Mobile Phone', experience AS 'Years Of Experience' FROM salesperson ORDER BY experience DESC";

    public static void listAllSalesperson(Connection conn) throws SQLException {
        int choice = takeOrderInput();
        Statement stmt = conn.createStatement();
        if (choice == 1)
            display(stmt, LIST_SALES_ASC);
        else
            display(stmt, LIST_SALES_DESC);
        System.out.println();
        stmt.close();
    }

    public static void countSalesForEachStaff(Connection conn) throws SQLException {
        String[] bound = takeBoundInput();
        String query =
                "SELECT s_id AS ID, s_name AS Name, experience AS 'Years Of Experience', COUNT(*) As 'Number Of Transaction' " +
                "FROM (SELECT * FROM transaction INNER JOIN salesperson " +
                "ON transaction.salesperson_id = salesperson.s_id " +
                "WHERE experience >= " + bound[0] + " AND experience <= " + bound[1] + ") AS sub " +
                "GROUP BY s_id " +
                "ORDER BY s_id DESC";
        Statement stmt = conn.createStatement();
        display(stmt, query);
        stmt.close();
    }

    public static void sortAndListManufacturer(Connection conn) throws SQLException {
        Statement stmt = conn.createStatement();
        String query =
        "SELECT m_id AS 'Manufacturer ID', m_name AS 'Manufacturer Name', SUM(price) AS 'Total Sales Value' " +
        "FROM transaction INNER JOIN " +
        "(SELECT p_id, m_id, m_name, price FROM part INNER JOIN manufacturer " +
        "ON part.manufacturer_id = manufacturer.m_id) AS sub " +
        "ON transaction.part_id = sub.p_id " +
        "GROUP BY m_id " +
        "ORDER BY SUM(price) DESC";
        display(stmt, query);
        stmt.close();
    }

    public static void NMostPopular(Connection conn) throws SQLException {
        Statement stmt = conn.createStatement();
        String n = takeLimitInput();
        String query =
            "SELECT part_id AS 'Part ID', p_name AS 'Part Name', COUNT(*) AS 'No. of Transaction' " +
            "FROM transaction INNER JOIN part " +
            "ON transaction.part_id = part.p_id " +
            "GROUP BY part_id " +
            "HAVING COUNT(*) != 0 " +
            "ORDER BY COUNT(*) DESC " +
            "LIMIT " + n;
        display(stmt, query);
        stmt.close();
    }

    private static int takeOrderInput() {
        Scanner sc = new Scanner(System.in);
        String input;
        while (true) {
            System.out.println("Choose ordering:");
            System.out.println("1. By ascending order");
            System.out.println("2. By descending order");
            System.out.print("Choose the list of ordering: ");
            input = sc.next();
            if (!input.matches(".*[a-zA-Z]+.*") && (Integer.parseInt(input) == 1 || Integer.parseInt(input) == 2)) {
                return Integer.parseInt(input);
            }
            else {
                System.out.printf("\nInvalid input\n");
            }
        }
    }

    private static String[] takeBoundInput() {
        Scanner sc = new Scanner(System.in);
        String[] bound = new String[2];
        System.out.print("Type in the lower bound for years of experience: ");
        String low = sc.next();
        System.out.print("Type in the upper bound for years of experience: ");
        String up = sc.next();
        while (true) {
            if (!low.matches(".*[a-zA-Z]+.*")
                    && !up.matches(".*[a-zA-Z]+.*")
                    && Integer.parseInt(low) >= 0
                    && Integer.parseInt(up) >= 0
                    && (Integer.parseInt(up) >= Integer.parseInt(low))) {
                bound[0] = low;
                bound[1] = up;
                return bound;
            }
            else {
                System.out.printf("\nInvalid input\n");
            }
        }
    }

    private static String takeLimitInput() {
        Scanner sc = new Scanner(System.in);
        String input;
        while (true) {
            System.out.print("Type in the number of parts: ");
            input = sc.next();
            if (!input.matches(".*[a-zA-Z]+.*") && Integer.parseInt(input) > 0) {
                return input;
            }
            else {
                System.out.printf("\nInvalid input\n");
            }
        }
    }
}
