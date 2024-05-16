package com.vincent.esspringboot;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLTest {

    public static void main(String[] args) {
        String url = "jdbc:mysql://192.168.2.130:3306/ESDB";
        String username = "root";
        String password = "2017727";

        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            System.out.println("Database connected!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
