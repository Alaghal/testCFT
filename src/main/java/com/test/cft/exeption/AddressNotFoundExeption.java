package com.test.cft.exeption;

import com.test.cft.domain.Address;

public class AddressNotFoundExeption extends Exception {

    public AddressNotFoundExeption(long addressId) {
        super(String.format("Address is not found with id : '%s'", addressId));
    }
}
