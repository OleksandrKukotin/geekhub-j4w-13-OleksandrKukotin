package org.geekhub.kukotin.coursework.dto;

import org.springframework.lang.NonNull;

public record CountryDTO(
    @NonNull long countryId,
    @NonNull String countryName
) {
}
