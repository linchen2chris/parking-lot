package com.thoughtworks.parkinglot.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.thoughtworks.parkinglot.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
