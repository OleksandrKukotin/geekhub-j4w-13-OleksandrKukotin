package org.geekhub.kukotin.coursework.repository.country;

import org.geekhub.kukotin.coursework.dto.CountryDTO;

import java.util.List;

public interface CountryRepository {

    void addCountry(String countryName);

    void updateCountry(CountryDTO dto);

    void removeCountry(CountryDTO dto);

    List<CountryDTO> getAllCountries();
}
