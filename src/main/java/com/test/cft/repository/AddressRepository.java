package com.test.cft.repository;

import com.test.cft.domain.Address;
import com.test.cft.domain.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;


public interface AddressRepository extends JpaRepository<Address,Long> {
    @Query("select b from Address b where b.addressName = :name")
    Address findByName(@Param("name") String name);


    @Modifying(clearAutomatically = true)
    @Transactional
    @Query("update Address b set  b.addressName=:name, b.city =:city  where b.id = :id")
    void updateAddress(@Param( "id" ) Long id,
                       @Param( "name" ) String name,
                       @Param( "city" ) City city);

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query("delete from Address b where b.id = :id")
    void deleteAddressById(@Param( "id" ) Long id);
}
