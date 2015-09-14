package ua.com.juja.core.SQLCmd.controler;

import ua.com.juja.core.SQLCmd.model.DatabaseManager;
import ua.com.juja.core.SQLCmd.model.JDBCDatabaseManager;
import ua.com.juja.core.SQLCmd.view.Console;
import ua.com.juja.core.SQLCmd.view.View;

public class Main {
    public static void main(String[] args) {
        View view = new Console();
        DatabaseManager manager = new JDBCDatabaseManager();
        MainControler controler = new MainControler(view, manager);

        controler.run();
    }
}
