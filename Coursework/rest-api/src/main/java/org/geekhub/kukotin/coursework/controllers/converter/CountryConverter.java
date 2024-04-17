package org.geekhub.kukotin.coursework.controllers.converter;

import org.geekhub.kukotin.coursework.controllers.dto.CountryDTO;
import org.geekhub.kukotin.coursework.entity.Country;

import java.util.List;
import java.util.stream.Collectors;

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
            .collect(Collectors.toList());
    }
}
