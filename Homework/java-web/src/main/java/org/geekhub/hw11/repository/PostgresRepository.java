package org.geekhub.hw11.repository;

import org.geekhub.hw11.exception.RepositoryDatabaseException;
import org.geekhub.hw11.model.LogEntry;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@org.springframework.stereotype.Repository
public class PostgresRepository implements Repository {

    DataSourceProvider dataSourceProvider;

    public PostgresRepository(DataSourceProvider dataSourceProvider) {
        this.dataSourceProvider = dataSourceProvider;
    }

    @Override
    public void save(List<LogEntry> log) {
        DataSource dataSource = dataSourceProvider.create();
        try (Connection connection = dataSource.getConnection()) {

        } catch (SQLException e) {
            throw new RepositoryDatabaseException(e.getMessage(), e);
        }
    }

    @Override
    public List<LogEntry> getLogs() {
        return null;
    }
}
