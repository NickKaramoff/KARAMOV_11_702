package ru.karamoff.basketdemo.services;

import ru.karamoff.basketdemo.models.Item;

import java.util.List;
import java.util.UUID;

public interface BasketService {

    Item getItemFromBasket(Long id, UUID basketUuid);

    void addItemToBasket(Item item, UUID basketUuid);

    void removeItemFromBasket(Item item, UUID basketUuid);

    List<Item> getAllItemsInBasket(UUID basketUuid);

    boolean basketExists(UUID basketUuid);

    UUID createNewBasket();
}
