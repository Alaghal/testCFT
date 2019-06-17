package com.test.cft.repository;

import com.test.cft.domain.Address;
import com.test.cft.domain.ServiceDirectory;
import com.test.cft.domain.ServiceStation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public interface ServiceStationRepository extends JpaRepository<ServiceStation, Long> {
    @Query("select b from ServiceStation b where b.serviceStationName = :name")
    ServiceStation findByName(@Param("name") String name);

}
