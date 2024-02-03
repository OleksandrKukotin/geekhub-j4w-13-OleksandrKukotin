package org.geekhub.hw11.repository;

import org.geekhub.hw11.exception.RepositoryDatabaseException;
import org.geekhub.hw11.model.LogEntry;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.LinkedList;
import java.util.List;

@Repository
public class PostgresRepository implements org.geekhub.hw11.repository.Repository {

    private final PostgresqlDatasourceProvider postgresqlDatasourceProvider;
    private final int activeUserId;

    public PostgresRepository(PostgresqlDatasourceProvider postgresqlDatasourceProvider, @Value("${activeUser.id}") int activeUserId) {
        this.postgresqlDatasourceProvider = postgresqlDatasourceProvider;
        this.activeUserId = activeUserId;
    }

    @Override
    public void saveLogEntry(LogEntry entry) {
        if (!isActiveUserIdValid(activeUserId)) {
            String message = String.format("User with ID %d doesn't exist", activeUserId);
            throw new RepositoryDatabaseException(message, new IllegalArgumentException());
        }

        String query = "INSERT INTO History (time, message, encrypted, algorithm, userID) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = postgresqlDatasourceProvider.create().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setTimestamp(1, Timestamp.from(entry.time()));
            preparedStatement.setString(2, entry.input());
            preparedStatement.setString(3, entry.encrypted());
            preparedStatement.setString(4, entry.algorithm());
            preparedStatement.setInt(5, activeUserId);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RepositoryDatabaseException(e.getMessage(), e);
        }
    }

    private boolean isActiveUserIdValid(int userId) {
        String validationQuery = "SELECT COUNT(*) FROM Users WHERE userID = ?";
        try (Connection connection = postgresqlDatasourceProvider.create().getConnection();
             PreparedStatement validationStatement = connection.prepareStatement(validationQuery)) {

            validationStatement.setInt(1, userId);
            ResultSet resultSet = validationStatement.executeQuery();
            resultSet.next();
            int count = resultSet.getInt(1);

            return count > 0;

        } catch (SQLException e) {
            throw new RepositoryDatabaseException(e.getMessage(), e);
        }
    }

    @Override
    public List<LogEntry> fetchPaginatedAll(int pageNumber, int offset) {
        List<LogEntry> entries = new LinkedList<>();
        String query = "SELECT * FROM History ORDER BY time DESC LIMIT ? OFFSET ?";
        try (Connection connection = postgresqlDatasourceProvider.create().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, offset);
            preparedStatement.setInt(2, (pageNumber - 1) * offset);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                extractResultSetIntoLogEntriesList(resultSet, entries);
            }
        } catch (SQLException e) {
            throw new RepositoryDatabaseException(e.getMessage(), e);
        }
        return entries;
    }

    private void extractResultSetIntoLogEntriesList(ResultSet resultSet, List<LogEntry> entries) throws SQLException {
        while (resultSet.next()) {
            entries.add(new LogEntry(
                resultSet.getTimestamp("time").toInstant(),
                resultSet.getString("message"),
                resultSet.getString("algorithm"),
                resultSet.getString("encrypted"),
                resultSet.getInt("userid")
            ));
        }
    }

    @Override
    public List<LogEntry> fetchPaginatedByUserId(int userId) {
        List<LogEntry> entries = new LinkedList<>();
        String query = "SELECT * FROM History WHERE userID = ? ORDER BY time DESC";
        try (Connection connection = postgresqlDatasourceProvider.create().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, userId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    extractResultSetIntoLogEntriesList(resultSet, entries);
                }
            }

        } catch (SQLException e) {
            throw new RepositoryDatabaseException(e.getMessage(), e);
        }
        return entries;
    }

    @Override
    public List<LogEntry> fetchByDate(Instant date) {
        List<LogEntry> entries = new LinkedList<>();
        String query = "SELECT * FROM History WHERE time >= ? AND time < ? ORDER BY time DESC";
        try (Connection connection = postgresqlDatasourceProvider.create().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setTimestamp(1, Timestamp.from(date));
            preparedStatement.setTimestamp(2, Timestamp.from(date.plusSeconds(86400)));
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    extractResultSetIntoLogEntriesList(resultSet, entries);
                }
            }
        } catch (SQLException e) {
            throw new RepositoryDatabaseException(e.getMessage(), e);
        }
        return entries;
    }

    @Override
    public List<LogEntry> fetchByEncryptor(String encryptor) {
        List<LogEntry> entries = new LinkedList<>();
        String query = "SELECT * FROM History WHERE algorithm = ? ORDER BY time DESC";
        try (Connection connection = postgresqlDatasourceProvider.create().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, encryptor);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    extractResultSetIntoLogEntriesList(resultSet, entries);
                }
            }

        } catch (SQLException e) {
            throw new RepositoryDatabaseException(e.getMessage(), e);
        }
        return entries;
    }
}
