package com.test.cft.services;

import com.test.cft.domain.ServiceStation;

import java.util.List;

public interface ServiceStationService {
    boolean addServiceStation(ServiceStation serviceStation);
    boolean deleteServiceStation(long id);
    boolean editServiceStation(ServiceStation serviceStation);
    ServiceStation getServiceStationByName(String serviceStationName);
    ServiceStation getServiceStationById (long id);
    List<ServiceStation> getAllServiceStations();
}
