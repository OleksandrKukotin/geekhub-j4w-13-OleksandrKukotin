package org.geekhub.kukotin.coursework.repository.country;

import org.geekhub.kukotin.coursework.service.country.Country;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CountryRepositoryImpl implements CountryRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final CountryMapper mapper;

    public CountryRepositoryImpl(NamedParameterJdbcTemplate jdbcTemplate, CountryMapper mapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.mapper = mapper;
    }

    @Override
    public void addCountry(String countryName) {
        SqlParameterSource parameterSource = new MapSqlParameterSource()
            .addValue("countryName", countryName);
        String query = "insert into countries(country_name) values (:countryName)";
        jdbcTemplate.update(query, parameterSource);
    }

    @Override
    public void updateCountry(Country entity) {
        SqlParameterSource parameterSource = new MapSqlParameterSource()
            .addValue("countryId", entity.getCountryId())
            .addValue("newCountryName", entity.getCountryName());
        String query = "update countries set country_name = :newCountryName where country_id = :countryId";
        jdbcTemplate.update(query, parameterSource);
    }

    @Override
    public void removeCountry(int id) {
        SqlParameterSource parameterSource = new MapSqlParameterSource()
            .addValue("countryId", id);
        String query = "delete from countries where country_id = :countryId";
        jdbcTemplate.update(query, parameterSource);
    }

    @Override
    public List<Country> getAllCountries() {
        String query = "select * from countries";
        return jdbcTemplate.query(query, mapper);
    }
}
