package com.test.cft.controller;

import com.test.cft.domain.Address;
import com.test.cft.exeption.AddressNotFoundExeption;
import com.test.cft.services.AddressService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/addresses")
public class AddressController {
    private final AddressService service;

    public  AddressController(AddressService service){
        this.service=service;
    }

    @GetMapping()
    public List getAddresses(){
        return service.getAllAddresses();
    }

    @GetMapping("/id")
    public Address getAddressById(@RequestParam(value = "id", required = true) Long id)  {
        return service.getAddressById(id );
    }

    @GetMapping("/name")
    public Address getAddressByName(@RequestParam(value = "name",required = true ) String name){
        return  service.getAddressByName( name );
    }

    @PostMapping()
    public  Address createAddress(@Valid @RequestBody Address address){
        if (service.addAddress( address ))
            return address;
        return new Address();
    }

    @PutMapping("/id")
    public  Address updateAddress(@RequestParam(value = "id", required = true) Long id,
                                  @Valid @RequestBody Address addressDetails) {
        Address address = service.getAddressById(id);

        address.setAddressName( addressDetails.getAddressName());

        address.setCity( addressDetails.getCity() );

        if (service.editAddress( address ))
            return address;
        return  new Address();
    }

    @DeleteMapping ("/id")
    public ResponseEntity deleteAddress(@RequestParam(value = "id", required = true) Long id) {
        if (service.deleteAddress(id))
            return ResponseEntity.ok(  ).build();
        return ResponseEntity.badRequest().build();
    }


}
