package org.geekhub.kukotin.coursework.repository.country;

import org.geekhub.kukotin.coursework.service.country.Country;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class CountryMapper implements RowMapper<Country> {

    @Override
    public Country mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Country(rs.getInt("country_id"), rs.getString("country_name"));
    }
}
