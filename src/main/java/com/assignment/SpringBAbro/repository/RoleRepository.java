package com.assignment.SpringBAbro.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.assignment.SpringBAbro.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
	
	Role findByName(String name);

}
