package org.geekhub.hw11.repository;

import org.geekhub.hw11.entity.User;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepositoryImpl implements UserRepository {

    NamedParameterJdbcTemplate jdbcTemplate;
    UserMapper mapper;

    public UserRepositoryImpl(NamedParameterJdbcTemplate jdbcTemplate, UserMapper mapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.mapper = mapper;
    }

    @Override
    public List<User> findAllUsers() {
        String query = "SELECT * FROM users ORDER BY user_id";
        return jdbcTemplate.query(query, mapper);
    }
}
