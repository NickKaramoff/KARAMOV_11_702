package ru.karamoff.mcdrive.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import ru.karamoff.mcdrive.models.AuthCookie;
import ru.karamoff.mcdrive.models.Role;
import ru.karamoff.mcdrive.models.User;

import java.util.List;
import java.util.UUID;

public class AuthCookieRepositoryImpl implements AuthCookieRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final RowMapper<AuthCookie> authCookieRowMapper =
            (rs, i) -> AuthCookie.builder()
                    .id(rs.getLong("id"))
                    .value(UUID.fromString(rs.getString("value")))
                    .user(
                            User.builder()
                                    .id(rs.getLong("user_id"))
                                    .name(rs.getString("name"))
                                    .email(rs.getString("email"))
                                    .phone(rs.getString("phone"))
                                    .passHash(rs.getString("pass_hash"))
                                    .role(Role.valueOf(rs.getString("role")))
                                    .build()
                    )
                    .build();

    @Override
    public void save(AuthCookie model) {
        jdbcTemplate.update(
                "INSERT INTO cookie(value, user_id) VALUES (?, ?)",
                model.getValue().toString(),
                model.getUser().getId()
        );
    }

    @Override
    public void update(AuthCookie model) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public AuthCookie findOne(Long id) {
        try {
            return jdbcTemplate.queryForObject(
                    "SELECT c.id, c.value, c.user_id, u.name, u.phone, u.email, u.pass_hash, u.role\n" +
                            "FROM cookie c\n" +
                            "  JOIN \"user\" u ON c.user_id = u.id\n" +
                            "WHERE c.id=?\n" +
                            "LIMIT 1;",
                    authCookieRowMapper,
                    id
            );
        } catch (EmptyResultDataAccessException e) {
            return null;
        }

    }

    @Override
    public List<AuthCookie> findAll() {
        return null;
    }

    @Override
    public AuthCookie getByUserId(Long userId) {
        try {
            return jdbcTemplate.queryForObject(
                    "SELECT c.id, c.value, c.user_id, u.name, u.phone, u.email, u.pass_hash, u.role\n" +
                            "FROM cookie c\n" +
                            "  JOIN \"user\" u ON c.user_id = u.id\n" +
                            "WHERE c.user_id=?\n" +
                            "LIMIT 1;",
                    authCookieRowMapper,
                    userId
            );
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public AuthCookie getByValue(String value) {
        try {
            return jdbcTemplate.queryForObject(
                    "SELECT c.id, c.value, c.user_id, u.name, u.phone, u.email, u.pass_hash, u.role\n" +
                            "FROM cookie c\n" +
                            "  JOIN \"user\" u ON c.user_id = u.id\n" +
                            "WHERE c.value=?\n" +
                            "LIMIT 1;",
                    authCookieRowMapper,
                    value
            );
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
}
