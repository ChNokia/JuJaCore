package ua.com.juja.core.week1.part2.bits.lab3;
/*
Реализуйте метод swapBits(b, i, j), который производит "рокировку" битов с номерами i и j, то есть swapBits(0b1111_0000, 0, 6) = 0b1011_0001 swapBits(0b0000_1000, 3, 1) = 0b0000_0010
 */
public class BitUtils {
    public static byte swapBits(byte b, int i, int j) {
        byte first = (byte) (((1 << i) & b) >> i);
        byte second = (byte) ((((1 << j) & b)) >> j);

        byte a = (byte) b;

        if ( first != second ) {
            if ( first == 1 ) {
                first <<= j;

                a = (byte) (b | first);
                a ^= (1 << i);
            } else {
                second <<= i;
                a = (byte) (b | second);
                a ^= (1 << j);
            }
        }

        return a;
    }
}
