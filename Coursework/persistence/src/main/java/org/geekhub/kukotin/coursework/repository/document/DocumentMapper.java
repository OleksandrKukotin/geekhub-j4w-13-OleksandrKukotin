package org.geekhub.kukotin.coursework.repository.document;

import dto.DocumentDTO;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class DocumentMapper implements RowMapper<DocumentDTO> {

    @Override
    public DocumentDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new DocumentDTO(
            rs.getLong("document_id"),
            rs.getLong("document_type"),
            rs.getString("document_title"),
            rs.getString("description"),
            rs.getInt("publishing_year"),
            rs.getLong("author_id"),
            rs.getInt("country_id"),
            rs.getInt("available_copies"),
            rs.getTimestamp("last_updated_time").toInstant(),
            rs.getLong("updated_by")
        );
    }
}
