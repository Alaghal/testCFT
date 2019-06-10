package com.test.cft.service;

import com.test.cft.domain.Country;
import com.test.cft.domain.ServiceDirectory;
import com.test.cft.repository.ServiceDirectoryRepository;
import com.test.cft.services.ServiceDirectoryService;
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
public class ServiceDirectoryServiceImplTest {
    @Autowired
    private ServiceDirectoryService service;

    @MockBean
    private ServiceDirectoryRepository repository;

    @Test
    public void addServiceDirectoryTest() {
        ServiceDirectory serviceDirectory = new ServiceDirectory(  );

        serviceDirectory.setId( 12l );

        Mockito.doReturn( null )
                .when( repository )
                .findById( 12l );

        boolean isServiceDirectoryCreated = service.addServiceDirectory( serviceDirectory );

        Assert.assertTrue( isServiceDirectoryCreated );

        Mockito.verify( repository, Mockito.times( 1 ) ).saveAndFlush( serviceDirectory );

    }

    @Test
    public void addServiceDirectoryFailTest() {
        ServiceDirectory serviceDirectory = new ServiceDirectory(  );

        serviceDirectory.setId( 12L );

        Mockito.doReturn( new Country() )
                .when( repository )
                .findById( 12l );

        boolean isServiceDirectoryCreated = service.addServiceDirectory( serviceDirectory );

        Assert.assertFalse( isServiceDirectoryCreated );

        Mockito.verify( repository, Mockito.times( 0 ) ).saveAndFlush( any() );
    }

    @Test
    public void deleteisServiceDirectoryTest() {
        Mockito.doReturn( Optional.of (ServiceDirectory.builder().id( 12l ).ServiceDirectoryName( "Posts" ).build())  )
                .when( repository )
                .findById( 12l );

        boolean isDeletedServiceDirectory = service.deleteServiceDirectory( 12l );

        Assert.assertTrue( isDeletedServiceDirectory );

        Mockito.verify( repository, Mockito.times( 1 ) ).deleteById( any() );
        Mockito.verify( repository, Mockito.times( 1 ) ).findById( any() );
    }

    @Test
    public void deleteServiceDirectoryFailTest() {
        Mockito.doReturn(  null  )
                .when( repository )
                .findById( 12l );

        boolean isDeletedServiceDirectory = service.deleteServiceDirectory( 12l );

        Assert.assertFalse( isDeletedServiceDirectory );

        Mockito.verify( repository, Mockito.times( 0 ) ).deleteById( any() );
        Mockito.verify( repository, Mockito.times( 1 ) ).findById( any() );
    }

    @Test
    public void getByNameTest() {
        ServiceDirectory serviceDirectory = new ServiceDirectory();
        serviceDirectory.setServiceDirectoryName( "Post" );

        Mockito.doReturn( serviceDirectory )
                .when( repository )
                .findByName( "Post" );

        ServiceDirectory expected = service.getServiceDirectoryByName("Post" );

        Assert.assertEquals(expected, serviceDirectory );

        Mockito.verify( repository, Mockito.times( 1 ) ).findByName( any() );
    }

    @Test
    public void editServiceDirectoryTest(){
        Optional<ServiceDirectory> serviceDirectory = Optional.of( new ServiceDirectory() );
        serviceDirectory.get().setId(1L);
        serviceDirectory.get().setServiceDirectoryName( "Street 12" );

        Mockito.doReturn( serviceDirectory )
                .when( repository )
                .findById(1L);

        boolean isServiceDirectoryEdit = service.editServiceDirectory( serviceDirectory.get() );

        Assert.assertTrue(isServiceDirectoryEdit);

        Mockito.verify( repository, Mockito.times( 1 ) ).saveAndFlush( any() );
        Mockito.verify( repository, Mockito.times( 1 ) ).findById( any() );
    }

    @Test
    public void  editServiceDirectoryFailTest(){
        ServiceDirectory serviceDirectory = new ServiceDirectory(  );
        serviceDirectory.setId(22L );
        serviceDirectory.setServiceDirectoryName( "Post" );

        Mockito.doReturn( null )
                .when( repository )
                .findById( 22l );

        boolean isServiceDirectoryEdit = service.editServiceDirectory( serviceDirectory );

        Assert.assertFalse(isServiceDirectoryEdit);

        Mockito.verify( repository, Mockito.times( 0 ) ).saveAndFlush( any() );
        Mockito.verify( repository, Mockito.times( 1 ) ).findById( any() );
    }

    @Test
    public void getAll(){
        List<ServiceDirectory> serviceDirectories = new ArrayList<>();
        serviceDirectories.add( new ServiceDirectory() );
        serviceDirectories.add( new ServiceDirectory() );

        Mockito.doReturn( serviceDirectories )
                .when( repository )
                .findAll( );

        var expectedList = service.getAllServiceDirectories();

        Assert.assertEquals( expectedList,serviceDirectories );

        Mockito.verify( repository, Mockito.times( 1 ) ).findAll(  );
    }
}
