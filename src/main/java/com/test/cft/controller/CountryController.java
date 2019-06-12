package com.test.cft.controller;

import com.test.cft.domain.Country;
import com.test.cft.exeption.CountryNotFoundExeption;
import com.test.cft.services.CountryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class CountryController {
    private  final CountryService service;

    public CountryController ( CountryService service){
        this.service=service;
    }

    @GetMapping("/countries")
    public List getCountries(){
        return service.getAllCountries();
    }

    @GetMapping("/countries/{id}")
    public  Country getCountryById(@PathVariable(value = "id") Long countryId) throws CountryNotFoundExeption {
        return service.getCountryById( countryId );
    }

    @GetMapping("/countries/{name}")
    public  Country getCountryByName(@PathVariable(value = "name") String name){
        return  service.getCountryByName( name );
    }

    @PostMapping("/countries")
    public  Country createCountry(@Valid @RequestBody Country country){
        if (service.addCountry( country )){
            return  country;
        }
        return new Country(  );
    }

    @PutMapping("/countries/{id}")
    public Country updateCountry(@PathVariable(value = "id") Long countryId,
                                 @Valid @RequestBody Country countryDetails) throws  CountryNotFoundExeption{
        Country country = service.getCountryById( countryId );

        country.setCountryName( countryDetails.getCountryName());
        country.setCityList( countryDetails.getCityList());

        if (service.editCountry( country ))
            return country;
        return  new Country();
    }

    @DeleteMapping("/countries/{id}")
     public ResponseEntity deleteCountry(@PathVariable(value = "id") Long countryId) throws CountryNotFoundExeption{
        if (service.deleteCountry( countryId ))
            return ResponseEntity.ok(  ).build();
        return ResponseEntity.badRequest().build();
    }


}
