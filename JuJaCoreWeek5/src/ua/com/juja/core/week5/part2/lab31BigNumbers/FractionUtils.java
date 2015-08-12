package ua.com.juja.core.week5.part2.lab31BigNumbers;

/*
Предположим, что мы работаем с дробями, представляя их как пару BigInteger-ов. 2/3 будем представлять как BigInteger[] x = {new BigInteger("2"), new BigInteger("3")}; Реализовать метод, который обеспечивает - сложение дробей 1/3 + 1/3 = 2/3 1/2 + 1/3 = 5/6 - итоговая дробь должна быть несократимой 1/2 + 1/2 = 1/1 (а не 2/2) 2/3 - 1/6 = 1/2 (а не 3/6) 1/2 + (-1)/2 = 0/1 (а не 0/2) --- P.S. Возможно, вам потребуется метод BigInteger.gcd().
 */

import java.math.BigInteger;

public class FractionUtils {
    public static void main(String[] args) {
        System.out.println("1/3 + 1/3");
        test_fraction("2/3", sum(new BigInteger[]{new BigInteger("1"), new BigInteger("3")}, new BigInteger[]{new BigInteger("1"), new BigInteger("3")}));
        System.out.println("1/2 + 1/3");
        test_fraction("5/6", sum(new BigInteger[]{new BigInteger("1"), new BigInteger("2")}, new BigInteger[]{new BigInteger("1"), new BigInteger("3")}));
        System.out.println("1/2 + 1/2");
        test_fraction("1/1", sum(new BigInteger[]{new BigInteger("1"), new BigInteger("2")}, new BigInteger[]{new BigInteger("1"), new BigInteger("2")}));
        System.out.println("2/3 - 1/6");
        test_fraction("1/2", sum(new BigInteger[]{new BigInteger("2"), new BigInteger("3")}, new BigInteger[]{new BigInteger("-1"), new BigInteger("6")}));
        System.out.println("1/2 + (-1)/2");
        test_fraction("0/1", sum(new BigInteger[]{new BigInteger("1"), new BigInteger("2")}, new BigInteger[]{new BigInteger("-1"), new BigInteger("2")}));
    }

    public static BigInteger[] sum(BigInteger[] x, BigInteger[] y) {
        BigInteger[] result = new BigInteger[]{new BigInteger("0"), new BigInteger("1")};
        BigInteger commonВenominator = lcm(x[1], y[1]);
        BigInteger numerator = x[0].multiply(commonВenominator.divide(x[1])).add(y[0].multiply(commonВenominator.divide(y[1])));

        if ( result[0].compareTo(numerator) == 0 ) {
            return result;
        }

        BigInteger gcdBigInteger = numerator.gcd(commonВenominator);

        result[0] = numerator.divide(gcdBigInteger);
        result[1] = commonВenominator.divide(gcdBigInteger);

        return result;
    }

    public static BigInteger lcm(BigInteger a, BigInteger b) {
        return (a.divide(b.gcd(a))).multiply(b);
    }

    public static void test_fraction(String expect, BigInteger[] result) {
        String resultString = result[0] + "/" + result[1];

        System.out.println("Expected: " + expect + " ===> result: " + result[0] + "/" + result[1] + "  " + expect.equals(resultString) );
    }
}
