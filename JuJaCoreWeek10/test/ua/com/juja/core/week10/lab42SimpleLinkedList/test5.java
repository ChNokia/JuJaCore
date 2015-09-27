package ua.com.juja.core.week10.lab42SimpleLinkedList;

public class test5 {
    /*public static void main(String[] args) {
        Integer[] testElement  = {1,2,3,4,5,6,7,8};
        //first array list
        SimpleLinkedList<Integer> firstSimpleLinkedList = new SimpleLinkedList<>();
        for (int i = 0; i <testElement.length ; i++) {
            firstSimpleLinkedList.add(testElement[i]);
        }

        //second array list
        SimpleLinkedList<Integer> secondSimpleLinkedList = new SimpleLinkedList<>();

        for (int i = 0; i <testElement.length-2; i++) {
            secondSimpleLinkedList.add(testElement[i]);
        }


        //check
        if (firstSimpleLinkedList.hashCode()==secondSimpleLinkedList.hashCode())
            throw new AssertionError("Two different simpleLinkedList hasCode should be not equals");


        System.out.print("OK");
    }*/
    public static void main(String[] args) {
        //first array list
        SimpleLinkedList<Integer> firstSimpleLinkedList = new SimpleLinkedList<>();

        //second array list
        SimpleLinkedList<Integer> secondSimpleLinkedList = new SimpleLinkedList<>();


        //check
        if (!(firstSimpleLinkedList.hashCode()==secondSimpleLinkedList.hashCode()))
            throw new AssertionError("Two empty simpleLinkedList with identical type should be equals");


        System.out.print("OK");
    }
}
