package org.geekhub.kukotin.coursework.service.country;

import java.util.List;

public interface CountryRepository {

    void addCountry(String countryName);

    void updateCountry(Country entity);

    void removeCountry(int id);

    List<Country> getAllCountries();
}
