package com.test.cft.exeption;

public class CityNotFoundExeption extends  Exception {

    public CityNotFoundExeption (long cityId){
        super(String.format("City is not found with id : '%s'", cityId));
    }
}
