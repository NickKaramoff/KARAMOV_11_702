package ru.karamoff.basketdemo.repositories;

import ru.karamoff.basketdemo.models.Basket;
import ru.karamoff.basketdemo.models.Item;

import java.util.UUID;

public interface BasketRepository extends CrudRepository<Long, Basket> {
    boolean exists(UUID basketUuid);

    void addItemToBasket(Item item, UUID basketUuid);

    void removeItemFromBasket(Item item, UUID basketUuid);

    void updateAmount(Item item, UUID basketUuid, int newAmount);

    Integer getAmount(Item item, UUID basketUuid);
}
