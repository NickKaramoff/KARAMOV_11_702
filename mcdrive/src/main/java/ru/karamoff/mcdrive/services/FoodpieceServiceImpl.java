package ru.karamoff.mcdrive.services;

import ru.karamoff.mcdrive.forms.FoodpieceForm;
import ru.karamoff.mcdrive.models.Foodpiece;
import ru.karamoff.mcdrive.repositories.FoodpieceRepository;

public class FoodpieceServiceImpl implements FoodpieceService {

    private FoodpieceRepository repository;

    public FoodpieceServiceImpl(FoodpieceRepository repository) {
        this.repository = repository;
    }

    @Override
    public void addFoodpiece(FoodpieceForm foodpieceForm) {
        Foodpiece foodpiece = Foodpiece.builder()
                .name(foodpieceForm.getName())
                .cost(foodpieceForm.getCost())
                .available(foodpieceForm.getAvailable())
                .build();

        repository.save(foodpiece);
    }
}
