package org.geekhub.kukotin.coursework.controllers;

import org.geekhub.kukotin.coursework.controllers.converter.CountryConverter;
import org.geekhub.kukotin.coursework.controllers.dto.CountryDTO;
import org.geekhub.kukotin.coursework.service.CountryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.geekhub.kukotin.coursework.controllers.converter.CountryConverter.fromDto;
import static org.geekhub.kukotin.coursework.controllers.converter.CountryConverter.toDtos;

@RestController
@RequestMapping("/api/v1/countries")
public class CountryController {

    private final CountryService countryService;

    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void addCountry(@RequestBody String countryName) {
        countryService.addCountry(countryName);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping()
    public void updateCountry(@RequestBody CountryDTO countryDTO) {
        countryService.updateCountry(fromDto(countryDTO));
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/{id}")
    public void removeCountry(@PathVariable int id) {
        countryService.removeCountry(id);
    }

    @GetMapping
    public ResponseEntity<List<CountryDTO>> getAllCountries() {
        return ResponseEntity.ok(toDtos(countryService.getAllCountries()));
    }
}
