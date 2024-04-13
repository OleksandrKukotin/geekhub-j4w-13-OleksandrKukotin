package org.geekhub.kukotin.coursework.repository.country;


import dto.CountryDTO;

import java.util.List;

public interface CountryRepository {

    void addCountry(String countryName);

    void updateCountry(CountryDTO dto);

    void removeCountry(int id);

    List<CountryDTO> getAllCountries();
}
