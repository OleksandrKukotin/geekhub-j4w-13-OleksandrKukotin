package org.geekhub.kukotin.coursework.repository.country;

import org.geekhub.kukotin.coursework.entity.Country;

import java.util.List;

public interface CountryRepository {

    void addCountry(String countryName);

    void updateCountry(Country entity);

    void removeCountry(int id);

    List<Country> getAllCountries();
}
