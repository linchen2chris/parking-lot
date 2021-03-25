package com.thoughtworks.parkinglot.repository;

import com.thoughtworks.parkinglot.entity.Garage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface GarageRepository extends JpaRepository<Garage, Long> {

    @Query(value="select * from garage where is_available = true limit 1", nativeQuery = true)
    Garage findFirstByAvailableIsTrue();

}
