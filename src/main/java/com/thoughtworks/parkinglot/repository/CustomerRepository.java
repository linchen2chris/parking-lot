package com.thoughtworks.parkinglot.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.thoughtworks.parkinglot.entity.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
