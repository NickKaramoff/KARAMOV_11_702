package ru.karamoff.basketdemo.repositories;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import ru.karamoff.basketdemo.models.Item;

import javax.sql.DataSource;
import java.util.List;
import java.util.UUID;

public class ItemRepositoryImpl implements ItemRepository {

    private final JdbcTemplate jdbcTemplate;

    public ItemRepositoryImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    private final RowMapper<Item> itemMapper = (rs, i) -> Item.builder()
            .id(rs.getLong("id"))
            .name(rs.getString("name"))
            .price(rs.getDouble("price"))
            .build();

    private final RowMapper<Item> itemInBasketMapper = ((rs, i) -> Item.builder()
            .id(rs.getLong("id"))
            .name(rs.getString("name"))
            .price(rs.getDouble("price"))
            .amount(rs.getInt("amount"))
            .build());

    @Override
    public void save(Item model) {

    }

    @Override
    public void update(Item model) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public Item findOne(Long id) {
        //language=SQL
        String q_GET_ONE_ITEM = "SELECT * FROM basket_item WHERE id=?;";
        return jdbcTemplate.queryForObject(q_GET_ONE_ITEM, itemMapper, id);
    }

    @Override
    public List<Item> findAll() {
        //language=SQL
        String q_GET_ALL_ITEMS = "SELECT * FROM basket_item ORDER BY name;";
        return jdbcTemplate.query(q_GET_ALL_ITEMS, itemMapper);
    }

    @Override
    public List<Item> findAllByBasketUuid(UUID basketUuid) {
        //language=SQL
        String qGetItemsInBasket = "SELECT i.id, i.name, i.price, bi.amount, b.cookie " +
                "FROM basket_item i " +
                "JOIN basket_basket_items bi on i.id = bi.item_id " +
                "JOIN basket_basket b on bi.basket_id = b.id " +
                "WHERE b.cookie=? ORDER BY name;";
        return jdbcTemplate.query(qGetItemsInBasket,
                                  itemInBasketMapper,
                                  basketUuid.toString());
    }

    @Override
    public Item findOneFromBasket(Long id, UUID basketUuid) {
        String qGetItemInBasket = "SELECT i.id, i.name, i.price, bi.amount, b.cookie " +
                "FROM basket_item i " +
                "LEFT OUTER JOIN basket_basket_items bi on i.id = bi.item_id " +
                "JOIN basket_basket b on bi.basket_id = b.id " +
                "WHERE b.cookie=? AND i.id=?;";
        try {
            return jdbcTemplate.queryForObject(qGetItemInBasket,
                    itemInBasketMapper,
                    basketUuid.toString(),
                    id);
        } catch (Exception e) {
            return findOne(id);
        }
    }


}
