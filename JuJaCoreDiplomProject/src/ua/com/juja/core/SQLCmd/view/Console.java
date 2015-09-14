package ua.com.juja.core.SQLCmd.view;

import java.util.Scanner;

public class Console implements View {
    @Override
    public void write(String message) {
        System.out.println(message);
    }

    @Override
    public String read() {
        return new Scanner(System.in).nextLine();
    }
}
