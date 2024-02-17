package org.geekhub.hw11.repository;

import org.geekhub.hw11.entity.HistoryEntry;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public class HistoryRepositoryImpl implements HistoryRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final HistoryMapper mapper;

    public HistoryRepositoryImpl(NamedParameterJdbcTemplate jdbcTemplate, HistoryMapper mapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.mapper = mapper;
    }

    @Override
    public void saveEntry(@NonNull HistoryEntry historyEntry) {
        SqlParameterSource parameterSource = new MapSqlParameterSource()
            .addValue("creatingTime", Timestamp.from(historyEntry.creatingTime()))
            .addValue("message", historyEntry.message())
            .addValue("encrypted", historyEntry.encrypted())
            .addValue("algorithm", historyEntry.algorithm().getValue())
            .addValue("userId", historyEntry.userId())
            .addValue("operation", historyEntry.operation().getValue());
        String query = """
            INSERT INTO history (creating_time, message, encrypted, algorithm, user_id, operation)
            VALUES (:creatingTime, :message, :encrypted, :algorithm, :userId, :operation)
            """;
        jdbcTemplate.update(query, parameterSource);
    }

    @Override
    public void deleteEntry(int entryId) {
        SqlParameterSource parameterSource = new MapSqlParameterSource()
            .addValue("entryId", entryId);
        String query = "DELETE FROM history WHERE entry_id = :entryId";
        jdbcTemplate.update(query, parameterSource);
    }

    @NonNull
    @Override
    public List<HistoryEntry> findAllEntries() {
        String query = "SELECT * FROM history";
        return jdbcTemplate.query(query, mapper);
    }
}
