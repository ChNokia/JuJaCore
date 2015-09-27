package ua.com.juja.core.week10.lab42SimpleLinkedList;

public class test6 {
    public static void main(String[] args) {
        Integer[] expectedElements = {1, 2, 3, 4, 5, 6, 7};
        String expectedPrintString = "[1, 2, 3, 4, 5, 6, 7]";

        SimpleLinkedList<Integer> simpleLinkedList = new SimpleLinkedList<>();

        for (int i = 0; i < expectedElements.length; i++) {
            simpleLinkedList.add(expectedElements[i]);
        }

        //call

        String actualPrintString = simpleLinkedList.toString();

        //check
        if (!expectedPrintString.equals(actualPrintString))
            throw new AssertionError("Should be printed " + expectedPrintString + " but printed " + actualPrintString);

        System.out.print("OK");
    }
}
