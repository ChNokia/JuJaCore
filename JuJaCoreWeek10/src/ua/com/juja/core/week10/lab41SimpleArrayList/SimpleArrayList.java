package ua.com.juja.core.week10.lab41SimpleArrayList;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

/*
Интерфейс SimpleList < E > - это упрощенная версия java.util.List.
В классе SimpleArrayList < E > уже реализована большая часть методов интерфейса SimpleList < E > . Необходимо закончить реализацию SimpleList < E > и переопределить следующие методы класса SimpleArrayList < E >:
1) public Iterator iterator() - метод возвращает экземпляр класса, который реализовует стандартный интерфейс Iterator < E > из SDK . При попытке удалить элемент итератора до вызова метода next() - кидать java.lang.IllegalArgumentException
Код интерфейса:

public interface Iterator {
    boolean hasNext(); - метод возвращает true когда next() может вернуть элемент,
                         иначе - false.
    E next();          - метод возвращает следующий элемент, если элементов нету
                         NoSuchElementException
    void remove();     - метод удаляет последний возвращенный элемент, если итератор еще не возвращал
                         элемента еще нету - IllegalStateException
}
Или детальнее в SDK.
2) public String toString() - метод, который возвращает строку которая является конкатенацией всех элементов коллекции. Ожидается следующий формат "[1, 2, 3, 4, 5]", "[]" - для пустого списка.
3) public boolean equals(Object o) - позволяет сравнить с другим  экземпляром класса.
4) public int hashCode() - метод возвращает хеш-код. Хеш код должен соответствовать следующим правилам: Одинаковые экземпляры класса должны иметь одинаковый хеш-код. Разные экземпляры класса имеют разный хеш-код (минимизировать вероятность возникновения коллизии).
 */
public class SimpleArrayList <E> implements SimpleList<E> {

    private static final int DEFAULT_INITIAL_CAPACITY = 16;
    private E[] data;
    private int size = 0;

    public SimpleArrayList() {
        this(DEFAULT_INITIAL_CAPACITY);
    }

    public SimpleArrayList(int initialCapacity) {
        this.data = (E[]) new Object[initialCapacity];
    }

    @Override
    public boolean add(E newElement) {
        ensureCapacity(size + 1);
        data[size] = newElement;
        size++;
        return true;
    }

    @Override
    public E get(int index) {
        rangeCheck(index);
        return data[index];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public E remove(int index) {
        rangeCheck(index);
        E oldValue = data[index];
        int numMoved = size - index - 1;
        System.arraycopy(data, index + 1, data, index, numMoved);
        data[--size] = null;
        return oldValue;
    }

    private void rangeCheck(int index) {
        if (index < 0 || size < index) {
            throw new ArrayIndexOutOfBoundsException();
        }
    }

    private void ensureCapacity(int minCapacity) {
        if (minCapacity > data.length) {
            int newCapacity = Math.max(minCapacity, data.length + (data.length >> 1));
            E[] newData = (E[]) new Object[newCapacity];
            System.arraycopy(data, 0, newData, 0, data.length);
            this.data = newData;
        }
    }

    /*BODY*/
    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private int index = 0;

            @Override
            public boolean hasNext() {
                return !(index == size);
            }

            @Override
            public E next() {
                if ( index == size ) {
                    throw new NoSuchElementException();
                }

                index += 1;
                return data[index - 1];
            }
            @Override
            public void remove() {
                if(index <= 0) {
                    throw new IllegalStateException();
                }

                for ( int i = index; i < size; i++ ) {
                    data[i - 1] = data[i];
                }

                size -= 1;
                data[size] = null;
            }
        };
    }

    @Override
    public String toString() {
        String result = "[";
        int last = size - 1;

        if ( size == 0 ) {
            return result = "[" + "]";
        }

        for ( int i = 0; i < last; i++ ) {
            result += data[i].toString() + ", ";
        }

        result += data[last] + "]";

        return result;
    }

    @Override
    public boolean equals(Object o) {
        if ( o instanceof SimpleArrayList ) {
            SimpleArrayList<E> other = (SimpleArrayList<E>)o;

            if ( size != other.size ) {
                return false;
            }

            for ( int i = 0; i < size; i++ ) {
                if ( !data[i].equals(other.data[i]) ) {
                    return false;
                }
            }

            return true;
        }

        return false;
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(data);
    }


}

interface SimpleList<E> {
    public boolean add(E newElement);
    public E get(int index);
    public Iterator<E> iterator();
    public int size();
    public boolean isEmpty();
    public E remove(int index);
}
