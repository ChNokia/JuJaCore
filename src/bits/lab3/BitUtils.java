package bits.lab3;

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
