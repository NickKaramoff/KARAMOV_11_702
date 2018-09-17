package ru.karamoff.mcdrive.models;

import lombok.*;

@Getter
@Setter
@Builder
@ToString
@EqualsAndHashCode
public class FoodpieceOrder {
    private Integer id;
    private Integer foodpieceId;
    private Integer orderId;
    private Boolean ready;
}
