package ua.com.juja.core.SQLCmd.controler.command;

import ua.com.juja.core.SQLCmd.view.View;

public class Help implements Command {
    private View view;

    public Help(View view) {
        this.view = view;
    }

    @Override
    public boolean canProcess(String command) {
        return command.equals("help");
    }

    @Override
    public void process(String command) {
        view.write ("Існуючі команди:");
        view.write ("\tlist");
        view.write ("\t\tдля отримання списку всіх таблиць бази, до якої підключилися");

        view.write ("\tfind|tableName");
        view.write ("\t\tдля отримання вмісту таблиці 'tableName'");

        view.write ("\thelp");
        view.write ("\t\tдля виведення цього списку на екран");

        view.write ("\texit");
        view.write ("\t\tдля виходу з програми");
    }
}
