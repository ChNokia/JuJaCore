package ua.com.juja.core.SQLCmd.controler.command;

import ua.com.juja.core.SQLCmd.model.DatabaseManager;
import ua.com.juja.core.SQLCmd.view.View;

public class IsReady implements Command {

    private DatabaseManager manager;
    private View view;

    public IsReady(DatabaseManager manager, View view) {
        this.manager = manager;
        this.view = view;
    }

    @Override
    public boolean canProcess(String command) {
        return !manager.isConnected();
    }

    @Override
    public void process(String command) {
        view.write(String.format("Ви не можете використовувати команду %s " +
                "поки не підключені до бази даних(connect|database|username|password)", command));
    }
}
