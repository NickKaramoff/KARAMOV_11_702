package ru.karamoff.basketdemo.services;

import ru.karamoff.basketdemo.models.Basket;
import ru.karamoff.basketdemo.models.Item;
import ru.karamoff.basketdemo.repositories.BasketRepository;
import ru.karamoff.basketdemo.repositories.ItemRepository;

import java.util.List;
import java.util.UUID;

public class BasketServiceImpl implements BasketService {

    private final BasketRepository basketRepository;
    private final ItemRepository itemRepository;

    public BasketServiceImpl(BasketRepository basketRepository, ItemRepository itemRepository) {
        this.basketRepository = basketRepository;
        this.itemRepository = itemRepository;
    }


    @Override
    public Item getItemFromBasket(Long id, UUID basketUuid) {
        return itemRepository.findOneFromBasket(id, basketUuid);
    }


    @Override
    public void addItemToBasket(Item item, UUID basketUuid) {
        Integer currAmount = basketRepository.getAmount(item, basketUuid);
        if (currAmount == null) {
            basketRepository.addItemToBasket(item, basketUuid);
        } else {
            basketRepository.updateAmount(item, basketUuid, currAmount + 1);
        }
    }

    @Override
    public void removeItemFromBasket(Item item, UUID basketUuid) {
        Integer currAmount = basketRepository.getAmount(item, basketUuid);
        if (currAmount <= 1) {
            basketRepository.removeItemFromBasket(item, basketUuid);
        } else {
            basketRepository.updateAmount(item, basketUuid, currAmount - 1);
        }
    }


    @Override
    public List<Item> getAllItemsInBasket(UUID basketUuid) {
        return itemRepository.findAllByBasketUuid(basketUuid);
    }

    @Override
    public boolean basketExists(UUID basketUuid) {
        return basketRepository.exists(basketUuid);
    }

    @Override
    public UUID createNewBasket() {
        UUID newCookieUUID = UUID.randomUUID();
        Basket basket = Basket.builder()
                .cookie(newCookieUUID)
                .build();
        basketRepository.save(basket);
        return newCookieUUID;
    }
}
