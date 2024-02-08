package org.geekhub.crypto.users;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UserRepositoryImpl implements UserRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final UserMapper mapper;

    public UserRepositoryImpl(NamedParameterJdbcTemplate jdbcTemplate, UserMapper mapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.mapper = mapper;
    }

    @Override
    public Optional<User> getUser(int userId) {
        String query = "SELECT * FROM users WHERE user_id = :userId";

        SqlParameterSource params = new MapSqlParameterSource()
            .addValue("userId", userId);

        try {
            User user = jdbcTemplate.queryForObject(query, params, mapper);
            return Optional.ofNullable(user);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }
}
