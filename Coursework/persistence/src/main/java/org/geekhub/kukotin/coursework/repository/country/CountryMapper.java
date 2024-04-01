package org.geekhub.kukotin.coursework.repository.country;

import dto.CountryDTO;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class CountryMapper implements RowMapper<CountryDTO> {

    @Override
    public CountryDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new CountryDTO(rs.getLong("country_id"), rs.getString("country_name"));
    }
}
