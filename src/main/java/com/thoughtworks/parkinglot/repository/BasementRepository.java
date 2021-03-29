package com.thoughtworks.parkinglot.repository;

import com.thoughtworks.parkinglot.entity.Basement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BasementRepository extends JpaRepository<Basement, Long> {

    @Query(value="select * from basement where available > 0 limit 1", nativeQuery = true)
    Basement findFirstBasementByAvailable();

}
