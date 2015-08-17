package ua.com.juja.core.week5.part2.lab35StringUtils;

/*
Реализовать функцию, которая делает циклический сдвиг букв вправо, то есть rightShift("ABCDE", 1) = "EABCD" rightShift("ABCDE", 2) = "DEABC" rightShift("ABCDE", 3) = "CDEAB" rightShift("ABCDE", 4) = "BCDEA" Допустимо сдвигать на расстояние больше длины строки rightShift("ABCDE", 5) = "ABCDE" rightShift("ABCDE", 6) = "EABCD" rightShift("ABCDE", 7) = "DEABC" Допустимо сдвигать на отрицательное расстояние (выходит сдвиг влево) rightShift("ABCDE", -1) = "BCDEA" rightShift("ABCDE", -2) = "CDEAB" rightShift("ABCDE", -7) = "CDEAB". Сдвиг на расстояние 0 и сдвиги строк длиной 0 и 1 символ - разрешены.
 */
public class StringUtils {
    public static void main(String[] args) {
        String a = rightShift("ABCDE", 1);

        System.out.println(rightShift("ABCDE", 1).equals("EABCD"));
        System.out.println(rightShift("ABCDE", 2).equals("DEABC"));
        System.out.println(rightShift("ABCDE", 3).equals("CDEAB"));
        System.out.println(rightShift("ABCDE", 4).equals("BCDEA"));
        System.out.println(rightShift("ABCDE", 5).equals("ABCDE"));
        System.out.println(rightShift("ABCDE", 6).equals("EABCD"));
        System.out.println(rightShift("ABCDE", 7).equals("DEABC"));
        System.out.println(rightShift("ABCDE", -1).equals("BCDEA"));
        System.out.println(rightShift("ABCDE", -2).equals("CDEAB"));
        System.out.println(rightShift("ABCDE", -7).equals("CDEAB"));
    }

    public static String rightShift(String arg, int delta) {
        char[] argChars = arg.toCharArray();
        int len = arg.length() - 1;
        int middle;

        if ( arg.length() == 0 ) {
            return "";
        }

        delta %= arg.length();

        if ( delta < 0 ) {
            delta += arg.length();
        }

        middle = arg.length() - delta;

        if ( delta <= middle ) {
            char[] temp = new char[delta];

            for ( int i = 0, j = middle; i < delta; i++, j++ ) {
                temp[i] = argChars[j];
            }
            for ( int i = len - delta, j = len; j >= delta; j--, i-- ) {
                argChars[j] = argChars[i];
            }
            for ( int i = 0; i < delta; i++ ) {
                argChars[i] = temp[i];
            }
        } else {
            char[] temp = new char[middle];

            for ( int i = 0; i < middle; i++ ) {
                temp[i] = argChars[i];
            }
            for ( int i = 0, j = middle; i < delta; i++, j++ ) {
                argChars[i] = argChars[j];
            }
            for ( int i = middle - 1, j = arg.length() - 1; i >= 0; i--, j-- ) {
                argChars[j] = temp[i];
            }
        }

        return new String(argChars);
    }
}
