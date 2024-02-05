package org.geekhub.hw11.repository;

import org.geekhub.hw11.exception.RepositoryDatabaseException;
import org.geekhub.hw11.model.LogEntry;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.Objects;

@Repository
public class PostgresRepositoryEncryptionImpl implements RepositoryEncryption {

    private final EncryptionMapper mapper;
    private final NamedParameterJdbcTemplate namedJdbcTemplate;
    private final int activeUserId;

    public PostgresRepositoryEncryptionImpl(@Value("${activeUser.id}") int activeUserId,
                                            EncryptionMapper mapper,
                                            NamedParameterJdbcTemplate namedJdbcTemplate) {
        this.mapper = mapper;
        this.namedJdbcTemplate = namedJdbcTemplate;
        this.activeUserId = activeUserId;
    }

    @Override
    public void saveLogEntry(LogEntry entry) {
        if (!isActiveUserIdValid(activeUserId)) {
            String message = String.format("User with ID %d doesn't exist", activeUserId);
            throw new RepositoryDatabaseException(message, new IllegalArgumentException());
        }
        SqlParameterSource parameterSource = new MapSqlParameterSource()
            .addValue("time", Timestamp.from(entry.time()))
            .addValue("message", entry.input())
            .addValue("encrypted", entry.encrypted())
            .addValue("algorithm", entry.algorithm())
            .addValue("userID", activeUserId);
        String query = """
                        INSERT INTO History (time, message, encrypted, algorithm, userID)
                        VALUES (:time, :message, :encrypted, :algorithm, :userID)
                        """;
        namedJdbcTemplate.update(query, parameterSource);
    }

    private boolean isActiveUserIdValid(int userId) {
        SqlParameterSource parameterSource = new MapSqlParameterSource()
            .addValue("userID", userId);
        String query = "SELECT COUNT(*) FROM Users WHERE userID = :userID";
        Integer count = namedJdbcTemplate.queryForObject(query, parameterSource, Integer.class);
        return Objects.requireNonNull(count) > 0;
    }

    @Override
    public List<LogEntry> fetchPaginatedAll(int pageNumber, int offset) {
        SqlParameterSource parameterSource = new MapSqlParameterSource()
            .addValue("offset", offset)
            .addValue("pageNumber", (pageNumber - 1) * offset);
        String query = "SELECT * FROM History ORDER BY time DESC LIMIT :offset OFFSET :pageNumber";
        return namedJdbcTemplate.query(query, parameterSource, mapper);
    }

    @Override
    public List<LogEntry> fetchPaginatedByUserId(int userId, int pageNumber, int offset) {
        SqlParameterSource parameterSource = new MapSqlParameterSource()
            .addValue("targetId", userId)
            .addValue("offset", offset)
            .addValue("pageNumber", (pageNumber - 1) * offset);
        String query = """
            SELECT * FROM History
            WHERE userID = :targetId
            ORDER BY time DESC LIMIT :offset OFFSET :pageNumber
            """;
        return namedJdbcTemplate.query(query, parameterSource, mapper);
    }

    @Override
    public List<LogEntry> fetchByDate(Instant date) {
        SqlParameterSource parameterSource = new MapSqlParameterSource()
            .addValue("from", Timestamp.from(date))
            .addValue("to", Timestamp.from(date.plusSeconds(86400)));

        String query = "SELECT * FROM History WHERE time BETWEEN :from AND :to ORDER BY time DESC";

        return namedJdbcTemplate.query(query, parameterSource, mapper);
    }

    @Override
    public List<LogEntry> fetchByEncryptor(String encryptor) {
        SqlParameterSource parameterSource = new MapSqlParameterSource()
            .addValue("algo", encryptor);
        String query = "SELECT * FROM History WHERE algorithm = :algo ORDER BY time DESC";
        return namedJdbcTemplate.query(query, parameterSource, mapper);
    }
}
