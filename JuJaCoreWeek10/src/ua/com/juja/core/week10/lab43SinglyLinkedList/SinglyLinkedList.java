package ua.com.juja.core.week10.lab43SinglyLinkedList;

import java.util.Iterator;
import java.util.NoSuchElementException;

/*
В классе SinglyLinkedList реализован односвязный список.
В этом классе реализован интерфейс Iterator.
Необходимо реализовать поиск K-элемента с конца списка при помощи Iterator.
Для этого нужно реализовать метод Integer findElement(SinglyLinkedList singleList, int k)
На вход принимает экземпляр SinglyLinkedList и номер элемента который необходимо найти с конца списка. Отсчет элементов с конца начинается с 0.
На выходе метод возвращает найденный элемент, если элемента не существует - IndexOutOfBoundsException.
 */

public class SinglyLinkedList <T> {
    private Node<T> first = null;

    public void add(T element) {
        if (first == null) {
            first = new Node<T>(element);

        } else {
            Node<T> current = getLast();
            current.next = new Node<T>(element);
        }
    }

    public Iterator<T> iterator() {
        return new Itr();
    }

    private class Itr implements Iterator<T> {
        Node<T> current = first;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public T next() {
            if (current == null) throw new IndexOutOfBoundsException();
            if (current.element == null) throw new NoSuchElementException();
            T element = current.element;
            current = current.next;
            return element;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    private Node<T> getLast() {
        Node<T> last = first;
        while (last.next != null) {
            last = last.next;
        }
        return last;
    }

    private class Node<T> {
        T element;
        Node<T> next;

        Node(T element) {
            this.element = element;
            this.next = null;
        }
    }
}

class FinderElements {
    public static Integer findElement(SinglyLinkedList<Integer> singlyLinkedList, int k) {
         /*BODY*/
        SinglyLinkedList<Integer> reversList = new SinglyLinkedList<>();
        int count = 0;

        for ( Iterator<Integer> iterator = singlyLinkedList.iterator(); iterator.hasNext(); ) {
            iterator.next();
            count += 1;
        }

        int n = count - k - 1;

        for ( Iterator<Integer> iterator = singlyLinkedList.iterator(); iterator.hasNext(); ) {
            if ( n  == 0 ) {
                return iterator.next();
            }

            n -= 1;
            iterator.next();
        }

        throw new IndexOutOfBoundsException();
    }

}
