package com.test.cft.controller;

import com.test.cft.repository.CountryRepository;

public class CountryController {
    private  final CountryRepository repository;

    public CountryController ( CountryRepository repository){
        this.repository=repository;
    }

}
