package org.geekhub.hw11.history;

import org.geekhub.hw11.exception.RepositoryDatabaseException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
public class HistoryRepositoryImpl implements HistoryRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final HistoryMapper mapper;

    public HistoryRepositoryImpl(NamedParameterJdbcTemplate jdbcTemplate, HistoryMapper mapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.mapper = mapper;
    }

    @Override
    public void saveEntry(HistoryEntry historyEntry) {
        if (!isActiveUserIdValid(historyEntry.userId())) {
            String message = String.format("User with ID %d doesn't exist", historyEntry.userId());
            throw new RepositoryDatabaseException(message, new IllegalArgumentException());
        }
        SqlParameterSource parameterSource = new MapSqlParameterSource()
            .addValue("creating_time", Timestamp.from(historyEntry.time()))
            .addValue("message", historyEntry.input())
            .addValue("encrypted", historyEntry.encrypted())
            .addValue("algorithm", historyEntry.algorithm())
            .addValue("userId", historyEntry.userId());
        String query = """
                        INSERT INTO History (creating_time, message, encrypted, algorithm, user_id)
                        VALUES (:creating_time, :message, :encrypted, :algorithm, :userID)
                        """;
        jdbcTemplate.update(query, parameterSource);
    }

    private boolean isActiveUserIdValid(int userId) {
        SqlParameterSource parameterSource = new MapSqlParameterSource()
            .addValue("userId", userId);
        String query = "SELECT COUNT(*) FROM Users WHERE user_id = :userID";
        Integer count = jdbcTemplate.queryForObject(query, parameterSource, Integer.class);
        return Objects.requireNonNull(count) > 0;
    }

    @Override
    public void deleteEntry(int entryId) {
        SqlParameterSource parameterSource = new MapSqlParameterSource().
            addValue("entryId", entryId);
        String query = "DELETE FROM History WHERE entry_id = :entryId";
        jdbcTemplate.update(query, parameterSource);
    }

    @Override
    public Optional<HistoryEntry> getEntry(int entryId) {
        SqlParameterSource parameterSource = new MapSqlParameterSource().addValue("entryId", entryId);
        String query = "SELECT FROM History WHERE entry_id = :entryId";
        return Optional.of(jdbcTemplate.queryForObject(query, parameterSource, mapper));
    }

    @Override
    public List<HistoryEntry> getPaginatedEntriesByUserId(int offset, int pageNumber) {
        SqlParameterSource parameterSource = new MapSqlParameterSource()
            .addValue("offset", offset)
            .addValue("pageNumber", (pageNumber - 1) * offset);
        String query = "SELECT * FROM History ORDER BY creating_time DESC LIMIT :offset OFFSET :pageNumber";
        return jdbcTemplate.query(query, parameterSource, mapper);
    }

    @Override
    public List<HistoryEntry> getPaginatedEntriesByUserId(int userId) {
        return null;
    }

    @Override
    public List<HistoryEntry> getPaginatedEntriesByUserId(OffsetDateTime from, OffsetDateTime to) {
        return null;
    }

    @Override
    public List<HistoryEntry> getEntries(int pageNum, int pageSize) {
        return null;
    }

    @Override
    public List<HistoryEntry> getPaginatedEntriesByUserId(int userId, int pageNum, int pageSize) {
        return null;
    }
}
