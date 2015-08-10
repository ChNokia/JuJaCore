package week2.matrix.multi;

public class MatrixUtils {
    public static int[][] mul(int[][] fst, int[][] snd) {
        if ( fst == null || snd == null ) {
            throw new IllegalArgumentException();
        }
        if ( fst[0].length != snd.length ) {
            throw new IllegalArgumentException();
        }

        int[][] result = new int[fst.length][snd[0].length];
        int rows = result.length;
        int columns = result[0].length;

        for ( int i = 0; i < rows; i++ ) {
            for ( int j = 0; j < columns; j++ ) {
                int sum = 0;

                for ( int k = 0; k < fst[0].length; k++ ) {
                    sum += fst[i][k] * snd[k][j];
                }

                result[i][j] = sum;
            }
        }
        return result;
    }
}
