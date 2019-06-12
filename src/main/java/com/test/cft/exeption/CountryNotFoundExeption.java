package com.test.cft.exeption;

public class CountryNotFoundExeption extends  Exception {

    public  CountryNotFoundExeption(long countryId){
        super(String.format("Country is not found with id : '%s'", countryId));
    }
}
