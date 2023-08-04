package com.assignment.SpringBAbro.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.assignment.SpringBAbro.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
