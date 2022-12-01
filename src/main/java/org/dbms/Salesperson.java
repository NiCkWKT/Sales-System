package org.dbms;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Salesperson extends Operation {
    private static final String LIST_ALL_PARTS_INFO =
    "SELECT * FROM (" +
    "SELECT p_id AS ID, p_name AS Name, m_name AS Manufacturer, c_name AS Category, available_quantity AS Quantity, warranty AS Warranty, price AS Price FROM part " +
    "INNER JOIN manufacturer " +
    "ON manufacturer.m_id = part.manufacturer_id " +
    "INNER JOIN category " +
    "ON category.c_id = part.category_id";
    public static void searchForPart(Connection conn) throws SQLException {
        int criterion = takeSearchInput();
        System.out.print("Type in the search keyword: ");
        String keyword = Page.takeStringInput();
        String order = takeOrderInput();
        String query;
        Statement stmt = conn.createStatement();
        if (criterion == 1) {
            query = LIST_ALL_PARTS_INFO + " WHERE p_name LIKE '%" + keyword + "%'" + " ORDER BY price " + order + ") AS sub";
            display(stmt, query);
        }
        else {
            query = LIST_ALL_PARTS_INFO + " WHERE m_name LIKE '%" + keyword + "%'" + " ORDER BY price " + order + ") AS sub";
            display(stmt, query);
        }
    }

    public static void sellPart(Connection conn) throws SQLException {
        // TODO: Error handle
        System.out.print("Enter the Part ID: ");
        String pid = Page.takeStringInput();
        System.out.print("Enter the Salesperson ID: ");
        String sid = Page.takeStringInput();

        String pattern = "dd/MM/yyyy";
        String currentDate = new SimpleDateFormat(pattern).format(new Date());
        String update = "UPDATE part SET available_quantity = available_quantity - 1 WHERE p_id = " + pid;

        try {
            conn.setAutoCommit(false);
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(update);

            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO transaction (part_id, salesperson_id, date) VALUES (?, ?, ?)");
            pstmt.setString(1, pid);
            pstmt.setString(2, sid);
            pstmt.setString(3, currentDate);
            pstmt.executeUpdate();

            String query = "SELECT p_id, p_name, available_quantity FROM part WHERE p_id = " + pid;
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                String id = rs.getString(1);
                String name = rs.getString(2);
                String quantity = rs.getString(3);
                if (Integer.parseInt(quantity) < 0) {
                    System.out.println("This Part is Out of Stock");
                    conn.rollback();
                }
                else
                    System.out.println("Product: " + name + "(id: " + id + ") Remaining Quantity: " + quantity);
            }
            conn.commit();
            conn.setAutoCommit(true);
            stmt.close();
        } catch (SQLException e) {
            conn.rollback();
            conn.setAutoCommit(true);
            System.err.println();
            System.err.println("Error!");
            System.err.println("Please contact Administrator");
            System.err.println();
        }
    }

    private static int takeSearchInput() {
        String input;
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("Choose the Search criterion:");
            System.out.println("1. Part Name");
            System.out.println("2. Manufacturer Name");
            System.out.print("Choose the search criterion: ");
            input = sc.next();

            if (!input.matches(".*[a-zA-Z]+.*") && (Integer.parseInt(input) == 1 || Integer.parseInt(input) == 2)) {
                return Integer.parseInt(input);
            }
            else {
                System.out.printf("\nInvalid input\n");
            }
        }
    }

    private static String takeOrderInput() {
        String input;
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("Choose ordering:");
            System.out.println("1. By price, ascending order");
            System.out.println("2. By price, descending order");
            System.out.print("Choose the search criterion: ");
            input = sc.next();

            if (!input.matches(".*[a-zA-Z]+.*") && (Integer.parseInt(input) == 1 || Integer.parseInt(input) == 2)) {
                if (Integer.parseInt(input) == 1)
                    return "ASC";
                else
                    return "DESC";
            }
            else {
                System.out.printf("\nInvalid input\n");
            }
        }
    }


}
