package org.geekhub.hw11.repository;

import org.postgresql.ds.PGSimpleDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
public class DataSourceProvider {

    private final String host;
    private final int port;
    private final String user;
    private final String password;
    private final String dbName;

    public DataSourceProvider(@Value("${dataSource.host}") String host, @Value("${dataSource.port}") int port,
                              @Value("${dataSource.user}") String user, @Value("${dataSource.password}") String password,
                              @Value("${dataSource.databaseName}") String dbName) {
        this.host = host;
        this.port = port;
        this.user = user;
        this.password = password;
        this.dbName = dbName;
    }

    public DataSource create() {
        PGSimpleDataSource dataSource = new PGSimpleDataSource();
        dataSource.setServerNames(new String[]{host});
        dataSource.setPortNumbers(new int[]{port});
        dataSource.setUser(user);
        dataSource.setPassword(password);
        dataSource.setDatabaseName(dbName);
        return dataSource;
    }
}
