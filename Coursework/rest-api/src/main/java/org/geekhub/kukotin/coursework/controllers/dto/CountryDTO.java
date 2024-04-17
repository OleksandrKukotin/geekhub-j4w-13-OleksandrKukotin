package org.geekhub.kukotin.coursework.controllers.dto;

import org.springframework.lang.NonNull;

public record CountryDTO(
    @NonNull int countryId,
    @NonNull String countryName
) {
}
