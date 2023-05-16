package com.geekster.studentportal.repositories;

import com.geekster.studentportal.models.Laptop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ILaptopDao extends JpaRepository<Laptop,Integer> {

    void deleteLaptopByLaptopId(Integer productId);

    @Modifying
    @Query(value = "update laptops set price =:price where laptop_id = :laptopId",nativeQuery = true)
    void updateLaptopPriceByLaptopId(Long laptopId, Double price);

    Laptop findByLaptopId(Long laptopId);
}
