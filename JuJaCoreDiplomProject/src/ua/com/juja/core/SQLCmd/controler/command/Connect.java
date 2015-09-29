package ua.com.juja.core.SQLCmd.controler.command;

import ua.com.juja.core.SQLCmd.model.DatabaseManager;
import ua.com.juja.core.SQLCmd.view.View;

public class Connect implements Command {
    private static final String SAMPLE_INPUT = "connect|database|username|password";
    private static int COUNT_SAMPLE_INPUT;
    private DatabaseManager manager;
    private View view;

    public Connect(DatabaseManager manager, View view) {
        this.manager = manager;
        this.view = view;
        COUNT_SAMPLE_INPUT = SAMPLE_INPUT.split("\\|").length;
    }

    @Override
    public boolean canProcess(String command) {
        return command.startsWith("connect|");
    }

    @Override
    public void process(String command) {
        try {
            String[] data = command.split("\\|");

            if ( data.length != COUNT_SAMPLE_INPUT) {
                throw new IllegalArgumentException(String.format("Невірно кількість параметрів розділених знаком '|', очікується %s, але є: %s", COUNT_SAMPLE_INPUT, data.length));
            }

            String database = data[1];
            String username = data[2];
            String password = data[3];

            manager.connect(database, username, password);
            view.write("Під'єднання до бази даних успішне!");
        } catch (Exception e) {
            printError(e);
        }
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
