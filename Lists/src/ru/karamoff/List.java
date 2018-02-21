package ru.karamoff;

public interface List<T> extends Collection<T> {
    T get(int index);
    void addToBegin(T object);
}
