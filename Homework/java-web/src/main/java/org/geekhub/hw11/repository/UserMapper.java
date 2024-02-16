package org.geekhub.hw11.repository;

import org.geekhub.hw11.entity.User;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class UserMapper implements RowMapper<User> {

    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new User(
            rs.getLong("user_id"),
            rs.getString("user_name"),
            rs.getString("email"));
    }
}
