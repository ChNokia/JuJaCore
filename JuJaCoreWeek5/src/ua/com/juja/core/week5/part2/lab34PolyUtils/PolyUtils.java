package ua.com.juja.core.week5.part2.lab34PolyUtils;
import java.math.BigInteger;

public class PolyUtils {
    public static void main(String[] args) {
        BigInteger[] data = new BigInteger[] {new BigInteger("1"),
                                              new BigInteger("2"),
                                              new BigInteger("3"),
                                              new BigInteger("4"),
                                            };
        BigInteger[] data1 = new BigInteger[] {new BigInteger("10"),
                                              new BigInteger("0"),
                                              new BigInteger("-100"),
                                            };

        System.out.println(mul(data, data1));
    }

    public static BigInteger[] mul(BigInteger[] x, BigInteger[] y) {
        BigInteger[] result = new BigInteger[x.length > y.length ? x.length : y.length];

        for ( int i = 0, j = 0; i <  x.length; i++, j++ ) {
            BigInteger sumBigInteger = new BigInteger("0");

            for ( int k = 0; k < y.length; k++ ) {
                sumBigInteger.add(x[i].multiply(y[k]));
            }
            result[j] = sumBigInteger;
        }

        return result;
    }
}
