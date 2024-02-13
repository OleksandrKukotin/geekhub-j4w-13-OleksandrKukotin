package org.geekhub.hw11.history;

import org.springframework.lang.NonNull;
import org.geekhub.hw11.exception.RepositoryDatabaseException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
public class HistoryRepositoryImpl implements HistoryRepository {

    public static final String USER_ID_PARAM_NAME = "userId";
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
            .addValue(USER_ID_PARAM_NAME, historyEntry.userId());
        String query = """
                        INSERT INTO History (creating_time, message, encrypted, algorithm, user_id)
                        VALUES (:creating_time, :message, :encrypted, :algorithm, :userId)
                        """;
        jdbcTemplate.update(query, parameterSource);
    }

    private boolean isActiveUserIdValid(int userId) {
        SqlParameterSource parameterSource = new MapSqlParameterSource()
            .addValue(USER_ID_PARAM_NAME, userId);
        String query = "SELECT COUNT(*) FROM Users WHERE user_id = :userId";
        Integer count = jdbcTemplate.queryForObject(query, parameterSource, Integer.class);
        return Objects.requireNonNull(count) > 0;
    }

    @NonNull
    @Override
    public void deleteEntry(int entryId) {
        SqlParameterSource parameterSource = new MapSqlParameterSource().
            addValue("entryId", entryId);
        String query = "DELETE FROM History WHERE entry_id = :entryId";
        jdbcTemplate.update(query, parameterSource);
    }

    @NonNull
    @Override
    public Optional<HistoryEntry> getEntry(int entryId) {
        SqlParameterSource parameterSource = new MapSqlParameterSource().addValue("entryId", entryId);
        String query = "SELECT * FROM History WHERE entry_id = :entryId";
        return Optional.ofNullable(jdbcTemplate.queryForObject(query, parameterSource, mapper));
    }

    @NonNull
    @Override
    public List<HistoryEntry> getEntries() {
        String query = "SELECT * FROM History ORDER BY entry_id";
        return jdbcTemplate.query(query, mapper);
    }

    @NonNull
    @Override
    public List<HistoryEntry> getEntries(int userId) {
        SqlParameterSource parameterSource = new MapSqlParameterSource().addValue(USER_ID_PARAM_NAME, userId);
        String query = "SELECT * FROM History WHERE entry_id = :userId ORDER BY entry_id";
        return jdbcTemplate.query(query, parameterSource, mapper);
    }

    @NonNull
    @Override
    public List<HistoryEntry> getEntries(Instant from, Instant to) {
        SqlParameterSource parameterSource = new MapSqlParameterSource()
            .addValue("from", Timestamp.from(from))
            .addValue("to", Timestamp.from(to));
        String query = """
            SELECT * FROM history
            WHERE creating_time BETWEEN
                      coalesce(CAST(:from AS timestamp with time zone), '-infinity')
                      AND
                      coalesce(CAST(:to AS timestamp with time zone), 'infinity')
            ORDER BY entry_id
            """;


        return jdbcTemplate.query(query, parameterSource, mapper);
    }

    @NonNull
    @Override
    public List<HistoryEntry> getEntries(int pageNumber, int offset) {
        SqlParameterSource parameterSource = new MapSqlParameterSource()
            .addValue("offset", offset)
            .addValue("pageNumber", (pageNumber - 1) * offset);
        String query = "SELECT * FROM History ORDER BY entry_id DESC LIMIT :offset OFFSET :pageNumber";
        return jdbcTemplate.query(query, parameterSource, mapper);
    }

    @NonNull
    @Override
    public List<HistoryEntry> getEntries(int userId, int pageNumber, int offset) {
        SqlParameterSource parameterSource = new MapSqlParameterSource()
            .addValue(USER_ID_PARAM_NAME, userId)
            .addValue("offset", offset)
            .addValue("pageNumber", (pageNumber - 1) * offset);
        String query = """
            SELECT * FROM History
            WHERE user_id = :userId
            ORDER BY creating_time DESC LIMIT :offset OFFSET :pageNumber
            """;
        return jdbcTemplate.query(query, parameterSource, mapper);
    }
}
