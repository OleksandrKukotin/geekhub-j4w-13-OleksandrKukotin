package org.geekhub.hw11.repository;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
public class DatasourceProvider {

    private final String host;
    private final int port;
    private final String user;
    private final String password;
    private final String dbName;

    public DatasourceProvider(@Value("${dataSource.host}") String host, @Value("${dataSource.port}") int port,
                              @Value("${dataSource.user}") String user, @Value("${dataSource.password}") String password,
                              @Value("${dataSource.databaseName}") String dbName) {
        this.host = host;
        this.port = port;
        this.user = user;
        this.password = password;
        this.dbName = dbName;
    }

    public DataSource create() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(String.format("jdbc:postgresql://%s:%d/%s", host, port, dbName));
        config.setUsername(user);
        config.setPassword(password);
        return new HikariDataSource(config);
    }
}
