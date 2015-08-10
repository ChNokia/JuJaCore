package week2.recursion.lab17HanoiSolver;

import java.util.Stack;

public class HanoiSolver {
    public static void exchange(Stack from, Stack help, Stack to, int count) {
        if (count > 0) {
            exchange(from, to, help, count - 1);
            int biggest = (Integer) from.pop();
            to.push(biggest);
            exchange(help, from, to, count - 1);
        }
    }
}
