package arrays.lab3merge;
/*
Необходимо корректно реализовать слияние сортированных массивов

public class Merger {
    // wrong
    public static int[] merge(int[] fst, int[] snd) {
        int[] result = new int[fst.length + snd.length];
        int fstIndex = 0;
        int sndIndex = 0;
        while (fstIndex + sndIndex != result.length) {
            if (fst[fstIndex] < snd[sndIndex]) {
                result[fstIndex + sndIndex] = fst[fstIndex++];
            } else {
                result[fstIndex + sndIndex] = snd[sndIndex++];
            }
        }
        return result;
    }
}
В данной заготовке метод merge реализован с ошибкой - он корректно сливает, но некорректно обрабатывает ситуацию, когда один из массивов полностью выбран.
1. Исправьте ошибку.
Дополнительные улучшения:
2. Попробуйте минимизировать количество операций (сравнения, сумма, присвоение, ...) на один "сливаемый" элемент ("сравнения" намного дороже арифметических операций).
Сейчас в коде 6 операций:

- fstIndex + sndIndex
- ... != result.length
- fst[fstIndex] < snd[sndIndex]
- fstIndex + sndIndex
- fstIndex++
- result[...] = fst[...]
 */
public class Merger {
    public static int[] merge(int[] fst, int[] snd) {
        int[] result = new int[fst.length + snd.length];
        int fstIndex = 0;
        int sndIndex = 0;

        while ( fstIndex < fst.length && sndIndex < snd.length ) {
            if ( fst[fstIndex] < snd[sndIndex] ) {
                result[fstIndex + sndIndex] = fst[fstIndex++];
            } else {
                result[fstIndex + sndIndex] = snd[sndIndex++];
            }
        }
        while ( fstIndex < fst.length ) {
            result[fstIndex + sndIndex] = fst[fstIndex++];
        }
        while ( sndIndex < snd.length ) {
            result[fstIndex + sndIndex] = snd[sndIndex++];
        }


        return result;
    }
}
