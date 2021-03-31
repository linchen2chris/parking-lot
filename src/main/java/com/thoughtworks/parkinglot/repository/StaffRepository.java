package com.thoughtworks.parkinglot.repository;

import com.thoughtworks.parkinglot.entity.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface StaffRepository extends JpaRepository<Staff, String> {

    @Query(value = "select * from staff where id = ?1", nativeQuery = true)
    Staff findStaffById(String id);

    @Query(value = "select * from staff where is_free = true ORDER BY RANDOM() limit 1", nativeQuery = true)
    Staff pickAStaffRandomly();
}
