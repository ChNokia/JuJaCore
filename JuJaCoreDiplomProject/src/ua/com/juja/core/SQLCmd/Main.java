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
        stmt.executeUpdate("INSERT INTO public.user (name, password)" +
                "VALUES ('Stiven4', 'Pupkin')");
        stmt.close();

        //update
        stmt = connection.createStatement();
        String sql = "UPDATE public.user set password = 12345 where user_id=1;";
        stmt.executeUpdate(sql);
        stmt.close();

        //delete
        stmt = connection.createStatement();
        sql = "DELETE from public.user where user_id=2;";
        stmt.executeUpdate(sql);
        stmt.close();
        // select
        stmt = connection.createStatement();
        //ResultSet rs = stmt.executeQuery("SELECT * FROM users WHERE id > 10");
        ResultSet rs = stmt.executeQuery("SELECT * FROM public.user");
        while (rs.next()) {
            System.out.println("user_id:" + rs.getString("user_id"));
            System.out.println("name:" + rs.getString("name"));
            System.out.println("password:" + rs.getString("password"));
            System.out.println("-----");
        }
        rs.close();
        stmt.close();

        // table names
        stmt = connection.createStatement();
        rs = stmt.executeQuery("SELECT table_name FROM information_schema.tables WHERE table_schema='public' AND table_type='BASE TABLE'");
        while (rs.next()) {
            System.out.println(rs.getString("table_name"));
        }
        rs.close();
        stmt.close();
    }
}
