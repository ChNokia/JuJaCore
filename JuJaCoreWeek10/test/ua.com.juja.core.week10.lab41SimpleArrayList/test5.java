package ua.com.juja.core.week10.lab41SimpleArrayList;

public class test5 {
    public static void main(String[] args) {
        Integer[] expectedElements = {1, 2, 3, 4, 5, 6, 7};
        String expectedPrintString = "[1, 2, 3, 4, 5, 6, 7]";

        SimpleArrayList<Integer> simpleArrayList = new SimpleArrayList<>();

        for (int i = 0; i < expectedElements.length; i++) {
            simpleArrayList.add(expectedElements[i]);
        }

        //call

        String actualPrintString = simpleArrayList.toString();

        //check
        if (!expectedPrintString.equals(actualPrintString))
            throw new AssertionError("Should be printed " + expectedPrintString + " but printed " + actualPrintString);

        System.out.print("OK");
    }
}
