package com.thoughtworks.parkinglot.repository;

import com.thoughtworks.parkinglot.entity.Basement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BasementRepository extends JpaRepository<Basement, Long> {

    @Query(value="select * from basement where availability > 0 ORDER BY id limit 1", nativeQuery = true)
    Basement findFirstBasementByAvailable();

    @Query(value = "select * from basement where availability > 0 ORDER BY availability DESC limit 1", nativeQuery = true)
    Basement findBasementWithMostAvailability();

    @Query(value = "select * from basement where id = ?1", nativeQuery = true)
    Basement findBasementById(Long basement_id);
}
