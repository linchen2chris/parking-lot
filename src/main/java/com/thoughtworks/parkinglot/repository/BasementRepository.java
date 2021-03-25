package com.thoughtworks.parkinglot.repository;

import com.thoughtworks.parkinglot.entity.Basement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BasementRepository extends JpaRepository<Basement, Long> {
}
