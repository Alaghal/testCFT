package com.test.cft.controller;


import com.test.cft.domain.City;
import com.test.cft.exeption.CityNotFoundExeption;
import com.test.cft.services.CityService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class CityController {
     private  final CityService service;

     public CityController(CityService service){
         this.service = service;
     }

    @GetMapping("/cities")
    public List getAllCities() {
        return service.getAllCities();
    }

    @GetMapping("/cities/{id}")
    public City getCityById(@PathVariable(value = "id") Long cityId) throws CityNotFoundExeption {
        return service.getCityById(cityId);
    }

    @GetMapping("/cities/{name}")
    public  City getCityByName(@PathVariable(value = "name") String cityName ){
         return service.getCityByName( cityName );
    }

    @PostMapping("/cities")
    public City createCity(@Valid @RequestBody City city){
          if (service.addCity( city ))
              return city;
         return new City();
    }

    @PutMapping("/cities/{id}")
    public City updateCity(@PathVariable(value = "id") Long cityId,
                           @Valid @RequestBody City cityDetails) throws  CityNotFoundExeption{
      City city = service.getCityById( cityId );

      city.setCityName( cityDetails.getCityName());
      city.setAddresses( cityDetails.getAddresses());
      city.setCountry( cityDetails.getCountry() );

      if (service.editCity( city ))
          return city;
      return  new City();
     }

     @DeleteMapping("/cities/{id}")
     public ResponseEntity deleteCity(@PathVariable(value = "id") Long cityId) throws  CityNotFoundExeption{
             if (service.deleteCity( cityId ))
                 return ResponseEntity.ok(  ).build();
             return ResponseEntity.badRequest().build();
     }

}
