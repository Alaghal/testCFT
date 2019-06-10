package com.test.cft.repository;

import com.test.cft.domain.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CountryRepository extends JpaRepository<Country,Long> {
    @Query("select b from Country b where b.countryName = :name")
    Country findByName(@Param("name") String name);
}
