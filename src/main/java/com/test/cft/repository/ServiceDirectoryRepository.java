package com.test.cft.repository;

import com.test.cft.domain.ServiceDirectory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface ServiceDirectoryRepository extends JpaRepository<ServiceDirectory, Long> {
    @Query("select b from ServiceDirectory b where b.serviceDirectoryName = :name")
    ServiceDirectory findByName(@Param("name") String name);

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query("update ServiceDirectory b set  b.serviceDirectoryName=:name  where b.id = :id")
    void updateServiceDirectory(@Param( "id" ) Long id,
                                @Param( "name" ) String name);

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query("delete from ServiceDirectory b where b.id = :id")
    void deleteServiceDirectoryById(@Param( "id" ) Long id);
}
