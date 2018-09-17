package ru.karamoff.mcdrive.models;

import lombok.*;

@Getter
@Setter
@Builder
@ToString
@EqualsAndHashCode
public class IngredientFoodpiece {
    private Integer ingedientId;
    private Integer foodpieceId;
    private Integer amount;
}
