package org.geekhub.kukotin.coursework.repository.document;

import org.geekhub.kukotin.coursework.service.document.Document;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class DocumentMapper implements RowMapper<Document> {

    @Override
    public Document mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Document(
            rs.getInt("document_id"),
            rs.getInt("document_type"),
            rs.getString("document_title"),
            rs.getString("description"),
            rs.getInt("publishing_year"),
            rs.getInt("author_id"),
            rs.getInt("available_copies"),
            rs.getTimestamp("last_updated_time").toInstant(),
            rs.getInt("updated_by"),
            rs.getInt("category_id"),
            rs.getInt("publisher_id")
        );
    }
}
