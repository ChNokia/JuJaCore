package ua.com.juja.core.weeks.sort.lab15InsertionSorter;
/*
Такая версия алгоритм сортировки вставками

public class InsertionSorter {
    public static void sort(int[] arr) {
        for (int k = 1; k < arr.length; k++) {
            int newElement = arr[k];
            int location = k - 1;
            while (location >= 0 && arr[location] > newElement) {
                arr[location + 1] = arr[location];
                location--;
            }
            arr[location + 1] = newElement;
        }
    }
}
реализована не оптимально, так как внутренний цикл while
- ищет позицию для вставки, перебирая последовательно элементы, при этом он
- поэлементно "смещает" массив.

В целях оптимизации перепишите алгоритм:
1. Ищите позицию для вставки элемента бинарным поиском (Arrays.binarySearch(...)).
2. Найдя позицию - смещайте всю часть массива за один вызов (System.arraycopy(...)).
В моих экспериментах эти две оптимизации ускорили сортировку в 2.2-2.6 раза. Скорость сортировки измерял данным кодом

import java.util.Random;

public class App {
    public static void main(String[] args) {
        int[] array = new int[256 * 1024];
        Random rnd = new Random(0);
        for (int k = 0; k < array.length; k++) {
            array[k] = rnd.nextInt();
        }
        long t1 = System.nanoTime();
        InsertionSorter.sort(array);
        System.out.println("A:" + (System.nanoTime() - t1) / 1_000_000);
    }
}
 */
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
