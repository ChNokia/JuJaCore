package ua.com.juja.core.SQLCmd.controler;

import ua.com.juja.core.SQLCmd.model.DataSet;
import ua.com.juja.core.SQLCmd.model.DatabaseManager;
import ua.com.juja.core.SQLCmd.view.View;

import java.util.Arrays;

public class MainControler {
    private View view;
    private DatabaseManager manager;

    public MainControler(View view, DatabaseManager manager) {
        this.view = view;
        this.manager = manager;
    }


    public void run() {
        connectToDB();

        while (true) {
            view.write("Введіть команду (або help для підказки) :");
            String command = view.read();

            if (command.equals("help")) {
                doHelp();
            } else if (command.equals("list")) {
                doList();
            } else if (command.startsWith("find|")) {
                doFind(command);
            } else if (command.equals("exit")) {
                view.write("Вихід з бази даних");
                System.exit(0);
            } else {
                view.write("Не відома команда: " + command);
            }
        }
    }

    private void doFind(String command) {
        String[] data = command.split("\\|");
        String tableName = data[1];

        printTable(tableName);
    }

    private void printTable(String tableName) {
        printHeader(manager.getTableColumns(tableName));
        printData(manager.getTableData(tableName));
    }

    private void printData(DataSet[] tableData) {
        for (DataSet dataSet : tableData) {
            printRow(dataSet);
        }
    }

    private void printRow(DataSet dataSet) {
        Object[] values = dataSet.getValues();
        String result = "|";

        for (Object value : values) {
            result += value + "|";
        }

        view.write(result);
    }

    private void printHeader(String[] tableColumns) {
        String result = "|";
        for (String columnName : tableColumns) {
            result += columnName + "|";
        }
        view.write("--------------------");
        view.write(result);
        view.write("--------------------");
    }

    private void doList() {
        String[] tables = manager.getTableNames();

        view.write(Arrays.toString(tables));
    }

    private void doHelp() {
        view.write("Наявні команди:");
        view.write("\tlist");
        view.write("\t\tдля отримання списку всіх таблиць бази, до якої підключилися");

        view.write("\tfind|tableName");
        view.write("\t\tдля отримання вмісту таблиці 'tableName'");

        view.write("\thelp");
        view.write("\t\tдля показу цього списку на екран");

        view.write("\texit");
        view.write("\t\tдля виходу з програми");
    }

    private void connectToDB() {
        view.write("Привіт!");
        view.write("Введіть ім'я бази даних, ім'я користувача і пароль у форматі: database|username|password");

        while ( true ) {
            try {
                String string = view.read();
                String[] data = string.split("\\|");

                if ( data.length != 3 ) {
                    throw new IllegalArgumentException("Невірна кількість параметрів розділених знаком '|', очікується 3, але є: " + data.length);
                }

                String database = data[0];
                String username = data[1];
                String password = data[2];

                manager.connect(database, username, password);

                break;
            } catch (Exception e) {
                printError(e);
            }
        }

        view.write("Успіх!");
    }

    private void printError(Exception e) {
        String message = e.getMessage();

        if ( e.getCause() != null ) {
            message = message + " " + e.getCause().getMessage();
        }

        view.write("Помилка! Причина:" +  message);
        view.write("Повторіть спробу!");
    }
}

