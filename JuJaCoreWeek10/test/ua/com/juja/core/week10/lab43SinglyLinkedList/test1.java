package ua.com.juja.core.week10.lab43SinglyLinkedList;

public class test1 {
    public static void main(String[] args) {
        SinglyLinkedList<Integer> list = new SinglyLinkedList<Integer>();
        Integer expectedElement = 7;
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(7);
        list.add(11);
        list.add(34);
        list.add(18);

        Integer actualElement = FinderElements.findElement(list, 3);

        if (actualElement == null)
            throw new AssertionError("result findElement()  is not null");

        if (expectedElement.compareTo(actualElement) != 0)
            throw new AssertionError("expected element equals " + expectedElement.toString() + " but found " + actualElement.toString());

        System.out.print("OK");

    }
}
