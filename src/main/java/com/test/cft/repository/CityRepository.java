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

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query("update City b set  b.cityName=:name, b.country =:country  where b.id = :id")
    void updateCity(@Param( "id" ) Long id,
                    @Param( "name" ) String name,
                    @Param( "country" ) Country country);

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query("delete from City b where b.id =:id")
    void deleteCityById(@Param( "id" ) Long id);
}
