package ru.karamoff.basketdemo.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Item {
    private Long id;        // идентификатор товара
    private String name;    // название товара
    private Double price;   // цена товара
    private UUID basketUuid;// куки корзины
                            // null если вызывается список всех товаров
    private Integer amount; // количество товара в корзине
                            // null если вызывается список всех товаров
}
