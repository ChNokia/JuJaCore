package delicacies.lab13ArrayUtils;

public class ArrayUtils {
    public static int[] lookFor(int[] array) {
        int[] result = new int[2];
        int firstIndex = 0;
        int lastIndex = 0;
        int sequenceLength = 0;
        int currentSequenceLength = 0;

        for (int i = 0; i < array.length; i++) {
            if (array[i] > 0) {
                if ( currentSequenceLength == 0 ) {
                    firstIndex = i;
                }

                currentSequenceLength += 1;
                lastIndex = i;
            } else {
                if (currentSequenceLength >= sequenceLength) {
                    sequenceLength = currentSequenceLength;

                    result[0] = firstIndex;
                    result[1] = lastIndex;
                }

                currentSequenceLength = 0;
            }
        }

        if (currentSequenceLength >= sequenceLength) {
            sequenceLength = currentSequenceLength;
            result[0] = firstIndex;
            result[1] = lastIndex;
        }
        if (sequenceLength == 0) {
            return new int[0];
        }

        return result;
    }
}
