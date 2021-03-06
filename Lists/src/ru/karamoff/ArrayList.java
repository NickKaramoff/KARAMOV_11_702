package ru.karamoff;

import java.util.Iterator;

public class ArrayList<T> implements List<T> {

    private static final int DEFAULT_SIZE = 10;

    private T elements[];
    private int count;

    public ArrayList() {
        this.elements = (T[]) (new Object[DEFAULT_SIZE]);
        this.count = 0;
    }

    @Override
    public T get(int index) {
        if (index < this.count) {
            return elements[index];
        } else throw new IllegalArgumentException();
    }

    @Override
    public void addToBegin(T object) {

    }

    @Override
    public void add(T element) {
        if (this.count < this.elements.length) {
            this.elements[count++] = element;
        } else throw new IllegalArgumentException();
    }

    @Override
    public void remove(T element) {
        for (int i = 0; i < count; i++) {
            if (element.equals(elements[i])) {
                for (int j = i; j < count - 1; j++) {
                    elements[j] = elements[j + 1];
                }
                count--;
                return;
            }
        }
    }

    @Override
    public boolean contains(T element) {
        for (int i = 0; i < count; i++) {
            if (elements[i].equals(element)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int size() {
        return count;
    }

    private class ArrayListIterator implements Iterator<T> {

        private int index;

        ArrayListIterator() {
            this.index = 0;
        }

        @Override
        public boolean hasNext() {
            return index < count;
        }

        @Override
        public T next() {
            T result = get(index);
            index++;
            return result;
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new ArrayListIterator();
    }
}
