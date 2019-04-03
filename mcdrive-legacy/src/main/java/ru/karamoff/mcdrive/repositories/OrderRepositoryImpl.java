package ru.karamoff.mcdrive.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import ru.karamoff.mcdrive.models.Foodpiece;
import ru.karamoff.mcdrive.models.Order;

import java.util.List;

public class OrderRepositoryImpl implements OrderRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final RowMapper<Order> orderRowMapper =
            (rs, i) -> Order.builder()
                    .id(rs.getLong("id"))
                    .sum(rs.getFloat("sum"))
                    .time(rs.getTimestamp("time").toLocalDateTime())
                    .ready(rs.getBoolean("ready"))
                    .archived(rs.getBoolean("archived"))
                    .build();

    private final RowMapper<Foodpiece> foodpieceRowMapper =
            (rs, i) -> Foodpiece.builder()
                    .id(rs.getLong("fp_id"))
                    .name(rs.getString("name"))
                    .available(rs.getBoolean("available"))
                    .cost(rs.getFloat("cost"))
                    .ready(rs.getBoolean("ready"))
                    .build();

    @Override
    public void save(Order model) {

    }

    @Override
    public void update(Order model) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public Order findOne(Long id) {
        return null;
    }

    @Override
    public List<Order> findAll() {
        List<Order> orders = jdbcTemplate.query(
                "SELECT * FROM \"order\";",
                orderRowMapper
        );

        for (Order order : orders) {
            List<Foodpiece> foodpieces = jdbcTemplate.query(
                    "SELECT fpo.ready, f.id as fp_id, f.name, f.cost, f.available\n" +
                            "FROM foodpiece_order fpo\n" +
                            "JOIN foodpiece f ON fpo.foodpiece_id = f.id\n" +
                            "WHERE order_id = ?;",
                    foodpieceRowMapper,
                    order.getId()
            );
            order.setFoodpieces(foodpieces);
        }

        return orders;
    }
}
