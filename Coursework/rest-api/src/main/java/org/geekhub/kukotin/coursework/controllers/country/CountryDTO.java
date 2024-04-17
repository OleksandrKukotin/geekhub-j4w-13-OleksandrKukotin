package org.geekhub.kukotin.coursework.controllers.country;

import org.springframework.lang.NonNull;

public record CountryDTO(
    @NonNull int countryId,
    @NonNull String countryName
) {
}
