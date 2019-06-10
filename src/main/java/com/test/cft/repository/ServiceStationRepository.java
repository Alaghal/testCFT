package com.test.cft.repository;

import com.test.cft.domain.ServiceStation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ServiceStationRepository extends JpaRepository<ServiceStation, Long> {
    @Query("select b from ServiceStation b where b.ServiceStationName = :name")
    ServiceStation findByName(@Param("name") String name);
}
