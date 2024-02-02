package org.geekhub.hw11.repository;

import org.geekhub.hw11.exception.RepositoryDatabaseException;
import org.geekhub.hw11.model.LogEntry;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class PostgresRepository implements org.geekhub.hw11.repository.Repository {

    private final DatasourceProvider datasourceProvider;
    private final int activeUserId;

    public PostgresRepository(DatasourceProvider datasourceProvider, @Value("${activeUser.id}") int activeUserId) {
        this.datasourceProvider = datasourceProvider;
        this.activeUserId = activeUserId;
    }

    @Override
    public void saveLogEntry(LogEntry entry) {
        String query = "INSERT INTO History (time, message, encrypted, algorithm, userID) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = datasourceProvider.create().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setTimestamp(1, Timestamp.from(entry.time()));
            preparedStatement.setString(2, entry.input());
            preparedStatement.setString(3, entry.encrypted());
            preparedStatement.setString(4, entry.algorithm());
            preparedStatement.setInt(5, activeUserId);
            preparedStatement.addBatch();
            preparedStatement.executeBatch();
        } catch (SQLException e) {
            throw new RepositoryDatabaseException(e.getMessage(), e);
        }
    }

    @Override
    public List<LogEntry> getLogs() {
        String query = "SELECT * FROM History WHERE userID = ?";
        List<LogEntry> logEntries = new ArrayList<>();

        try (Connection connection = datasourceProvider.create().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, activeUserId);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                LogEntry logEntry = new LogEntry(
                    resultSet.getTimestamp("time").toInstant(),
                    resultSet.getString("message"),
                    resultSet.getString("algorithm"),
                    resultSet.getString("encrypted")
                );
                logEntries.add(logEntry);
            }

        } catch (SQLException e) {
            throw new RepositoryDatabaseException(e.getMessage(), e);
        }

        return logEntries;
    }
}
