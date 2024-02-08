package org.geekhub.example.repositories;

import org.geekhub.example.models.Book;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

public class BookMapper implements RowMapper<Book> {

    @Override
    public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
        int id = rs.getInt("id");
        String name = rs.getString("name");
        String description = rs.getString("description");
        String author = rs.getString("author");
        OffsetDateTime publishDate = rs.getTimestamp("publishDate").toInstant().atOffset(ZoneOffset.UTC);
        return new Book(id, name, description, author, publishDate);
    }
}
