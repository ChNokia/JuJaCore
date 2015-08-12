package ua.com.juja.core.weeks.recursion.lab18Parser;
/*
Это пример небольшого и ограниченного по функциональности рекурсивного парсера арифметических выражений.
Он умеет:
1) встретив скобки с двух сторон – свести задачу к анализу содержимого скобок (пример:”(1+1)” -> “1+1″, пример “(1+(2/3))” -> “1+(2/3)”);
2) встретив число-знак-XXX – свести задачу к анализу XXX (пример:”1*(10-3)” -> “(10-3)”, пример “3+(2/(9+1))” -> “(2/(9+1)”);

Задание: переписать его, что бы он ВМЕСТО пункта 2 дела пункт 2′
2′) встретив XXX-знак-число – сведил задачу к анализу XXX. (пример:”(10-3)*1″ -> “(10-3)”, пример “(2/(9+1))+3″ -> “(2/(9+1)”);
Предупреждение #1: это не полноценный парсер арифметических выражений, есть множество корректных выражений на которых он “падает” или вычисляет некорректно (“(1+1)+(1+1)”, “-1″, …).
Предупреждение #2: в условиях лабораторной ожидается, что старые выражения вида число-знак-XXX больше на разбираются.

public class Parser {
    public static void main(String[] args) {
        System.out.println(eval("123"));
        System.out.println(eval("2*3"));
        System.out.println(eval("2*(1+3)"));
        System.out.println(eval("1+(5-2*(13/6))"));
    }

    public static int eval(String expr) {
        return eval(expr, 0, expr.length());
    }

    private static int eval(String expr, int from, int to) {
        if (expr.charAt(from) == '(') {
            return eval(expr, from + 1, to - 1);
        } else {
            int pos = from;
            while (pos < to) {
                if (Character.isDigit(expr.charAt(pos))) {
                    pos++;
                } else {
                    int leftOperand = Integer.valueOf(expr.substring(from, pos));
                    char operation = expr.charAt(pos);
                    int rightOperand = eval(expr, pos + 1, to);
                    switch (operation) {
                        case '+':
                            return leftOperand + rightOperand;
                        case '-':
                            return leftOperand - rightOperand;
                        case '*':
                            return leftOperand * rightOperand;
                        case '/':
                            return leftOperand / rightOperand;
                    }
                }
            }
            return Integer.valueOf(expr.substring(from, to));
        }
    }
}
Тестирующий код (для моего примера)

public class ParserTest {
    public static void main(String[] args) {
        System.out.println(">> 123 = " + Parser.eval("123"));
        System.out.println(">> 2*3 = " + Parser.eval("2*3"));
        System.out.println(">> 2*(1+3) = " + Parser.eval("2*(1+3)"));
        System.out.println(">> 1+(5-2*(13/6)) = " + Parser.eval("1+(5-2*(13/6))"));
    }
}
Тестирующий код (для вашего решения)

public class ParserTest {
    public static void main(String[] args) {
        System.out.println(">> 123 = " + Parser.eval("123"));
        System.out.println(">> 2*3 = " + Parser.eval("2*3"));
        System.out.println(">> (1+3)*2 = " + Parser.eval("(1+3)*2"));
        System.out.println(">> ((13/6)*2-5)+1 = " + Parser.eval("((13/6)*2-5)+1"));
    }
}
Демонстрация работы метода Character.isDidit(...)

public class IsDigitDemo {
    public static void main(String[] args) {
        System.out.println(Character.isDigit('0'));
        System.out.println(Character.isDigit('2'));
        System.out.println(Character.isDigit('4'));
        System.out.println(Character.isDigit('5'));
        System.out.println(Character.isDigit('7'));
        System.out.println(Character.isDigit('9'));
        System.out.println(Character.isDigit(' '));
        System.out.println(Character.isDigit('A'));
        System.out.println(Character.isDigit('!'));
    }
}
>> true
>> true
>> true
>> true
>> true
>> true
>> false
>> false
>> false
Демонстрация работы метода Integer.valueOf(...)

public class ValueOfDemo {
    public static void main(String[] args) {
        int value0 = Integer.valueOf("123");
        System.out.println(value0);

        int value1 = Integer.valueOf("abc");
        System.out.println(value1);
    }
}
>> 123
>> ... java.lang.NumberFormatException: For input string: "abc" ...
Демонстрация работы метода String.substring(...)

public class SubstringDemo {
    public static void main(String[] args) {
        System.out.println("Hello!".substring(0, 6));
        System.out.println("Hello!".substring(0, 5));
        System.out.println("Hello!".substring(0, 4));
        System.out.println("Hello!".substring(0, 3));
        System.out.println();
        System.out.println("Hello!".substring(0, 6));
        System.out.println("Hello!".substring(1, 6));
        System.out.println("Hello!".substring(2, 6));
        System.out.println("Hello!".substring(3, 6));
        System.out.println();
        System.out.println("Hello!".substring(0, 6));
        System.out.println("Hello!".substring(1, 5));
        System.out.println("Hello!".substring(2, 3));
    }
}
>> Hello!
>> Hello
>> Hell
>> Hel
>>
>> Hello!
>> ello!
>> llo!
>> lo!
>>
>> Hello!
>> ello
>> ll
 */
public class Parser {
    public static int eval(String expr) {
        return eval(expr, expr.length() - 1, 0);
    }

    private static int eval(String expr, int from, int to) {
        if (expr.charAt(from) == ')') {
            return eval(expr, from - 1, to + 1);
        } else {
            int pos = from;

            while (pos > to) {
                if (Character.isDigit(expr.charAt(pos))) {
                    pos--;
                } else {
                    int rightOperand = Integer.valueOf(expr.substring(pos + 1, from+1));
                    char operation = expr.charAt(pos);
                    int leftOperand = eval(expr, pos - 1, to);

                    switch (operation) {
                        case '+':
                            return leftOperand + rightOperand;
                        case '-':
                            return leftOperand - rightOperand;
                        case '*':
                            return leftOperand * rightOperand;
                        case '/':
                            return leftOperand / rightOperand;
                    }
                }
            }
            return Integer.valueOf(expr.substring(to, from + 1));
        }
    }
}
