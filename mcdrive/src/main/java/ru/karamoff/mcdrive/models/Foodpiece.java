package ru.karamoff.mcdrive.models;

import lombok.*;

@Getter
@Setter
@Builder
@ToString
@EqualsAndHashCode
public class Foodpiece {
    private Integer id;
    private String name;
    private Float cost;
    private Boolean available;
}
