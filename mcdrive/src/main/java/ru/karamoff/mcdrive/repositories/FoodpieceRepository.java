package ru.karamoff.mcdrive.repositories;

import ru.karamoff.mcdrive.models.Foodpiece;

import java.util.Optional;

public interface FoodpieceRepository extends CrudRepository<Foodpiece, Long> {
    Optional<Foodpiece> findByName(String name);
}
