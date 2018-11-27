package ru.karamoff.basketdemo.repositories;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import ru.karamoff.basketdemo.models.Basket;
import ru.karamoff.basketdemo.models.Item;

import javax.sql.DataSource;
import java.util.List;
import java.util.UUID;

public class BasketRepositoryImpl implements BasketRepository {

    private final JdbcTemplate jdbcTemplate;

    public BasketRepositoryImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    private final RowMapper<Long> idMapper = (rs, i) -> rs.getLong("id");
    private final RowMapper<Integer> amountMapper = (rs, i) -> rs.getInt("amount");

    @Override
    public void save(Basket model) {
        //language=SQL
        String qCreateBasket = "INSERT INTO basket_basket (cookie) VALUES (?);";
        jdbcTemplate.update(qCreateBasket, model.getCookie());
    }

    @Override
    public void update(Basket model) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public Basket findOne(Long id) {
        return null;
    }

    @Override
    public List<Basket> findAll() {
        return null;
    }

    @Override
    public boolean exists(UUID basketUuid) {
        try {
            //language=SQL
            String Q_BASKET_BY_COOKIE = "SELECT * FROM basket_basket WHERE cookie=?;";
            jdbcTemplate.queryForObject(Q_BASKET_BY_COOKIE,
                    (rs, i) -> rs.getLong("id"),
                    basketUuid.toString());
        } catch (Exception e) {
            return false;
        }

        return true;
    }

    @Override
    public void addItemToBasket(Item item, UUID basketUuid) {
        //language=SQL
        String qInsertItem = "INSERT INTO basket_basket_items (basket_id, item_id) VALUES (?,?)";
        jdbcTemplate.update(qInsertItem,
                            getBasketIdByUuid(basketUuid),
                            item.getId());
    }

    @Override
    public void removeItemFromBasket(Item item, UUID basketUuid) {
        //language=SQL
        String qDeleteItem = "DELETE FROM basket_basket_items WHERE basket_id=? AND item_id=?;";
        jdbcTemplate.update(qDeleteItem,
                            getBasketIdByUuid(basketUuid),
                            item.getId()
        );
    }

    @Override
    public void updateAmount(Item item, UUID basketUuid, int newAmount) {
        //language=SQL
        String qUpdateAmount = "UPDATE basket_basket_items SET amount=? WHERE basket_id=? AND item_id=?;";
        jdbcTemplate.update(qUpdateAmount,
                            newAmount,
                            getBasketIdByUuid(basketUuid),
                            item.getId());
    }

    @Override
    public Integer getAmount(Item item, UUID basketUuid) {
        //language=SQL
        String qSelectAmount = "SELECT amount FROM basket_basket_items WHERE basket_id=? AND item_id=?;";
        try {
            return jdbcTemplate.queryForObject(qSelectAmount,
                    amountMapper,
                    getBasketIdByUuid(basketUuid),
                    item.getId());
        } catch (Exception e) {
            return null;
        }
    }

    private Long getBasketIdByUuid(UUID basketUuid) {
        //language=SQL
        String q_GET_BASKET_ID = "SELECT id FROM basket_basket WHERE cookie=?;";
        return jdbcTemplate.queryForObject(q_GET_BASKET_ID,
                idMapper,
                basketUuid.toString());
    }
}
