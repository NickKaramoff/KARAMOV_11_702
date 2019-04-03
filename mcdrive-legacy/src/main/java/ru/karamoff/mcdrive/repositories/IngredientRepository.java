package ru.karamoff.mcdrive.repositories;

import ru.karamoff.mcdrive.models.Ingredient;

import java.util.List;

public interface IngredientRepository extends CrudRepository<Ingredient, Long> {
    List<Ingredient> findByName(String name);
}
