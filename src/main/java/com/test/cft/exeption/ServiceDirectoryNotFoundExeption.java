package com.test.cft.exeption;

public class ServiceDirectoryNotFoundExeption extends  Exception {

    public  ServiceDirectoryNotFoundExeption(long serviceDirectoryId){
        super(String.format(" ServiceDirectory is not found with id : '%s'", serviceDirectoryId));
    }
}
