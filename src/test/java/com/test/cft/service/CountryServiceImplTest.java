package com.test.cft.service;

import com.test.cft.domain.City;
import com.test.cft.domain.Country;
import com.test.cft.repository.CityRepository;
import com.test.cft.repository.CountryRepository;
import com.test.cft.services.CityService;
import com.test.cft.services.CountryService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CountryServiceImplTest {
    @Autowired
    private CountryService service;

    @MockBean
    private CountryRepository repository;

    @Test
    public void addCountryTest() {
        Country country = new Country(  );

        country.setId( 12l );

        Mockito.doReturn( null )
                .when( repository )
                .findById( 12l );

        boolean isCountryCreated = service.addCountry( country );

        Assert.assertTrue( isCountryCreated );

        Mockito.verify( repository, Mockito.times( 1 ) ).saveAndFlush( country );

    }

    @Test
    public void addCountryFailTest() {
        Country country = new Country(  );

        country.setId( 12L );

        Mockito.doReturn( new Country() )
                .when( repository )
                .findById( 12l );

        boolean isCountryCreated = service.addCountry( country );

        Assert.assertFalse( isCountryCreated );

        Mockito.verify( repository, Mockito.times( 0 ) ).saveAndFlush( any() );
    }

    @Test
    public void deleteCountryTest() {
        Mockito.doReturn( Optional.of (Country.builder().id( 12l ).countryName( "Russia" ).build())  )
                .when( repository )
                .findById( 12l );

        boolean isDeletedCountry = service.deleteCountry( 12l );

        Assert.assertTrue( isDeletedCountry );

        Mockito.verify( repository, Mockito.times( 1 ) ).deleteById( any() );
        Mockito.verify( repository, Mockito.times( 1 ) ).findById( any() );
    }

    @Test
    public void deleteCountryFailTest() {
        Mockito.doReturn(  null  )
                .when( repository )
                .findById( 12l );

        boolean isDeletedCountry = service.deleteCountry( 12l );

        Assert.assertFalse( isDeletedCountry );

        Mockito.verify( repository, Mockito.times( 0 ) ).deleteById( any() );
        Mockito.verify( repository, Mockito.times( 1 ) ).findById( any() );
    }

    @Test
    public void getByNameTest() {
        Country country = new Country();
        country.setCountryName( "Russia" );

        Mockito.doReturn( country )
                .when( repository )
                .findByName( "Russia" );

        Country expected = service.getCountryByName("Russia" );

        Assert.assertEquals(expected, country );

        Mockito.verify( repository, Mockito.times( 1 ) ).findByName( any() );
    }

    @Test
    public void editCountryTest(){
        Optional<Country> country = Optional.of( new Country() );
        country.get().setId(1L);
        country.get().setCountryName( "Russia" );

        Mockito.doReturn( country )
                .when( repository )
                .findById(1L);

        boolean isCountryEdit = service.editCountry( country.get() );

        Assert.assertTrue(isCountryEdit);

        Mockito.verify( repository, Mockito.times( 1 ) ).saveAndFlush( any() );
        Mockito.verify( repository, Mockito.times( 1 ) ).findById( any() );
    }

    @Test
    public void  editCityFailTest(){
        Country country = new Country(  );
        country.setId(22L );
        country.setCountryName( "Russia" );

        Mockito.doReturn( null )
                .when( repository )
                .findById( 22l );

        boolean isCountryEdit = service.editCountry( country );

        Assert.assertFalse(isCountryEdit);

        Mockito.verify( repository, Mockito.times( 0 ) ).saveAndFlush( any() );
        Mockito.verify( repository, Mockito.times( 1 ) ).findById( any() );
    }

    @Test
    public void getAll(){
        List<Country> countries = new ArrayList<>();
        countries.add( new Country() );
        countries.add( new Country() );

        Mockito.doReturn( countries )
                .when( repository )
                .findAll( );

        var expectedList = service.getAllCountries();

        Assert.assertEquals( expectedList,countries );

        Mockito.verify( repository, Mockito.times( 1 ) ).findAll(  );
    }
}
