package org.geekhub.hw11.repository;

import org.geekhub.hw11.exception.RepositoryDatabaseException;
import org.geekhub.hw11.model.LogEntry;
import org.springframework.context.annotation.Profile;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@org.springframework.stereotype.Repository
@Profile("prod")
public class PostgresRepository implements Repository {

    DatasourceProvider datasourceProvider;

    public PostgresRepository(DatasourceProvider datasourceProvider) {
        this.datasourceProvider = datasourceProvider;
    }

    @Override
    public void save(List<LogEntry> log) {
        DataSource dataSource = datasourceProvider.create();
        try (Connection connection = dataSource.getConnection()) {

        } catch (SQLException e) {
            throw new RepositoryDatabaseException(e.getMessage(), e);
        }
    }

    @Override
    public List<LogEntry> getLogs() {
        String query = "select * from History";
        return null;
    }
}
