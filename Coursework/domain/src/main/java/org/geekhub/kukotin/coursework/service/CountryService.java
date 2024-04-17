package org.geekhub.kukotin.coursework.service;

import org.geekhub.kukotin.coursework.repository.country.CountryRepository;
import org.springframework.stereotype.Service;

@Service
public class CountryService {

    private final CountryRepository countryRepository;

    public CountryService(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    public void addCountry(String countryName) {
        countryRepository.addCountry(countryName);
    }

    public void updateCountry(CountryDTO dto) {
        countryRepository.updateCountry(dto);
    }

    public void removeCountry(int id) {
        countryRepository.removeCountry(id);
    }

    public List<CountryDTO> getAllCountries() {
        return countryRepository.getAllCountries();
    }
}
