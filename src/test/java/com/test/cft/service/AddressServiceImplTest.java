package com.test.cft.service;

import com.test.cft.domain.Address;
import com.test.cft.repository.AddressRepository;
import com.test.cft.services.AddressService;
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
public class AddressServiceImplTest {
    @Autowired
    private AddressService service;

    @MockBean
    private AddressRepository repository;

    @Test
    public void addAddressTest() {
        Address address = new Address(  );

        address.setId( 12l );

        Mockito.doReturn( null )
                .when( repository )
                .findById( 12l );

        boolean isAddressCreated = service.addAddress( address );

        Assert.assertTrue( isAddressCreated );

        Mockito.verify( repository, Mockito.times( 1 ) ).saveAndFlush( address );

    }

    @Test
    public void addAddressFailTest() {
        Address address = new Address(  );

        address.setId( 12L );

        Mockito.doReturn( new Address() )
                .when( repository )
                .findById( 12l );

        boolean isAddressCreated = service.addAddress( address );

        Assert.assertFalse( isAddressCreated );

        Mockito.verify( repository, Mockito.times( 0 ) ).saveAndFlush( any() );
    }

    @Test
    public void deleteAddressTest() {
        Mockito.doReturn( Optional.of (Address.builder().id( 12l ).addressName( "Street" ).build())  )
                .when( repository )
                .findById( 12l );

        boolean isDeletedAddress = service.deleteAddress( 12l );

        Assert.assertTrue( isDeletedAddress );

        Mockito.verify( repository, Mockito.times( 1 ) ).deleteById( any() );
        Mockito.verify( repository, Mockito.times( 1 ) ).findById( any() );
    }

    @Test
    public void deleteAddressFailTest() {
        Mockito.doReturn(  null  )
                .when( repository )
                .findById( 12l );

        boolean isDeletedAddress = service.deleteAddress( 12l );

        Assert.assertFalse( isDeletedAddress );

        Mockito.verify( repository, Mockito.times( 0 ) ).deleteById( any() );
        Mockito.verify( repository, Mockito.times( 1 ) ).findById( any() );
    }

    @Test
    public void getByNameTest() {
        Address address = new Address();
        address.setAddressName( "Street 12" );

        Mockito.doReturn( address )
                .when( repository )
                .findByName( "Street 12" );

        Address expected = service.getAddressByName("Street 12" );

        Assert.assertEquals(expected, address );

        Mockito.verify( repository, Mockito.times( 1 ) ).findByName( any() );
    }

    @Test
    public void editAddressTest(){
        Optional<Address> address = Optional.of( new Address() );
        address.get().setId(1L);
        address.get().setAddressName( "Street 12" );

        Mockito.doReturn( address )
                .when( repository )
                .findById(1L);

        boolean isAddressEdit = service.editAddress( address.get() );

        Assert.assertTrue(isAddressEdit);

        Mockito.verify( repository, Mockito.times( 1 ) ).saveAndFlush( any() );
        Mockito.verify( repository, Mockito.times( 1 ) ).findById( any() );
    }

    @Test
    public void  editAddressFailTest(){
        Address address = new Address();
        address.setId(22L );
        address.setAddressName( "Street 12" );

        Mockito.doReturn( null )
                .when( repository )
                .findById( 22l );

        boolean isAddressEdit = service.editAddress( address );

        Assert.assertFalse(isAddressEdit);

        Mockito.verify( repository, Mockito.times( 0 ) ).saveAndFlush( any() );
        Mockito.verify( repository, Mockito.times( 1 ) ).findById( any() );
    }

    @Test
    public void getAll(){
        List<Address> addresses = new ArrayList<>();
        addresses.add( new Address() );
        addresses.add( new Address() );

        Mockito.doReturn( addresses )
                .when( repository )
                .findAll( );

        var expectedList = service.getAllAddresses();

        Assert.assertEquals( expectedList,addresses );

        Mockito.verify( repository, Mockito.times( 1 ) ).findAll(  );
    }

}

