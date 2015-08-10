package delicacies.lab11lookFor;
/*
Реализовать функцию, которая по целочисленому аргументу c возвращает количество целочисленных решений неравенства
a*a + b*b <= c a > 0 b > 0
Например
lookFor(14) == 8
поскольку для решения подходят следующие пары значений
(a=1,b=1), (a=1,b=2), (a=1,b=3), (a=2,b=1), (a=2,b=2), (a=2,b=3), (a=3,b=1), (a=3,b=2)
 */
public class MathUtils {
    public static int lookFor(int max) {
        int midle = max / 2;
        int base = 1;
        int counter = 0;

        for ( int i = midle; i > 0; i-- ) {
            if ( i * i <= max ) {
                base = i;
                i = 0;
            }
        }

        for ( int i = 1; i <= base; i++ ) {
            for ( int j = 1, k = i * i; j <= base; j++ ) {
                if ( (k + j * j)  <= max ) {
                    counter += 1;
                }
            }
        }

        return counter;
    }
}
