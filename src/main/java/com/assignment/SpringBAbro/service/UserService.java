package com.assignment.SpringBAbro.service;

import java.util.List;

import com.assignment.SpringBAbro.dao.UserDto;
import com.assignment.SpringBAbro.entity.User;

public interface UserService {
	
	void saveUser(UserDto userDto);

    User findByEmail(String email);

    List<UserDto> findAllUsers();

}
