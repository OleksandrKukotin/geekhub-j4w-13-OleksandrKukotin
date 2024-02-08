package org.geekhub.crypto.history;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.OffsetDateTime;
import java.util.List;
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
    public void saveRecord(@NonNull HistoryRecord historyEntry) {
        String query = """
                INSERT INTO history (user_id, operation, algorithm, original_text, encoded_text, date)
                VALUES (:userId, :operation, :algorithm, :originalText, :encodedText, :date)
            """;

        SqlParameterSource params = new MapSqlParameterSource()
            .addValue("userId", historyEntry.userId())
            .addValue("operation", historyEntry.operation().name())
            .addValue("algorithm", historyEntry.algorithm().name())
            .addValue("originalText", historyEntry.originalText())
            .addValue("encodedText", historyEntry.encodedText())
            .addValue("date", Timestamp.from(historyEntry.date().toInstant()));

        jdbcTemplate.update(query, params);
    }

    @Override
    public void deleteRecord(int id) {
        String query = "DELETE FROM history WHERE record_id = :id";

        SqlParameterSource params = new MapSqlParameterSource()
            .addValue("id", id);

        jdbcTemplate.update(query, params);
    }

    @NonNull
    @Override
    public Optional<HistoryRecord> getRecord(int id) {
        String query = "SELECT * FROM history WHERE record_id = :id";

        SqlParameterSource params = new MapSqlParameterSource()
            .addValue("id", id);

        HistoryRecord historyRecord = jdbcTemplate.queryForObject(query, params, mapper);
        return Optional.ofNullable(historyRecord);
    }

    @NonNull
    @Override
    public List<HistoryRecord> getRecords() {
        String query = "SELECT * FROM history ORDER BY record_id";

        return jdbcTemplate.query(query, mapper);
    }

    @NonNull
    @Override
    public List<HistoryRecord> getRecords(int userId) {
        String query = "SELECT * FROM history WHERE user_id = :userId ORDER BY record_id";

        SqlParameterSource params = new MapSqlParameterSource()
            .addValue("userId", userId);

        return jdbcTemplate.query(query, params, mapper);
    }

    @NonNull
    @Override
    public List<HistoryRecord> getRecords(@Nullable OffsetDateTime from, @Nullable OffsetDateTime to) {
        String query = """
            SELECT * FROM history
            WHERE date BETWEEN
                      coalesce(CAST(:from AS timestamp with time zone), '-infinity')
                      AND
                      coalesce(CAST(:to AS timestamp with time zone), 'infinity')
            ORDER BY record_id
            """;

        SqlParameterSource params = new MapSqlParameterSource()
            .addValue("from", from)
            .addValue("to", to);

        return jdbcTemplate.query(query, params, mapper);
    }

    @NonNull
    @Override
    public List<HistoryRecord> getRecords(int pageNum, int pageSize) {
        String query = "SELECT * FROM history ORDER BY record_id LIMIT :pageSize OFFSET :offset";

        SqlParameterSource params = new MapSqlParameterSource()
            .addValue("pageSize", pageSize)
            .addValue("offset", getOffset(pageNum, pageSize));

        return jdbcTemplate.query(query, params, mapper);
    }

    @NonNull
    @Override
    public List<HistoryRecord> getRecords(int userId, int pageNum, int pageSize) {
        String query = "SELECT * FROM history WHERE user_id = :userId ORDER BY record_id LIMIT :pageSize OFFSET :offset";

        SqlParameterSource params = new MapSqlParameterSource()
            .addValue("userId", userId)
            .addValue("pageSize", pageSize)
            .addValue("offset", getOffset(pageNum, pageSize));

        return jdbcTemplate.query(query, params, mapper);
    }

    // Offset starts from Zero, so we need to subtract 1 from the page number
    private static int getOffset(int pageNum, int pageSize) {
        return pageNum * pageSize - 1;
    }
}
