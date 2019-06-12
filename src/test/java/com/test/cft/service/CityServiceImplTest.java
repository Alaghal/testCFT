package com.test.cft.service;

import com.test.cft.domain.Address;
import com.test.cft.domain.City;
import com.test.cft.repository.AddressRepository;
import com.test.cft.repository.CityRepository;
import com.test.cft.services.AddressService;
import com.test.cft.services.CityService;
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
public class CityServiceImplTest {
    @Autowired
    private CityService service;

    @MockBean
    private CityRepository repository;

    @Test
    public void addCityTest() {
        City city = new City(  );

        city.setId( 12l );

        Mockito.doReturn( null )
                .when( repository )
                .findById( 12l );

        boolean isCityCreated = service.addCity( city );

        Assert.assertTrue( isCityCreated );

        Mockito.verify( repository, Mockito.times( 1 ) ).saveAndFlush( city );

    }

    @Test
    public void addCityFailTest() {
        City city = new City(  );

        city.setId( 12L );

        Mockito.doReturn( new City() )
                .when( repository )
                .findById( 12l );

        boolean isCityCreated = service.addCity( city );

        Assert.assertFalse( isCityCreated );

        Mockito.verify( repository, Mockito.times( 0 ) ).saveAndFlush( any() );
    }

    @Test
    public void deleteCityTest() {
        Mockito.doReturn( Optional.of (City.builder().id( 12l ).cityName( "Tomsk" ).build())  )
                .when( repository )
                .findById( 12l );

        boolean isDeletedCity = service.deleteCity( 12l );

        Assert.assertTrue( isDeletedCity );

        Mockito.verify( repository, Mockito.times( 1 ) ).deleteById( any() );
        Mockito.verify( repository, Mockito.times( 1 ) ).findById( any() );
    }

    @Test
    public void deleteCityFailTest() {
        Mockito.doReturn(  null  )
                .when( repository )
                .findById( 12l );

        boolean isDeletedCity = service.deleteCity( 12l );

        Assert.assertFalse( isDeletedCity );

        Mockito.verify( repository, Mockito.times( 0 ) ).deleteById( any() );
        Mockito.verify( repository, Mockito.times( 1 ) ).findById( any() );
    }

    @Test
    public void getByNameTest() {
        City city = new City();
        city.setCityName( "Tomsk" );

        Mockito.doReturn( city )
                .when( repository )
                .findByName( "Tomsk");

        City expected = service.getCityByName("Tomsk" );

        Assert.assertEquals(expected, city );

        Mockito.verify( repository, Mockito.times( 1 ) ).findByName( any() );
    }

    @Test
    public void editCityTest(){
        Optional<City> city = Optional.of( new City() );
        city.get().setId(1L);
        city.get().setCityName( "Tomsk" );

        Mockito.doReturn( city )
                .when( repository )
                .findById(1L);

        boolean isAddressEdit = service.editCity( city.get() );

        Assert.assertTrue(isAddressEdit);

        Mockito.verify( repository, Mockito.times( 1 ) ).saveAndFlush( any() );
        Mockito.verify( repository, Mockito.times( 1 ) ).findById( any() );
    }

    @Test
    public void  editCityFailTest(){
        City city = new City(  );
        city.setId(22L );
        city.setCityName( "Tomsk" );

        Mockito.doReturn( null )
                .when( repository )
                .findById( 22l );

        boolean isCityEdit = service.editCity( city );

        Assert.assertFalse(isCityEdit);

        Mockito.verify( repository, Mockito.times( 0 ) ).saveAndFlush( any() );
        Mockito.verify( repository, Mockito.times( 1 ) ).findById( any() );
    }

    @Test
    public void getAll(){
        List<City> cities = new ArrayList<>();
        cities.add( new City() );
        cities.add( new City() );

        Mockito.doReturn( cities )
                .when( repository )
                .findAll( );

        var expectedList = service.getAllCities();

        Assert.assertEquals( expectedList,cities );

        Mockito.verify( repository, Mockito.times( 1 ) ).findAll(  );
    }
}
