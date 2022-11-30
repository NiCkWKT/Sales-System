package org.dbms;

import java.sql.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Administrator extends Operation {
    private static final String CREATE_CATEGORY =
            "CREATE TABLE category (" +
                    "c_id INT NOT NULL AUTO_INCREMENT," +
                    "c_name VARCHAR(20) NOT NULL," +
                    "PRIMARY KEY (c_id)" +
            ")";

    private static final String CREATE_MANUFACTURER =
            "CREATE TABLE manufacturer (" +
                    "m_id INT NOT NULL AUTO_INCREMENT," +
                    "m_name VARCHAR(20) NOT NULL," +
                    "m_address VARCHAR(50) NOT NULL," +
                    "m_phone_number INT NOT NULL," +
                    "PRIMARY KEY (m_id)" +
            ")";

    private static final String CREATE_PART =
            "CREATE TABLE part (" +
                    "p_id INT NOT NULL AUTO_INCREMENT," +
                    "p_name VARCHAR(20) NOT NULL," +
                    "price INT NOT NULL," +
                    "manufacturer_id INT NOT NULL," +
                    "category_id INT NOT NULL," +
                    "warranty INT NOT NULL," +
                    "available_quantity INT NOT NULL," +
                    "PRIMARY KEY (p_id)," +
                    "FOREIGN KEY (manufacturer_id) REFERENCES manufacturer(m_id)," +
                    "FOREIGN KEY (category_id) REFERENCES category(c_id)" +
            ")";

    private static final String CREATE_SALESPERSON =
            "CREATE TABLE salesperson (" +
                    "s_id INT NOT NULL AUTO_INCREMENT," +
                    "s_name VARCHAR(20) NOT NULL," +
                    "s_address VARCHAR(50) NOT NULL," +
                    "s_phone_number INT NOT NULL," +
                    "experience INT NOT NULL," +
                    "PRIMARY KEY (s_id)" +
            ")";

    private static final String CREATE_TRANSACTION =
            "CREATE TABLE transaction (" +
                    "t_id INT NOT NULL AUTO_INCREMENT," +
                    "part_id INT NOT NULL," +
                    "salesperson_id INT NOT NULL," +
                    "date VARCHAR(10) NOT NULL," +
                    "PRIMARY KEY (t_id)," +
                    "FOREIGN KEY (part_id) REFERENCES part(p_id)," +
                    "FOREIGN KEY (salesperson_id) REFERENCES salesperson(s_id)" +
            ")";

    private static final String DELETE_TRANSACTION = "DROP TABLE IF EXISTS transaction";
    private static final String DELETE_SALESPERSON = "DROP TABLE IF EXISTS salesperson";
    private static final String DELETE_PART = "DROP TABLE IF EXISTS part";
    private static final String DELETE_MANUFACTURER = "DROP TABLE IF EXISTS manufacturer;";
    private static final String DELETE_CATEGORY = "DROP TABLE IF EXISTS category";
    private static final String LOAD_CATEGORY = "LOAD DATA LOCAL INFILE '/Users/nickng/IdeaProjects/CSCI3170_Sales_System/data/category.txt' INTO TABLE category";
    private static final String LOAD_MANUFACTURER = "LOAD DATA LOCAL INFILE '/Users/nickng/IdeaProjects/CSCI3170_Sales_System/data/manufacturer.txt' INTO TABLE manufacturer";
    private static final String LOAD_PART = "LOAD DATA LOCAL INFILE '/Users/nickng/IdeaProjects/CSCI3170_Sales_System/data/part.txt' INTO TABLE part";
    private static final String LOAD_SALESPERSON = "LOAD DATA LOCAL INFILE '/Users/nickng/IdeaProjects/CSCI3170_Sales_System/data/salesperson.txt' INTO TABLE salesperson";
    private static final String LOAD_TRANSACTION = "LOAD DATA LOCAL INFILE '/Users/nickng/IdeaProjects/CSCI3170_Sales_System/data/transaction.txt' INTO TABLE transaction";
    private static final String[] createTables = {CREATE_CATEGORY, CREATE_MANUFACTURER, CREATE_PART, CREATE_SALESPERSON, CREATE_TRANSACTION};
    private static final String[] deleteTables = {DELETE_TRANSACTION, DELETE_SALESPERSON, DELETE_PART, DELETE_MANUFACTURER, DELETE_CATEGORY};
    private static final String[] loadTables = {LOAD_CATEGORY, LOAD_MANUFACTURER, LOAD_PART, LOAD_SALESPERSON, LOAD_TRANSACTION};
    private static final Set<String> tables = new HashSet<>(Arrays.asList("category", "manufacturer", "part", "salesperson", "transaction"));

    public static void createAllTables(Connection conn) throws SQLException {
        System.out.print("Processing...");
        Statement stmt = conn.createStatement();
        for (int i = 0; i < createTables.length; i++) {
//            System.out.println(createTables[i]);
            stmt.executeUpdate(createTables[i]);
        }
        stmt.close();
        System.out.println("Done! Database is initialized!");
        System.out.println();
    }
    public static void deleteAllTables(Connection conn) throws SQLException {
        System.out.print("Processing...");
        Statement stmt = conn.createStatement();
        for (int i = 0; i < deleteTables.length; i++) {
//            System.out.println(deleteTables[i]);
            stmt.executeUpdate(deleteTables[i]);
        }
        stmt.close();
        System.out.println("Done! Database is removed!");
        System.out.println();
    }

    public static void loadDataFromFiles(Connection conn) throws SQLException {
        System.out.print("Processing...");
        Statement stmt = conn.createStatement();
        for (int i = 0; i < loadTables.length; i++) {
//            System.out.println(loadTables[i]);
            stmt.executeUpdate(loadTables[i]);
        }
        stmt.close();
        System.out.println("Done! Data is inputted to the database!");
        System.out.println();
    }

    public static void showContent(Connection conn, String tableName) throws SQLException {
        Statement stmt = conn.createStatement();
        if (tables.contains(tableName)) {
            String query = "SELECT * FROM " + tableName;
            System.out.println("Content of table " + tableName);
            display(stmt, query);
        }
        else {
            System.out.println("Table does not exist!");
        }
        System.out.println();
        stmt.close();
    }

}
