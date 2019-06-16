package com.test.cft.services.Impl;

import com.test.cft.domain.ServiceStation;
import com.test.cft.repository.ServiceStationRepository;
import com.test.cft.services.ServiceStationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ServiceStationServiceImpl implements ServiceStationService {
    private  final ServiceStationRepository repository;

    public ServiceStationServiceImpl(ServiceStationRepository repository){
        this.repository=repository;
    }


    @Override
    public boolean addServiceStation(ServiceStation serviceStation) {
        var serviceStationFromDB = Optional.ofNullable(repository.findByName( serviceStation.getServiceStationName() ));
        if (serviceStationFromDB.isPresent()) {
            return false;
        }

        repository.saveAndFlush( serviceStation );
        log.info( "Added " + serviceStation.toString() + " " + LocalDate.now() );
        return true;
    }

    @Override
    public boolean deleteServiceStation(long id) {
        Optional<ServiceStation> serviceStationFromDB = repository.findById( id );

        if(serviceStationFromDB == null){
            return false;
        }

        repository.deleteServiceStationById( id );

        log.info( "Delete " + serviceStationFromDB.get().toString() + " " + LocalDate.now() );

        return true;
    }

    @Override
    public boolean editServiceStation(ServiceStation serviceStation) {
        Optional<ServiceStation> serviceStationFromDB = repository.findById( serviceStation.getId() );
        if (serviceStationFromDB == null) {
            return false;
        }
        repository.saveAndFlush( serviceStation );
        log.info( "Edit  old version = " + serviceStationFromDB.get().toString() + ", new version =" + serviceStation.toString() + " " + LocalDate.now() );
        return true;
    }

    @Override
    public ServiceStation getServiceStationByName(String serviceStationName) {
        return Optional.ofNullable(repository.findByName( serviceStationName )).orElse( new ServiceStation(  ) );
    }

    @Override
    public ServiceStation getServiceStationById(long id) {
        return Optional.ofNullable( repository.findById( id ).get()).orElse( new ServiceStation(  ) );
    }

    @Override
    public List<ServiceStation> getAllServiceStations() {
        return Optional.ofNullable(repository.findAll()).orElse( new ArrayList<>( ) );
    }
}
