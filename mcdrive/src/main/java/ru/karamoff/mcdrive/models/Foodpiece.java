package ru.karamoff.mcdrive.models;

import lombok.*;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@Builder
@ToString
@EqualsAndHashCode
public class Foodpiece {
    private Long id;
    private String name;
    private Float cost;
    private Boolean available;

    private Map<Ingredient, Integer> ingredients;
    private List<Modification> modifications;
}
