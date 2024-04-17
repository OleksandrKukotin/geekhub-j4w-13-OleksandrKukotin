package org.geekhub.kukotin.coursework.controllers;

import org.geekhub.kukotin.coursework.controllers.dto.CountryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.geekhub.kukotin.coursework.service.CountryService;

import java.util.List;

@RestController
@RequestMapping("/countries")
public class CountryController {

    private final CountryService countryService;

    @Autowired
    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void addCountry(@RequestBody String countryName) {
        countryService.addCountry(countryName);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateCountry(@RequestBody CountryDTO countryDTO) {
        countryService.updateCountry(countryDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeCountry(@PathVariable int id) {
        countryService.removeCountry(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity<List<CountryDTO>> getAllCountries() {
        List<CountryDTO> countries = countryService.getAllCountries();
        return new ResponseEntity<>(countries, HttpStatus.OK);
    }
}
