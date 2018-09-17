package ru.karamoff.mcdrive.models;

import lombok.*;

@Getter
@Setter
@Builder
@ToString
@EqualsAndHashCode
public class Ingredient {
    private Integer id;
    private String name;
    private Boolean available;
}
