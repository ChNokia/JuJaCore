package ua.com.juja.core.SQLCmd.controler.command;

import ua.com.juja.core.SQLCmd.view.View;

public class NotExisted implements Command {
    private View view;

    public NotExisted(View view) {
        this.view = view;
    }

    @Override
    public boolean canProcess(String command) {
        return true;
    }

    @Override
    public void process(String command) {
        view.write("Команда не підтримується: " + command);
    }
}
