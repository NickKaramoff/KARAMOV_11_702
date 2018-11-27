package ru.karamoff.mcdrive.repositories;

import lombok.SneakyThrows;
import ru.karamoff.mcdrive.models.Foodpiece;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.Optional;

public class FoodpieceRepositoryJdbcImpl implements FoodpieceRepository {

    private Connection connection;

    //language=sql
    private static final String SQL_INSERT_ROW =
            "INSERT INTO mcdrive_foodpiece (\"name\", cost, available) " +
                    "VALUES (?, ?, ?)";

    //language=sql
    private static final String SQL_FIND_BY_ID =
            "SELECT * FROM mcdrive_foodpiece WHERE id = ?";

    private PreparedStatement findByIdStatement, insertRowStatement;

    @SneakyThrows
    public FoodpieceRepositoryJdbcImpl(Connection connection) {
        this.connection = connection;
        findByIdStatement = this.connection.prepareStatement(SQL_FIND_BY_ID);
        insertRowStatement = this.connection.prepareStatement(SQL_INSERT_ROW);
    }

    @Override
    public Optional<Foodpiece> findByName(String name) {
        return Optional.empty();
    }

    @SneakyThrows
    @Override
    public void save(Foodpiece model) {
        insertRowStatement.setString(1, model.getName());
        insertRowStatement.setFloat(2, model.getCost());
        insertRowStatement.setBoolean(3, model.getAvailable());
        insertRowStatement.executeUpdate();
    }

    @Override
    public void update(Foodpiece model) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    @SneakyThrows
    public Foodpiece findOne(Long id) {
        findByIdStatement.setLong(1, id);
        ResultSet resultSet = findByIdStatement.executeQuery();
        resultSet.next();
        return Foodpiece.builder()
                .id(resultSet.getLong("id"))
                .name(resultSet.getString("name"))
                .cost(resultSet.getFloat("cost"))
                .build();
    }

    @Override
    public List<Foodpiece> findAll() {
        return null;
    }
}
