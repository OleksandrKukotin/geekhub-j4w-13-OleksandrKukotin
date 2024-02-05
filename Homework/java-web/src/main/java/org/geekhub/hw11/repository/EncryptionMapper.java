package org.geekhub.hw11.repository;

import org.geekhub.hw11.model.LogEntry;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class EncryptionMapper implements RowMapper<LogEntry> {

    @Override
    public LogEntry mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new LogEntry(
            rs.getTimestamp("time").toInstant(),
            rs.getString("message"),
            rs.getString("algorithm"),
            rs.getString("encrypted"),
            rs.getInt("userid")
        );
    }
}
