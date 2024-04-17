package org.geekhub.kukotin.coursework.entity;

import org.geekhub.kukotin.coursework.controllers.dto.CountryDTO;

public class Country {

    private int countryId;
    private String countryName;

    public Country(int countryId, String countryName) {
        this.countryId = countryId;
        this.countryName = countryName;
    }

    public Country(CountryDTO dto) {
        this.countryId = dto.countryId();
        this.countryName = dto.countryName();
    }

    public int getCountryId() {
        return countryId;
    }

    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }
}
