package ua.com.juja.core.SQLCmd.model;

import java.sql.*;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        String database = "postgres";
        String userName = "postgres";
        String password = "12345";
        DatabaseManager databaseManager = new JDBCDatabaseManager();

        databaseManager.connect(database, userName, password);

        String[] columnsName = databaseManager.getTableColumns("user");

        System.out.println(Arrays.toString(columnsName));

        int a = 4;

        int count = 32;
        while( count-- > 0 ) {
            if ( a < 0 ) {
                System.out.print(1);
            } else {
                System.out.print(0);
            }

            a = a << 1;
        }
    }
}
