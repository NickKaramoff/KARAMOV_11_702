package ru.karamoff.mcdrive.repositories;

import ru.karamoff.mcdrive.models.Foodpiece;

import java.util.List;

public interface FoodpieceRepository extends CrudRepository<Foodpiece, Long> {
    List<Foodpiece> findByName(String name);
}
