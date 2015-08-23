package ua.com.juja.core.SQLCmd;

import java.sql.*;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        System.out.println("-------- PostgreSQL " + "JDBC Connection Testing ------------");

        Class.forName("org.postgresql.Driver");

        System.out.println("PostgreSQL JDBC Driver Registered!");

        Connection connection = DriverManager.getConnection(
                "jdbc:postgresql://127.0.0.1:5432/postgres", "postgres",
                "12345");

        System.out.println("You made it, take control your database now!");

        // insert
        Statement stmt = connection.createStatement();
        stmt.executeUpdate("INSERT INTO users (name, password)" +
                "VALUES ('Stiven', 'Pupkin')");
        stmt.close();

        // select
        stmt = connection.createStatement();
        //ResultSet rs = stmt.executeQuery("SELECT * FROM users WHERE id > 10");
        ResultSet rs = stmt.executeQuery("SELECT * FROM users");
        while (rs.next()) {
            System.out.println("id:" + rs.getString("id"));
            System.out.println("name:" + rs.getString("name"));
            System.out.println("password:" + rs.getString("password"));
            System.out.println("-----");
        }
        rs.close();
        stmt.close();
    }
}
