package org.geekhub.hw11.repository;

import org.geekhub.hw11.history.HistoryEntry;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class EncryptionMapper implements RowMapper<HistoryEntry> {

    @Override
    public HistoryEntry mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new HistoryEntry(
            rs.getInt("entry_id"),
            rs.getTimestamp("creating_time").toInstant(),
            rs.getString("message"),
            rs.getString("algorithm"),
            rs.getString("encrypted"),
            rs.getInt("user_id")
        );
    }
}
