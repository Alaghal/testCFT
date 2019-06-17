package com.test.cft.services.Impl;

import com.test.cft.domain.City;
import com.test.cft.domain.Country;
import com.test.cft.repository.CountryRepository;
import com.test.cft.services.CountryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class CountryServiceImpl  implements CountryService {
    private  final CountryRepository repository;

    public CountryServiceImpl(CountryRepository repository){
        this.repository=repository;
    }

    @Override
    public boolean addCountry(Country country) {
        var countryFromDB = Optional.ofNullable(repository.findByName( country.getCountryName() ));
        if (countryFromDB.isPresent()) {
            return false;
        }

        repository.saveAndFlush( country );
        log.info( "Added " + country.toString() + " " + LocalDate.now() );
        return true;
    }

    @Override
    public boolean deleteCountry(long id) {
        Optional<Country> countryFromDB = repository.findById( id );

        if(countryFromDB == null){
            return false;
        }

        repository.deleteById( id );

        log.info( "Delete " + countryFromDB.get().toString() + " " + LocalDate.now() );

        return true;
    }

    @Override
    public boolean editCountry(Country country) {
        Optional<Country> countryFromDB = repository.findById( country.getId() );
        if (countryFromDB == null) {
            return false;
        }
         // repository.updateCountry( country.getId(),country.getCountryName() );
        repository.saveAndFlush( country );
        log.info( "Edit  old version = " + countryFromDB.get().toString() + ", new version =" + country.toString() + " " + LocalDate.now() );
        return true;
    }

    @Override
    public Country getCountryByName(String CountryName) {
        return Optional.ofNullable(repository.findByName( CountryName )).orElse( new Country(  ) );
    }

    @Override
    public Country getCountryById(long id) {
        return Optional.ofNullable( repository.findById( id ).get()).orElse( new Country(  ) );
    }

    @Override
    public List<Country> getAllCountries() {
        return Optional.ofNullable(repository.findAll()).orElse( new ArrayList<>( ) );
    }
}
