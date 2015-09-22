package ua.com.juja.core.SQLCmd.controler.command;

import ua.com.juja.core.SQLCmd.model.DatabaseManager;
import ua.com.juja.core.SQLCmd.view.View;

public class Connect implements Command {
    private static final int COUNT_CONNECT_DATA = "connect|database|username|password".split("\\|").length;
    private DatabaseManager manager;
    private View view;

    public Connect(DatabaseManager manager, View view) {
        this.manager = manager;
        this.view = view;
    }

    @Override
    public boolean canProcess(String command) {
        return command.startsWith("connect|");
    }

    @Override
    public void process(String command) {
        try {
            String[] data = command.split("\\|");

            if ( data.length != COUNT_CONNECT_DATA ) {
                throw new IllegalArgumentException(String.format("Невірно кількість параметрів розділених знаком '|', очікується %s, але є: %s", COUNT_CONNECT_DATA, data.length));
            }

            String database = data[1];
            String username = data[2];
            String password = data[3];

            manager.connect(database, username, password);
        } catch (Exception e) {
            printError(e);
        }

        view.write("Під'єднання до бази даних успішне!");
    }

    private void printError(Exception e) {
        String message = e.getMessage();

        if ( e.getCause() != null ) {
            message = message + " " + e.getCause().getMessage();
        }

        view.write("Помилка! Причина:" +  message);
        view.write("Повтори спробу!");
    }
}
