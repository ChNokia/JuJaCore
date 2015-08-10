package week2.sort.lab15InsertionSorter;

public class InsertionSorter {
    public static void sort(int[] arr) {
        for (int k = 1; k < arr.length; k++) {
            int newElement = arr[k];
            int location = k - 1;
            int counterShift = 0;

            while (location >= 0 && arr[location] > newElement) {
                counterShift++;
                location--;
            }

            int srcPos = location + 1;

            System.arraycopy(arr, srcPos, arr, srcPos + 1, counterShift);
            arr[srcPos] = newElement;
        }
    }
}
