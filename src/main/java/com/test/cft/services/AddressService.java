package com.test.cft.services;

import com.test.cft.domain.Address;

import java.util.List;

public interface AddressService {
    boolean addAddress(Address address);
    boolean deleteAddress(long id);
    boolean editAddress (Address address);
    Address getAddressByName(String addressName);
    Address getAddressById (long id);
    List<Address> getAllAddresses();

}

