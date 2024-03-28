package org.geekhub.kukotin.coursework.repository.documenttype;

import org.geekhub.kukotin.coursework.dto.DocumentTypeDTO;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;

@Service
public class DocumentTypeMapper implements RowMapper<DocumentTypeDTO> {

    @Override
    public DocumentTypeDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new DocumentTypeDTO(rs.getLong("type_id"), rs.getString("type_name"));
    }
}
