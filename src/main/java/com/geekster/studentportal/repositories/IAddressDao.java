package com.geekster.studentportal.repositories;

import com.geekster.studentportal.models.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IAddressDao extends JpaRepository<Address,Integer> {
    @Modifying
    @Query(value = "update address set landmark=:landmark where address_id=:addressId",nativeQuery = true)
    void UpdateAddress(Long addressId, String landmark);
}
