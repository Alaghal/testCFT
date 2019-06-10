package com.test.cft.repository;

import com.test.cft.domain.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CityRepository extends JpaRepository<City,Long> {
    @Query("select b from City b where b.cityName = :name")
    City findByName(@Param("name") String name);
}
