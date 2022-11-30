package org.dbms;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class Operation {
    public static void display(Statement stmt, String query) throws SQLException {
        ResultSet rs = stmt.executeQuery(query);
        ResultSetMetaData rsmd = rs.getMetaData();
        int colNumber = rsmd.getColumnCount();
        while (rs.next()) {
            for (int i = 1; i <= colNumber; i++) {
                if (i > 1)
                    System.out.print(",  ");
                String colVal = rs.getString(i);
                System.out.print(colVal + " " + rsmd.getColumnName(i));
            }
            System.out.println("");
        }
    }
}
