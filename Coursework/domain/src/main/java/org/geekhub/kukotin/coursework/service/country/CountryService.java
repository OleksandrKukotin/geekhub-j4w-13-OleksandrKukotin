package org.geekhub.kukotin.coursework.service.country;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryService {

    private final CountryRepository countryRepository;

    public CountryService(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    public void addCountry(String countryName) {
        countryRepository.addCountry(countryName);
    }

    public void updateCountry(Country entity) {
        countryRepository.updateCountry(entity);
    }

    public void removeCountry(int id) {
        countryRepository.removeCountry(id);
    }

    public List<Country> getAllCountries() {
        return countryRepository.getAllCountries();
    }
}
