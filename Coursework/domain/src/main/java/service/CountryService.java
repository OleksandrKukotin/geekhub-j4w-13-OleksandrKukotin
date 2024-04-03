package service;

import dto.CountryDTO;
import org.geekhub.kukotin.coursework.repository.country.CountryRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class CountryService {

    CountryRepository countryRepository;

    public CountryService(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @Transactional
    public void addCountry(String countryName) {
        countryRepository.addCountry(countryName);
    }

    @Transactional
    public void updateCountry(CountryDTO dto) {
        countryRepository.updateCountry(dto);
    }

    @Transactional
    public void removeCountry(CountryDTO dto) {
        countryRepository.removeCountry(dto);
    }

    @Transactional(readOnly = true)
    public List<CountryDTO> getAllCountries() {
        return countryRepository.getAllCountries();
    }
}
