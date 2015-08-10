package arrays.lab1inverter;

public class ArrayInverter {
    public static void invert(int[] arr) {
        for ( int k = arr.length / 2 - 1; k >= 0; k-- ) {
            int temp = arr[k];
            arr[k] = arr[arr.length - k - 1];
            arr[arr.length - k - 1] = temp;
        }
    }
}
