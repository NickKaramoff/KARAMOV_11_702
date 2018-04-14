package ru.karamoff;

public interface Tree<T extends Comparable<T>> {
    void insert (T value);
    boolean remove (T value);
    boolean contains(T value);
    void print();
    void printByLevels();
    boolean isBST();
}
