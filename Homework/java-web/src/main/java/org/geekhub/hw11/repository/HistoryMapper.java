package org.geekhub.hw11.repository;

import com.geekhub.hw15.encoding.EncodingAlgorithm;
import com.geekhub.hw15.encoding.EncodingOperation;
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
            EncodingAlgorithm.fromValue(rs.getString("algorithm")),
            rs.getInt("user_id"),
            EncodingOperation.fromValue(rs.getString("operation"))
        );
    }
}
