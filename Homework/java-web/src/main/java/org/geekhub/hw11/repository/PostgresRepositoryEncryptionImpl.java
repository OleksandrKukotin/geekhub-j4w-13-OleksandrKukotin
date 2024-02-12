package org.geekhub.hw11.repository;

import org.geekhub.hw11.history.HistoryEntry;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

@Repository
public class PostgresRepositoryEncryptionImpl implements HistoryRepository {

    private final EncryptionMapper mapper;
    private final NamedParameterJdbcTemplate namedJdbcTemplate;

    public PostgresRepositoryEncryptionImpl(@Value("${activeUser.id}") int activeUserId,
                                            EncryptionMapper mapper,
                                            NamedParameterJdbcTemplate namedJdbcTemplate) {
        this.mapper = mapper;
        this.namedJdbcTemplate = namedJdbcTemplate;
        this.activeUserId = activeUserId;
    }

    @Override
    public void saveLogEntry(HistoryEntry entry) {

    }

    @Override
    public List<HistoryEntry> fetchPaginatedAll(int pageNumber, int offset) {

    }

    @Override
    public List<HistoryEntry> fetchPaginatedByUserId(int userId, int pageNumber, int offset) {
        SqlParameterSource parameterSource = new MapSqlParameterSource()
            .addValue("targetId", userId)
            .addValue("offset", offset)
            .addValue("pageNumber", (pageNumber - 1) * offset);
        String query = """
            SELECT * FROM History
            WHERE userID = :targetId
            ORDER BY creating_time DESC LIMIT :offset OFFSET :pageNumber
            """;
        return namedJdbcTemplate.query(query, parameterSource, mapper);
    }

    @Override
    public List<HistoryEntry> fetchByDate(Instant date) {
        SqlParameterSource parameterSource = new MapSqlParameterSource()
            .addValue("from", Timestamp.from(date))
            .addValue("to", Timestamp.from(date.plusSeconds(86400)));

        String query = "SELECT * FROM History WHERE creating_time BETWEEN :from AND :to ORDER BY creating_time DESC";

        return namedJdbcTemplate.query(query, parameterSource, mapper);
    }

    @Override
    public List<HistoryEntry> fetchByEncryptor(String encryptor) {
        SqlParameterSource parameterSource = new MapSqlParameterSource()
            .addValue("algo", encryptor);
        String query = "SELECT * FROM History WHERE algorithm = :algo ORDER BY creating_time DESC";
        return namedJdbcTemplate.query(query, parameterSource, mapper);
    }
}
