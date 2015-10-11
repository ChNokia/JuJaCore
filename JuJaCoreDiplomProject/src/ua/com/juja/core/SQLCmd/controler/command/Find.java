package ua.com.juja.core.SQLCmd.controler.command;

import ua.com.juja.core.SQLCmd.model.DataSet;
import ua.com.juja.core.SQLCmd.model.DatabaseManager;
import ua.com.juja.core.SQLCmd.view.View;

public class Find implements Command {
    private DatabaseManager manager;
    private View view;

    public Find(DatabaseManager manager, View view) {
        this.manager = manager;
        this.view = view;
    }

    @Override
    public boolean canProcess(String command) {
        return command.startsWith("find|");
    }

    @Override
    public void process(String command) {
        String[] data = command.split("\\|");
        String tableName = data[1];

        printTable(tableName);
    }

    private void printTable(String tableName) {
        try {
            DataSet[] tableData = null;
            tableData = manager.getTableData(tableName);

        /*if ( tableData == null ) { //TODO: empty table
            view.write("Помилка! Причина в таблиці " + tableName);

            return;
        }*/
            printHeader(manager.getTableColumns(tableName));
            printData(tableData);
        }  catch (Exception e) {
            printError(e);
        }
    }

    private void printError(Exception e) {
        String message = e.getMessage();

        view.write("Помилка! Причина: " +  message);
        view.write("Повтори спробу!");
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
}
