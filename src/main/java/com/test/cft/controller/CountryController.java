package com.test.cft.controller;

import com.test.cft.domain.Country;
import com.test.cft.services.CountryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/countries")
public class CountryController {
    private final CountryService service;

    public CountryController(CountryService service) {
        this.service = service;
    }

    @GetMapping()
    public List getCountries() {
        return service.getAllCountries();
    }

    @GetMapping("/id")
    public Country getCountryById(@RequestParam(value = "id", required = true) Long id) {
        return service.getCountryById( id );
    }

    @GetMapping("/name")
    public Country getCountryByName(@RequestParam(value = "name", required = true) String name) {
        return service.getCountryByName( name );
    }

    @PostMapping()
    public Country createCountry(@Valid @RequestBody Country country) {
        if (service.addCountry( country )) {
            return country;
        }
        return new Country();
    }

    @PutMapping("/id")
    public Country updateCountry(@RequestParam(value = "id", required = true) Long id,
                                 @Valid @RequestBody Country countryDetails) {
        Country country = service.getCountryById( id );

        country.setCountryName( countryDetails.getCountryName() );
        country.setCityList( countryDetails.getCityList() );

        if (service.editCountry( country ))
            return country;
        return new Country();
    }

    @DeleteMapping("/id")
    public ResponseEntity deleteCountry(@RequestParam(value = "id", required = true) Long id) {
        if (service.deleteCountry( id ))
            return ResponseEntity.ok().build();
        return ResponseEntity.badRequest().build();
    }


}
