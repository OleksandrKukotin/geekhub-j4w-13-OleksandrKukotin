package org.geekhub.kukotin.coursework.controllers.country;

import org.geekhub.kukotin.coursework.service.country.Country;

import java.util.List;

public class CountryConverter {

    private CountryConverter() {
    }

    public static CountryDTO toDto(Country entity) {
        return new CountryDTO(entity.getCountryId(), entity.getCountryName());
    }

    public static Country fromDto(CountryDTO dto) {
        return new Country(dto.countryId(), dto.countryName());
    }

    public static List<CountryDTO> toDtos(List<Country> entities) {
        return entities.stream()
            .map(CountryConverter::toDto)
            .toList();
    }
}
