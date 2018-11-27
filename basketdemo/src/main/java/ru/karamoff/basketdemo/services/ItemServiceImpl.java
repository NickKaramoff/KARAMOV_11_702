package ru.karamoff.basketdemo.services;

import ru.karamoff.basketdemo.models.Item;
import ru.karamoff.basketdemo.repositories.ItemRepository;

import java.util.List;

public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;

    public ItemServiceImpl(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Override
    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }

}
