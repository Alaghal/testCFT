package com.test.cft.repository;

import com.test.cft.domain.City;
import com.test.cft.domain.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface CityRepository extends JpaRepository<City,Long> {
    @Query("select b from City b where b.cityName =:name")
    City findByName(@Param("name") String name);



}
