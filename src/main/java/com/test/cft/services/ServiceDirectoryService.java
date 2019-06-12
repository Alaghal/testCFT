package com.test.cft.services;

import com.test.cft.domain.ServiceDirectory;

import java.util.List;

public interface ServiceDirectoryService {
    boolean addServiceDirectory(ServiceDirectory serviceDirectory);
    boolean deleteServiceDirectory (long id);
    boolean editServiceDirectory(ServiceDirectory serviceDirectory );
    ServiceDirectory getServiceDirectoryByName(String serviceDirectoryName);
    ServiceDirectory getServiceDirectoryById (long id);
    List<ServiceDirectory> getAllServiceDirectories();
}
