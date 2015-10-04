package ua.com.juja.core.SQLCmd.controler.command;

import ua.com.juja.core.SQLCmd.model.DataSet;

public class Create implements Command {
    @Override
    public boolean canProcess(String command) {
        return command.startsWith("create|");
    }

    @Override
    public void process(String command) {
        String[] data = command.split("\\|");

        if ( data.length % 2 != 0 ) {
            throw new IllegalArgumentException(String.format("Кількість параметрів повинна бути парною:" +
                    "'create|tableName|column1|value1|column2|value2|...|columnN|valueN', " +
            "а є: ", command));
        }

        String tableName = data[1];
        DataSet dataSets = new DataSet();
    }
}
