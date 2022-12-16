package myArrayListImplementation;

import java.util.Iterator;

public class MyArrayList<E> extends MyAbstractList<E> {
    private static final int INITIAL_CAPACITY = 16;
    private E[] data = (E[]) new Object[INITIAL_CAPACITY];

    public MyArrayList() {
    }

    public MyArrayList(E[] objects) {
        for (int i = 0; i < objects.length; i++) {
            add(objects[i]);
        }
    }

    @Override
    public void add(int index, E e) {
        ensureCapacity();
        for (int i = size - 1; i >= index; i--) {
            data[i + 1] = data[i];
        }
        data[index] = e;
        size++;
    }

    private void ensureCapacity() {
        if (size >= data.length) {
            E[] newData = (E[]) (new Object[size * 2 + 1]);
            for (int i = 0; i < size; i++) {
                newData[i] = data[i];
            }
            data = newData;
        }
    }

    @Override
    public E get(int index) {
        checkIndex(index);
        return data[index];
    }

    public void checkIndex(int index) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException("index " + index + "out of bounds");
    }
    public String toString(){
        StringBuilder result = new StringBuilder("[");
        for (int i = 0; i < size; i++) {
            result.append(data[i]);
            if(i < size - 1)
                result.append(", ");
        }
        return result.toString() + "]";
    }

    @Override
    public Object set(int index, E e) {
        checkIndex(index);
        E old = data[index];
        data[index] = e;
        return old;
    }

    @Override
    public E remove(int index) {
        checkIndex(index);
        E e = data[index];
        for (int j = index; j < size - 1; j++) {
            data[j] = data[j + 1];
        }
        data[size - 1] = null;
        size--;
        return e;
    }

    @Override
    public int indexOf(E e) {
        for (int i = 0; i < size; i++) {
            if(e.equals(data[i]))
                return i;
        }
        return -1;
    }

    @Override
    public Iterator<E> iterator() {
        return new ArrayListIterator();
    }

    private class ArrayListIterator implements java.util.Iterator<E> {
        private int current = 0;
        @Override
        public boolean hasNext(){
            return (current < size);
        }

        @Override
        public E next() {
            return data[current++];
        }
        @Override
        public void remove(){
            MyArrayList.this.remove(current);
        }
    }
}
