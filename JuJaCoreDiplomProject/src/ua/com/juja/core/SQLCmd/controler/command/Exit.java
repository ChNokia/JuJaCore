package ua.com.juja.core.SQLCmd.controler.command;

import ua.com.juja.core.SQLCmd.view.View;

public class Exit implements Command {
    private View view;

    public Exit(View view) {
        this.view = view;
    }

    @Override
    public boolean canProcess(String command) {
        return command.equals("exit");
    }

    @Override
    public void process(String command) {
        view.write("Програма завершила робоу!");
        System.exit(0);
    }
}
