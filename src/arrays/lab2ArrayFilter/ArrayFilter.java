package arrays.lab2ArrayFilter;

public class ArrayFilter {
    public static int [] filterEven(int [] nums){
        int []result = new int[nums.length];

        for (int i= 0, k = 0; i< nums.length; i++ ) {
            if ( nums[i] % 2 == 0 ) {
                result[k] = nums[i];
                k += 1;
            }
        }

        return result;
    }
}
