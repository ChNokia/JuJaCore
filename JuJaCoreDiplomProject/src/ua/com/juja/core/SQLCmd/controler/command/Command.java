package ua.com.juja.core.SQLCmd.controler.command;

public interface Command {
    public boolean canProcess(String command);
    public void process(String command);
}
