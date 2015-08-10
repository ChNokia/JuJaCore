package week2.sort.lab14BubbleSorter;

public class BubbleSorter {
    public static void sort(int[] arr) {
        for ( int i = 0, last = arr.length - 1; i < last; i++ ) {
            boolean isIter = false;

            for ( int j = last; j > i; j-- ) {
                if ( arr[j] < arr[j - 1] ) {
                    int temp = arr[j];
                    arr[j] = arr[j-1];
                    arr[j - 1] = temp;
                    isIter = true;
                }
            }

            if ( !isIter ) {
                break;
            }
        }
    }
}
