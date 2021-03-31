package com.thoughtworks.parkinglot.repository;

import com.thoughtworks.parkinglot.entity.Boy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface BoyRepository extends JpaRepository<Boy, String> {

    @Query(value = "select * from boy where id = ?1", nativeQuery = true)
    Boy findParkingBoyById(String id);
}
