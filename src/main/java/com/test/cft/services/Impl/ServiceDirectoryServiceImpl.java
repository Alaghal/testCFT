package com.test.cft.services.Impl;


import com.test.cft.domain.ServiceDirectory;
import com.test.cft.repository.ServiceDirectoryRepository;
import com.test.cft.services.ServiceDirectoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ServiceDirectoryServiceImpl implements ServiceDirectoryService {
    private  final ServiceDirectoryRepository repository;

    public ServiceDirectoryServiceImpl(ServiceDirectoryRepository repository){
        this.repository=repository;
    }

    @Override
    public boolean addServiceDirectory(ServiceDirectory serviceDirectory) {
        var serviceDirectoryFromDB = Optional.ofNullable(repository.findByName( serviceDirectory.getServiceDirectoryName() ));
        if (serviceDirectoryFromDB.isPresent()) {
            return false;
        }

        repository.saveAndFlush( serviceDirectory );
        log.info( "Added " + serviceDirectory.toString() + " " + LocalDate.now() );
        return true;
    }

    @Override
    public boolean deleteServiceDirectory(long id) {
        Optional<ServiceDirectory> serviceDirectoryFromDB = repository.findById( id );

        if(serviceDirectoryFromDB == null){
            return false;
        }

        repository.deleteById( id );

        log.info( "Delete " + serviceDirectoryFromDB.get().toString() + " " + LocalDate.now() );

        return true;
    }

    @Override
    public boolean editServiceDirectory(ServiceDirectory serviceDirectory) {
        Optional<ServiceDirectory> serviceDirectoryFromDB = repository.findById( serviceDirectory.getId() );
        if (serviceDirectoryFromDB == null) {
            return false;
        }

        repository.saveAndFlush( serviceDirectory );
        log.info( "Edit  old version = " + serviceDirectoryFromDB.get().toString() + ", new version =" + serviceDirectory.toString() + " " + LocalDate.now() );
        return true;
    }

    @Override
    public ServiceDirectory getServiceDirectoryByName(String serviceDirectoryName) {
        return Optional.ofNullable(repository.findByName( serviceDirectoryName )).orElse( new ServiceDirectory(  ) );
    }

    @Override
    public ServiceDirectory getServiceDirectoryById(long id) {
       return Optional.ofNullable( repository.findById( id ).get()).orElse( new ServiceDirectory(  ) );
    }

    @Override
    public List<ServiceDirectory> getAllServiceDirectories() {
        return Optional.ofNullable(repository.findAll()).orElse( new ArrayList<>( ) );
    }
}
