package com.test.cft.services;

import com.test.cft.domain.Country;

import java.util.List;

public interface CountryService {
    boolean addCountry(Country country);
    boolean deleteCountry(long id);
    boolean editCountry(Country country);
    Country getCountryByName(String CountryName);
    Country getCountryById (long id);
    List<Country> getAllCountries();
}
