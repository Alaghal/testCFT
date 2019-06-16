package com.test.cft.repository;

import com.test.cft.domain.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface CountryRepository extends JpaRepository<Country,Long> {
    @Query("select b from Country b where b.countryName = :name")
    Country findByName(@Param("name") String name);

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query("update Country b set  b.countryName=:name  where b.id = :id")
    void updateCountry(@Param( "id" ) Long id,
                       @Param( "name" ) String name);

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query("delete from Country b where b.id = :id")
    void deleteCountryById(@Param( "id" ) Long id);
}
