package ua.com.juja.core.SQLCmd.controler;

import ua.com.juja.core.SQLCmd.controler.command.*;
import ua.com.juja.core.SQLCmd.model.DatabaseManager;
import ua.com.juja.core.SQLCmd.view.View;

public class MainController {
    private View view;
    private DatabaseManager manager;
    private Command[] commands;

    public MainController(View view, DatabaseManager manager) {
        this.view = view;
        this.manager = manager;
        this.commands = new Command[]{new Connect(manager, view)
                                    , new Exit(view)
                                    , new Help(view)
                                    , new IsReady(manager, view)
                                    , new List(manager, view)
                                    , new Find(manager, view)
                                    , new Clear(manager, view)
                                    , new NotExisted(view)};
    }

    public void run() {
        try {
            operate();
        } catch (ExitExceptions e) {}
    }

    private void operate() {
        view.write("SQLCmd запущена!");
        view.write ("Для під'єднання до бази даних введіть ім'я бази даних," +
                " ім'я користувача та пароль у форматі: connect|database|username|password");

        while (true) {
            String inputCommand = view.read();

            if ( inputCommand == null ) {
                new Exit(view).process(inputCommand);
            }

            for ( Command command : commands) {
                if ( command.canProcess(inputCommand) ) {
                    command.process(inputCommand);
                    break;
                }
            }

            view.write("Введи команду (або help для допомоги) :");
        }
    }
}

