package org.geekhub.crypto.users;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
class UserMapper implements RowMapper<User> {

    private UserMapper() {
    }

    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new User(
            rs.getInt("user_id"),
            rs.getString("user_name")
        );
    }
}
