package ru.karamoff.mcdrive.models;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@ToString
@EqualsAndHashCode
public class Order {
    private Integer id;
    private Float sum;
    private LocalDateTime time;
    private Boolean paid;
    private Boolean ready;
}
