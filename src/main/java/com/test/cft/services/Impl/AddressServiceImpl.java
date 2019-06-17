package com.test.cft.services.Impl;

import com.test.cft.domain.Address;
import com.test.cft.repository.AddressRepository;
import com.test.cft.services.AddressService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class AddressServiceImpl implements AddressService {
    private  final AddressRepository repository;

    public AddressServiceImpl(AddressRepository repository){
        this.repository=repository;
    }

    @Override
    public boolean addAddress(Address address) {
        var addressFromDB = Optional.ofNullable(repository.findByName( address.getAddressName() ));
        if (addressFromDB.isPresent()) {
            return false;
        }

        repository.saveAndFlush( address );
        log.info( "Added " + address.toString() + " " + LocalDate.now() );
        return true;
    }

    @Override
    public boolean deleteAddress(long id) {
        Optional<Address> addressFromDB = repository.findById( id );

        if(addressFromDB == null){
            return false;
        }

        repository.deleteById( id );
        log.info( "Delete " + addressFromDB.get().toString() + " " + LocalDate.now() );

        return true;
    }

    @Override
    public boolean editAddress(Address address) {
        Optional<Address> addressFromDB = repository.findById( address.getId() );
        if (addressFromDB == null) {
            return false;
        }
        repository.saveAndFlush( address );
        log.info( "Edit  old version = " + addressFromDB.get().toString() + ", new version =" + address.toString() + " " + LocalDate.now() );
        return true;
    }

    @Override
    public Address getAddressByName(String addressName) {
        return Optional.ofNullable(repository.findByName( addressName )).orElse( new Address(  ) );
    }

    @Override
    public Address getAddressById(long id) {
        return  repository.findById( id ).get();
    }

    @Override
    public List<Address> getAllAddresses() {
        return Optional.ofNullable(repository.findAll()).orElse( new ArrayList<>( ) );
    }
}
