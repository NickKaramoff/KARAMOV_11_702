package ru.karamoff.mcdrive.forms;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FoodpieceForm {
    private String name;
    private Float cost;
    private Boolean available;
}
