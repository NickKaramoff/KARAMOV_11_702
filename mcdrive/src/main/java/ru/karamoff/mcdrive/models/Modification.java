package ru.karamoff.mcdrive.models;

import lombok.*;

@Getter
@Setter
@Builder
@ToString
@EqualsAndHashCode
public class Modification {
    private Integer id;
    private String name;
    private Integer ingredientId;
    private Float cost;
}
