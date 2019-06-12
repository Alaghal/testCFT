package com.test.cft.services;

import com.test.cft.domain.City;

import java.util.List;

public interface CityService {
    boolean addCity(City city);
    boolean deleteCity(long id);
    boolean editCity(City city);
    City getCityByName(String cityName);
    City getCityById (long id);
    List<City> getAllCities();
}
