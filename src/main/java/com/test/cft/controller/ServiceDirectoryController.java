package com.test.cft.controller;

import com.test.cft.repository.ServiceDirectoryRepository;

public class ServiceDirectoryController {
    private  final ServiceDirectoryRepository  repository;

    public ServiceDirectoryController(ServiceDirectoryRepository repository){
        this.repository=repository;

    }
}
