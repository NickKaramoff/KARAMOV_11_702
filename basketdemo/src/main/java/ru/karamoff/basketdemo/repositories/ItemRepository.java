package ru.karamoff.basketdemo.repositories;

import ru.karamoff.basketdemo.models.Item;

import java.util.List;
import java.util.UUID;

public interface ItemRepository extends CrudRepository<Long, Item> {
    List<Item> findAllByBasketUuid(UUID basketUuid);

    Item findOneFromBasket(Long id, UUID basketUuid);
}
