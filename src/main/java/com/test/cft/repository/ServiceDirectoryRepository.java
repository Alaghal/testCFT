package com.test.cft.repository;

import com.test.cft.domain.ServiceDirectory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ServiceDirectoryRepository extends JpaRepository<ServiceDirectory, Long> {
    @Query("select b from ServiceDirectory b where b.serviceDirectoryName = :name")
    ServiceDirectory findByName(@Param("name") String name);
}
