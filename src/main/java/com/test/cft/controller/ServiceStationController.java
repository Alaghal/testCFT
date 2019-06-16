package com.test.cft.controller;

import com.test.cft.domain.ServiceDirectory;
import com.test.cft.domain.ServiceStation;
import com.test.cft.exeption.ServiceStationNotFoundExeption;
import com.test.cft.services.ServiceStationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/serviceStations")
public class ServiceStationController {
    private  final ServiceStationService service;

    public  ServiceStationController(ServiceStationService service){
        this.service=service;
    }

    @GetMapping()
    public List getServiceStations(){
        return service.getAllServiceStations();
    }

    @GetMapping("/id")
    public ServiceStation getServiceStationById(@RequestParam(value = "id", required = true) String id) {
        return service.getServiceStationById(Long.parseLong(id));
    }

    @GetMapping("/name")
    public ServiceStation getServiceStationByName(@RequestParam(value = "name", required = true) String serviceDirectoryName){
        return  service.getServiceStationByName(serviceDirectoryName);
    }

    @PostMapping()
    public  ServiceStation  createServiceStation(@Valid @RequestBody ServiceStation serviceStation){
        if (service.addServiceStation( serviceStation )){
            return  serviceStation;
        }
        return new ServiceStation(  );
    }

    @PutMapping("/id")
    public ServiceStation updateServiceStation(@RequestParam(value = "id", required = true)  Long serviceStationId,
                                               @Valid @RequestBody ServiceStation serviceStationDetails) {
        ServiceStation serviceStation = service.getServiceStationById( serviceStationId );

        serviceStation.setServiceStationName( serviceStationDetails.getServiceStationName());
        serviceStation.setAddress( serviceStationDetails.getAddress());
        serviceStation.setServiceDirectories( serviceStationDetails.getServiceDirectories() );

        if (service.editServiceStation( serviceStation ))
            return serviceStation;
        return  new ServiceStation();
    }

    @DeleteMapping("/id")
    public ResponseEntity deleteServiceStation(@RequestParam(value = "id", required = true) Long id) {
        if (service.deleteServiceStation(id ))
            return ResponseEntity.ok(  ).build();
        return ResponseEntity.badRequest().build();
    }
}
