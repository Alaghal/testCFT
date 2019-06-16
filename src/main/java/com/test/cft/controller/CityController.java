package com.test.cft.controller;


import com.test.cft.domain.City;
import com.test.cft.exeption.CityNotFoundExeption;
import com.test.cft.services.CityService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/cities")
public class CityController {
     private  final CityService service;

     public CityController(CityService service){
         this.service = service;
     }

    @GetMapping()
    public List getAllCities() {
        return service.getAllCities();
    }

    @GetMapping("/id")
    public City getCityById(@RequestParam(value = "id", required = true) Long id) throws CityNotFoundExeption {
        return service.getCityById(id);
    }

    @GetMapping("/name")
    public  City getCityByName(@RequestParam(value = "name",required = true ) String name ){
         return service.getCityByName( name );
    }

    @PostMapping()
    public City createCity(@Valid @RequestBody City city){
          if (service.addCity( city ))
              return city;
         return new City();
    }

    @PutMapping("/id")
    public City updateCity(@RequestParam(value = "id", required = true) Long id,
                           @Valid @RequestBody City cityDetails) throws  CityNotFoundExeption{
      City city = service.getCityById( id );

      city.setCityName( cityDetails.getCityName());
      city.setAddresses( cityDetails.getAddresses());
      city.setCountry( cityDetails.getCountry() );

      if (service.editCity( city ))
          return city;
      return  new City();
     }

     @DeleteMapping("/id")
     public ResponseEntity deleteCity(@RequestParam(value = "id", required = true) Long id) throws  CityNotFoundExeption{
             if (service.deleteCity( id ))
                 return ResponseEntity.ok(  ).build();
             return ResponseEntity.badRequest().build();
     }

}
