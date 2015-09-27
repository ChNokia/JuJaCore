package ua.com.juja.core.week10.lab42SimpleLinkedList;

public class test3 {
    public static void main(String[] args) {

        //first array list
        SimpleLinkedList<Integer> firstSimpleLinkedList = new SimpleLinkedList<>();

        //second array list
        SimpleLinkedList<Integer> secondSimpleLinkedList = new SimpleLinkedList<>();


        //check
        if (!firstSimpleLinkedList.equals(secondSimpleLinkedList))
            throw new AssertionError("Two empty simpleLinkedList with identical type should be equals");


        System.out.print("OK");
    }
}
