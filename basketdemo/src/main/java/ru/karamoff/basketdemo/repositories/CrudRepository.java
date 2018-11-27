package ru.karamoff.basketdemo.repositories;

import java.util.List;

public interface CrudRepository<T, V> {
    void save(V model);

    void update(V model);

    void delete(T id);

    V findOne(T id);

    List<V> findAll();
}