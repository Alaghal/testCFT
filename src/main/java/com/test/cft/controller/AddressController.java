package com.test.cft.controller;

import com.test.cft.domain.Address;
import com.test.cft.exeption.AddressNotFoundExeption;
import com.test.cft.services.AddressService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class AddressController {
    private final AddressService service;

    public  AddressController(AddressService service){
        this.service=service;
    }

    @GetMapping("/addresses")
    public List getAddresses(){
        return service.getAllAddresses();
    }

    @GetMapping("/address/{id}")
    public Address getAddressById(@PathVariable(value = "id") Long addressId) throws AddressNotFoundExeption {
        return service.getAddressById( addressId );
    }

    @GetMapping("/address/{name}")
    public Address getAddressByName(@PathVariable(value = "name") String name){
        return  service.getAddressByName( name );
    }

    @PostMapping("/address")
    public  Address createAddress(@Valid @RequestBody Address address){
        if (service.addAddress( address ))
            return address;
        return new Address();
    }

    @PutMapping("/address/{id}")
    public  Address updateAddress(@PathVariable(value = "id") Long addressId,
                                  @Valid @RequestBody Address addressDetails) throws  AddressNotFoundExeption{
        Address address = service.getAddressById( addressId );

        address.setAddressName( address.getAddressName());
        address.setServiceStation( addressDetails.getServiceStation());
        address.setCity( addressDetails.getCity() );


        if (service.editAddress( address ))
            return address;
        return  new Address();
    }

    @DeleteMapping ("/address/{id}")
    public ResponseEntity deleteAddress(@PathVariable(value = "id") Long addressId) throws  AddressNotFoundExeption{
        if (service.deleteAddress( addressId ))
            return ResponseEntity.ok(  ).build();
        return ResponseEntity.badRequest().build();
    }


}
