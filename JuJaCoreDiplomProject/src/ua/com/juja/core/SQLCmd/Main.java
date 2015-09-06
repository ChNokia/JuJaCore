package ua.com.juja.core.SQLCmd;

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
    }
}
