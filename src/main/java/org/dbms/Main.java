package org.dbms;

import java.sql.DriverManager;
import java.sql.Connection;

public class Main {
    public static void main(String[] args) {
        String driverName = "com.mysql.jdbc.Driver";
        String dbAddress = "jdbc:mysql://projgw.cse.cuhk.edu.hk:2633/db10?autoReconnect=true&useSSL=false";
        String userName = "Group10";
        String userPwd = "CSCI3170";

        try {
            Class.forName(driverName);

            Connection con = DriverManager.getConnection(dbAddress, userName, userPwd);

            System.out.printf("Connection success");

        } catch(Exception e) {
            e.printStackTrace();

            System.out.printf("Connection failed!");

        }
    }
}