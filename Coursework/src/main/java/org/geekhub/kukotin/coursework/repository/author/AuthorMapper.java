package org.geekhub.kukotin.coursework.repository.author;

import org.geekhub.kukotin.coursework.dto.AuthorDTO;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;

@Service
public class AuthorMapper implements RowMapper<AuthorDTO> {

    @Override
    public AuthorDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new AuthorDTO(
            rs.getLong("author_id"),
            rs.getString("author_name"),
            rs.getString("author_surname")
        );
    }
}
