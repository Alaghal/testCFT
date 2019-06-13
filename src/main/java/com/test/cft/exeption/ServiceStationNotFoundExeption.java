package com.test.cft.exeption;

public class ServiceStationNotFoundExeption extends Exception {
    
    public ServiceStationNotFoundExeption(long serviceStationId){
        super(String.format("ServiceStation is not found with id : '%s'", serviceStationId));
    }

}
