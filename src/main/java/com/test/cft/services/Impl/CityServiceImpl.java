package com.test.cft.services.Impl;

import com.test.cft.domain.Address;
import com.test.cft.domain.City;
import com.test.cft.repository.CityRepository;
import com.test.cft.services.CityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class CityServiceImpl implements CityService {
    private  final CityRepository repository;

    public CityServiceImpl(CityRepository repository){
        this.repository=repository;
    }

    @Override
    public boolean addCity(City city) {
        var cityFromDB = Optional.ofNullable(repository.findByName( city.getCityName() ));
        if (cityFromDB.isPresent()) {
            return false;
        }

        repository.saveAndFlush( city );
        log.info( "Added " + city.toString() + " " + LocalDate.now() );
        return true;
    }

    @Override
    public boolean deleteCity(long id) {
        Optional<City> cityFromDB = repository.findById( id );

        if(cityFromDB == null){
            return false;
        }

        repository.deleteCityById( id );

        log.info( "Delete " + cityFromDB.get().toString() + " " + LocalDate.now() );

        return true;
    }

    @Override
    public boolean editCity(City city) {
        Optional<City> cityFromDB = repository.findById( city.getId() );
        if (cityFromDB == null) {
            return false;
        }
        repository.updateCity(city.getId(),city.getCityName(),city.getCountry() );
        log.info( "Edit  old version = " + cityFromDB.get().toString() + ", new version =" + city.toString() + " " + LocalDate.now() );
        return true;
    }

    @Override
    public City getCityByName(String cityName) {
        return Optional.ofNullable(repository.findByName( cityName )).orElse( new City(  ) );
    }

    @Override
    public City getCityById(long id) {
        return Optional.ofNullable( repository.findById( id ).get()).orElse( new City(  ) );
    }

    @Override
    public List<City> getAllCities() {
        return Optional.ofNullable(repository.findAll()).orElse( new ArrayList<>( ) );
    }
}
