package ua.com.juja.core.week5.part2.lab33PolyUtils;

import java.math.BigInteger;

public class PolyUtils {
    public static void main(String[] args) {
        BigInteger[] data = new BigInteger[] {new BigInteger("2"),
                                            new BigInteger("0"),
                                            new BigInteger("-3"),
                                            new BigInteger("0"),
                                            };

        System.out.println(eval(data,  new BigInteger("100")));
    }

    public static BigInteger eval(BigInteger[] poly, BigInteger arg) {
        BigInteger nullBigInteger = new BigInteger("0");
        BigInteger result = nullBigInteger;

        for ( int i = 0; i < poly.length; i++ ) {
            if ( poly[i].compareTo(nullBigInteger) != 0 ) {
                result = result.add(arg.pow(poly.length - i - 1).multiply(poly[i]));
            }
        }

        return result;
    }
}
