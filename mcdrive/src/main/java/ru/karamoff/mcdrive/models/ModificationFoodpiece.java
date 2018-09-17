package ru.karamoff.mcdrive.models;

import lombok.*;

@Getter
@Setter
@Builder
@ToString
@EqualsAndHashCode
public class ModificationFoodpiece {
    private Integer modificationId;
    private Integer foodpieceId;
}
