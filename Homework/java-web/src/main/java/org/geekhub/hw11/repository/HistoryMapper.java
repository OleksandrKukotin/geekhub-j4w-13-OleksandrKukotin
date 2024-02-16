package org.geekhub.hw11.repository;

import org.geekhub.hw11.entity.HistoryEntry;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class HistoryMapper implements RowMapper<HistoryEntry> {

    @Override
    public HistoryEntry mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new HistoryEntry(
            rs.getInt("entry_id"),
            rs.getTime("creating_time").toInstant(),
            rs.getString("message"),
            rs.getString("encrypted"),
            rs.getString("algorithm"),
            rs.getInt("user_id"),
            rs.getString("operation"));
    }
}
