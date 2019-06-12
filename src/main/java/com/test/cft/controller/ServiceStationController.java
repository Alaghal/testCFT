package com.test.cft.controller;

import com.test.cft.domain.ServiceDirectory;
import com.test.cft.domain.ServiceStation;
import com.test.cft.services.ServiceStationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class ServiceStationController {
    private  final ServiceStationService service;

    public  ServiceStationController(ServiceStationService service){
        this.service=service;
    }

    @GetMapping("/serviceStations")
    public List getServiceStations(){
         return service.getAllServiceStations();
    }

    @GetMapping("/serviceStations/{id}")
    public ServiceStation getServiceStationById(@PathVariable(value = "id") Long serviceDirectoryId){
        return service.getServiceStationById(serviceDirectoryId);
    }

    @GetMapping("/serviceStations/{name}")
    public ServiceStation getServiceStationByName(@PathVariable(value = "name") String serviceDirectoryName){
        return  service.getServiceStationByName(serviceDirectoryName);
    }

    @PostMapping("/serviceStations")
    public  ServiceStation  createServiceStation(@Valid @RequestBody ServiceStation serviceStation){
        if (service.addServiceStation( serviceStation )){
            return  serviceStation;
        }
        return new ServiceStation(  );
    }

    @PutMapping("/serviceStation/{id}")
    public ServiceStation updateServiceStation(@PathVariable(value = "id") Long serviceStationId,
                                               @Valid @RequestBody ServiceStation serviceStationDetails){
        ServiceStation serviceStation = service.getServiceStationById( serviceStationId );

        serviceStation.setServiceStationName( serviceStationDetails.getServiceStationName());
        serviceStation.setAddress( serviceStationDetails.getAddress());
        serviceStation.setServiceDirectories( serviceStationDetails.getServiceDirectories() );

        if (service.editServiceStation( serviceStation ))
            return serviceStation;
        return  new ServiceStation();
    }

    @DeleteMapping("/serviceStation/{id}")
    public ResponseEntity deleteServiceStation(@PathVariable(value = "id") Long serviceStationId){
        if (service.deleteServiceStation( serviceStationId ))
            return ResponseEntity.ok(  ).build();
        return ResponseEntity.badRequest().build();
    }
}
