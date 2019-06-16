package com.test.cft.controller;

import com.test.cft.domain.ServiceDirectory;
import com.test.cft.exeption.ServiceDirectoryNotFoundExeption;
import com.test.cft.services.ServiceDirectoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/serviceDirectories")
public class ServiceDirectoryController {
    private  final ServiceDirectoryService service;

    public ServiceDirectoryController(ServiceDirectoryService service){
        this.service=service;
    }

    @GetMapping()
    public List getServiceDirectories(){
        return service.getAllServiceDirectories();
    }

    @GetMapping("/id")
    public ServiceDirectory getServiceDirectoryById(@RequestParam(value = "id", required = true) Long id)  {
        return service.getServiceDirectoryById( id );
    }

    @GetMapping("/name")
    public ServiceDirectory getServiceDirectoryByName(@RequestParam(value = "name", required = true)  String serviceDirectoryName){
       return service.getServiceDirectoryByName( serviceDirectoryName );
    }

    @PostMapping()
    public  ServiceDirectory createServiceDirectory(@Valid @RequestBody ServiceDirectory serviceDirectory){
        if (service.addServiceDirectory( serviceDirectory )){
            return  serviceDirectory;
        }
        return new ServiceDirectory(  );
    }

    @PutMapping("/id")
    public ServiceDirectory updateServiceDirectory(@RequestParam(value = "id", required = true) Long id,
                                 @Valid @RequestBody ServiceDirectory serviceDirectoryDetails) {
        ServiceDirectory serviceDirectory = service.getServiceDirectoryById( id );

        serviceDirectory.setServiceDirectoryName( serviceDirectoryDetails.getServiceDirectoryName());
        serviceDirectory.setServiceStations( serviceDirectoryDetails.getServiceStations());

        if (service.editServiceDirectory( serviceDirectory ))
            return serviceDirectory;
        return  new ServiceDirectory();
    }

    @DeleteMapping("/id")
    public ResponseEntity deleteCountry(@RequestParam(value = "id", required = true) Long id) {
        if (service.deleteServiceDirectory( id ))
            return ResponseEntity.ok(  ).build();
        return ResponseEntity.badRequest().build();
    }

}
