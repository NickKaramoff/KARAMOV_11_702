package ru.karamoff.mcdrive.models;

import lombok.*;

@Getter
@Setter
@Builder
@ToString
@EqualsAndHashCode
public class ModificationFoodpieceOrder {
    private Integer modificationId;
    private Integer foodpieceOrderId;
    private Integer amount;
    private Float extraCost;
}
