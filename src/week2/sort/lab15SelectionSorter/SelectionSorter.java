package week2.sort.lab15SelectionSorter;

public class SelectionSorter {
    public static void sort(int[] arr) {
        for (int barrier = 0; barrier < arr.length - 1; barrier++) {
            int minIndex = barrier;
            int min = arr[minIndex];

            for (int index = barrier + 1; index < arr.length; index++) {
                if (min > arr[index]) {
                    min = arr[index];
                    minIndex = index;
                }
            }

            arr[minIndex] = arr[barrier];
            arr[barrier] = min;
        }
    }
}
