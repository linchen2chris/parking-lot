package com.thoughtworks.parkinglot.repository;

import com.thoughtworks.parkinglot.entity.Manager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ManagerRepository extends JpaRepository<Manager, String> {

    @Query(value = "select * from manager where id = ?1", nativeQuery = true)
    Manager findManagerById(String id);
}
