package com.test.cft.service;

import com.test.cft.domain.Country;
import com.test.cft.domain.ServiceDirectory;
import com.test.cft.domain.ServiceStation;
import com.test.cft.repository.ServiceStationRepository;
import com.test.cft.services.ServiceStationService;
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
public class ServiceStationServiceImplTest {
    @Autowired
    private ServiceStationService service;

    @MockBean
    private ServiceStationRepository repository;

    @Test
    public void addServiceStationTest() {
        ServiceStation serviceStation = new ServiceStation(  );

        serviceStation.setId( 12l );

        Mockito.doReturn( null )
                .when( repository )
                .findById( 12l );

        boolean isServiceStationCreated = service.addServiceStation( serviceStation );

        Assert.assertTrue( isServiceStationCreated );

        Mockito.verify( repository, Mockito.times( 1 ) ).saveAndFlush( serviceStation );

    }

    @Test
    public void addServiceStationFailTest() {
        ServiceStation serviceStation = new ServiceStation(  );

        serviceStation.setId( 12L );

        Mockito.doReturn( new Country() )
                .when( repository )
                .findById( 12l );

        boolean isServiceStationCreated = service.addServiceStation( serviceStation );

        Assert.assertFalse( isServiceStationCreated );

        Mockito.verify( repository, Mockito.times( 0 ) ).saveAndFlush( any() );
    }

    @Test
    public void deleteServiceStationTest() {
        Mockito.doReturn( Optional.of (ServiceStation.builder().id( 12l ).serviceStationName( "service-1" ).build())  )
                .when( repository )
                .findById( 12l );

        boolean isDeletedServiceDirectory = service.deleteServiceStation( 12l );

        Assert.assertTrue( isDeletedServiceDirectory );

        Mockito.verify( repository, Mockito.times( 1 ) ).deleteById( any() );
        Mockito.verify( repository, Mockito.times( 1 ) ).findById( any() );
    }

    @Test
    public void deleteServiceStationFailTest() {
        Mockito.doReturn(  null  )
                .when( repository )
                .findById( 12l );

        boolean isDeletedServiceStation = service.deleteServiceStation( 12l );

        Assert.assertFalse( isDeletedServiceStation );

        Mockito.verify( repository, Mockito.times( 0 ) ).deleteById( any() );
        Mockito.verify( repository, Mockito.times( 1 ) ).findById( any() );
    }

    @Test
    public void getByNameTest() {
        ServiceStation serviceStation = new ServiceStation();
        serviceStation.setServiceStationName( "service-2" );

        Mockito.doReturn( serviceStation )
                .when( repository )
                .findByName( "service-2" );

        ServiceStation expected = service.getServiceStationByName("Post" );

        Assert.assertEquals(expected, serviceStation );

        Mockito.verify( repository, Mockito.times( 1 ) ).findByName( any() );
    }

    @Test
    public void editServiceStationTest(){
        Optional<ServiceStation> serviceStation = Optional.of( new ServiceStation() );
        serviceStation.get().setId(1L);
        serviceStation.get().setServiceStationName( "service-2" );

        Mockito.doReturn( serviceStation )
                .when( repository )
                .findById(1L);

        boolean isServiceStationEdit = service.editServiceStation( serviceStation.get() );

        Assert.assertTrue(isServiceStationEdit);

        Mockito.verify( repository, Mockito.times( 1 ) ).saveAndFlush( any() );
        Mockito.verify( repository, Mockito.times( 1 ) ).findById( any() );
    }

    @Test
    public void  editServiceStationFailTest(){
        ServiceStation serviceStation = new ServiceStation(  );
        serviceStation.setId(22L );
        serviceStation.setServiceStationName( "service-2" );

        Mockito.doReturn( null )
                .when( repository )
                .findById( 22l );

        boolean isServiceStationEdit = service.editServiceStation(serviceStation );

        Assert.assertFalse(isServiceStationEdit);

        Mockito.verify( repository, Mockito.times( 0 ) ).saveAndFlush( any() );
        Mockito.verify( repository, Mockito.times( 1 ) ).findById( any() );
    }

    @Test
    public void getAll(){
        List<ServiceStation> serviceStations = new ArrayList<>();
        serviceStations.add( new ServiceStation() );
        serviceStations.add( new ServiceStation() );

        Mockito.doReturn( serviceStations )
                .when( repository )
                .findAll( );

        var expectedList = service.getAllServiceStations();

        Assert.assertEquals( expectedList,serviceStations );

        Mockito.verify( repository, Mockito.times( 1 ) ).findAll(  );
    }
}
