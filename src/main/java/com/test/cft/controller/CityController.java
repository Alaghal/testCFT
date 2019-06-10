package com.test.cft.controller;

import com.test.cft.repository.CityRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CityController {
     private  final CityRepository repository;

     public CityController(CityRepository repository){
         this.repository = repository;
     }

   // @GetMapping("/countries")
   // public List getAllCountries() {
     //   return Arra
   // }
}
