package ua.com.juja.core.week10.lab42SimpleLinkedList;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class test2 {
    public static void main(String[] args) {

        // list is one element
        Integer[] expectedElements = {1, 2, 3, 4, 5, 6, 7};

        SimpleLinkedList<Integer> simpleLinkedList = new SimpleLinkedList<>();

        for (int i = 0; i < expectedElements.length; i++) {
            simpleLinkedList.add(expectedElements[i]);
        }

        //call and check
        Iterator<Integer> iterator = null;
        try {
            iterator = simpleLinkedList.iterator();
        } catch (UnsupportedOperationException u) {
            throw new AssertionError("Iterator not implemented");
        }

        if (iterator == null)
            throw new AssertionError("Iterator must be no equals null");

        // call before get element
        if (!iterator.hasNext())
            throw new AssertionError("hasNext() must be returned true if list is not empty");

        boolean isExceptionMethodNext = false;
        Integer[] actualElements = new Integer[expectedElements.length];

        try {
            int i = 0;
            while (iterator.hasNext()) {
                if (i < actualElements.length)
                    actualElements[i++] = iterator.next();
                else
                    throw new AssertionError("length actual data more  length expected data");
            }

        } catch (NoSuchElementException e) {
            isExceptionMethodNext = true;
        }

        if (isExceptionMethodNext)
            throw new AssertionError("next() throw NoSuchElementException when list is not empty");

        if (!Arrays.equals(actualElements, expectedElements))
            throw new AssertionError("actual data is not equal to expected data");


        System.out.print("OK");
    }
}
