package ru.karamoff.mcdrive.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import ru.karamoff.mcdrive.models.Role;
import ru.karamoff.mcdrive.models.User;

import java.util.List;

public class UserRepositoryImpl implements UserRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final RowMapper<User> userRowMapper =
            (rs, i) -> User.builder()
                    .id(rs.getLong("id"))
                    .name(rs.getString("name"))
                    .email(rs.getString("email"))
                    .phone(rs.getString("phone"))
                    .passHash(rs.getString("pass_hash"))
                    .role(Role.valueOf(rs.getString("role")))
                    .build();

    @Override
    public void save(User model) {

    }

    @Override
    public void update(User model) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public User findOne(Long id) {
        return null;
    }

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public User findByEmail(String email) {
        try {
            return jdbcTemplate.queryForObject(
                    "SELECT * FROM \"user\" WHERE email=? LIMIT 1;",
                    userRowMapper,
                    email
            );
        } catch (EmptyResultDataAccessException e) {
            return null;
        }

    }
}
