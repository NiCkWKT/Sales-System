package org.dbms;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class AdministratorPage extends Page {
    public static void start(Connection conn) throws SQLException {
        boolean running = true;
        while (running) {
            printAdminMenu();
            int choice = takeChoiceInput(1, 5);
            switch (choice) {
                case 1:
                    Administrator.createAllTables(conn);
                    pressEnterToContinue();
                    break;
                case 2:
                    Administrator.deleteAllTables(conn);
                    pressEnterToContinue();
                    break;
                case 3:
                    Administrator.loadDataFromFiles(conn);
                    pressEnterToContinue();
                    break;
                case 4:
                    System.out.print("Which table would you like to show: ");
                    String tableName = takeStringInput();
                    System.out.println();
                    Administrator.showContent(conn, tableName);
                    pressEnterToContinue();
                    break;
                default:
                    running = false;
                    break;
            }
        }
    }

}