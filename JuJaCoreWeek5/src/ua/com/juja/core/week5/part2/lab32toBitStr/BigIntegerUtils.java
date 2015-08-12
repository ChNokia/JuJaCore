package ua.com.juja.core.week5.part2.lab32toBitStr;

/*
Реализовать метод, который преобразует BigInteger в битовую строку (String из '0' и '1') toBitStr(new BigInteger("2")) = "10" toBitStr(new BigInteger("8")) = "1000" toBitStr(new BigInteger("10")) = "1010" toBitStr(new BigInteger("140")) = "10001100"
 */
import java.math.BigInteger;

public class BigIntegerUtils {
    public static void main(String[] args) {
        toBitStr(new BigInteger("1"));
    }

    public static String toBitStr(BigInteger arg) {
        String result = "";
        BigInteger twoPow;
        BigInteger nulBigInteger = new BigInteger("0");
        int pow = arg.bitLength() - 1;

        if ( pow < 0 ) {
            return "0";
        }

        twoPow = (new BigInteger("2")).pow(pow);

        while ( twoPow.compareTo(nulBigInteger) > 0 ) {
            if ( twoPow.and(arg).compareTo(nulBigInteger) > 0 ) {
                result += "1";
            } else {
                result += "0";
            }

            twoPow = twoPow.shiftRight(1);
        }

        System.out.println(result);
        //System.out.println(arg.bitLength());

        return result;
    }
}
