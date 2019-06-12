package com.test.cft.controller;

import com.test.cft.domain.ServiceDirectory;
import com.test.cft.exeption.ServiceDirectoryNotFoundExeption;
import com.test.cft.services.ServiceDirectoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class ServiceDirectoryController {
    private  final ServiceDirectoryService service;

    public ServiceDirectoryController(ServiceDirectoryService service){
        this.service=service;
    }

    @GetMapping("/serviceDirectories")
    public List getServiceDirectories(){
        return service.getAllServiceDirectories();
    }

    @GetMapping("/serviceDirectories/{id}")
    public ServiceDirectory getServiceDirectoryById(@PathVariable(value = "id") Long serviceDirectoryId) throws ServiceDirectoryNotFoundExeption {
        return service.getServiceDirectoryById( serviceDirectoryId );
    }

    @GetMapping("/serviceDirectories/{name}")
    public ServiceDirectory getServiceDirectoryByName(@PathVariable(value = "name") String serviceDirectoryName){
       return service.getServiceDirectoryByName( serviceDirectoryName );
    }

    @PostMapping("/serviceDirectories")
    public  ServiceDirectory createServiceDirectory(@Valid @RequestBody ServiceDirectory serviceDirectory){
        if (service.addServiceDirectory( serviceDirectory )){
            return  serviceDirectory;
        }
        return new ServiceDirectory(  );
    }

    @PutMapping("/serviceDirectories/{id}")
    public ServiceDirectory updateServiceDirectory(@PathVariable(value = "id") Long serviceDirectoryId,
                                 @Valid @RequestBody ServiceDirectory serviceDirectoryDetails) throws  ServiceDirectoryNotFoundExeption{
        ServiceDirectory serviceDirectory = service.getServiceDirectoryById( serviceDirectoryId );

        serviceDirectory.setServiceDirectoryName( serviceDirectoryDetails.getServiceDirectoryName());
        serviceDirectory.setServiceStations( serviceDirectoryDetails.getServiceStations());

        if (service.editServiceDirectory( serviceDirectory ))
            return serviceDirectory;
        return  new ServiceDirectory();
    }

    @DeleteMapping("/serviceDirectories/{id}")
    public ResponseEntity deleteCountry(@PathVariable(value = "id") Long serviceDirectoryId) throws ServiceDirectoryNotFoundExeption{
        if (service.deleteServiceDirectory( serviceDirectoryId ))
            return ResponseEntity.ok(  ).build();
        return ResponseEntity.badRequest().build();
    }

}
