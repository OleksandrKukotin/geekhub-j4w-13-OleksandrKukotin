package org.geekhub.hw11.repository;

import org.geekhub.hw11.entity.User;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import java.util.List;

public class UserRepositoryImpl implements UserRepository {

    NamedParameterJdbcTemplate jdbcTemplate;
    UserMapper mapper;

    public UserRepositoryImpl(NamedParameterJdbcTemplate jdbcTemplate, UserMapper mapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.mapper = mapper;
    }

    @Override
    public List<User> getUsers() {
        String query = "SELECT * FROM users ORDER BY user_id";
        return jdbcTemplate.query(query, mapper);
    }

    @Override
    public User getUserById(long userId) {
        SqlParameterSource parameterSource = new MapSqlParameterSource()
            .addValue("userId", userId);
        String query = "SELECT * FROM users WHERE user_id = :userId";
        return jdbcTemplate.queryForObject(query, parameterSource, mapper);
    }

    @Override
    public User getUserByName(String userName) {
        SqlParameterSource parameterSource = new MapSqlParameterSource()
            .addValue("userName", userName);
        String query = "SELECT * FROM users WHERE user_name = :userName";
        return jdbcTemplate.queryForObject(query, parameterSource, mapper);
    }

    @Override
    public User getUserByEmail(String email) {
        SqlParameterSource parameterSource = new MapSqlParameterSource()
            .addValue("email", email);
        String query = "SELECT * FROM users WHERE email = :email";
        return jdbcTemplate.queryForObject(query, parameterSource, mapper);
    }
}
