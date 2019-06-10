package com.test.cft.controller;

import com.test.cft.repository.ServiceStationRepository;

public class ServiceStationController {
    private  final ServiceStationRepository repository;

    public  ServiceStationController(ServiceStationRepository repository){
        this.repository=repository;
    }

}
