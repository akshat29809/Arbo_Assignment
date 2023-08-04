package com.assignment.SpringBAbro.service.Impl;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.assignment.SpringBAbro.controller.AuthController;
import com.assignment.SpringBAbro.dao.UserDto;
import com.assignment.SpringBAbro.entity.Role;
import com.assignment.SpringBAbro.entity.User;
import com.assignment.SpringBAbro.repository.RoleRepository;
import com.assignment.SpringBAbro.repository.UserRepository;
import com.assignment.SpringBAbro.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	
	
		Logger logger =LoggerFactory.getLogger(UserServiceImpl.class);
	
	    private UserRepository userRepository;
	    private RoleRepository roleRepository;
	    //private PasswordEncoder passwordEncoder;

	    public UserServiceImpl(UserRepository userRepository,
	                           RoleRepository roleRepository
	                           ) {
	        this.userRepository = userRepository;
	        this.roleRepository = roleRepository;
	        //this.passwordEncoder = passwordEncoder;
	    }

	    @Override
	    public void saveUser(UserDto userDto) {
	        User user = new User();
	        String INV_CODE="ABROTECHNOLOGY";
	        user.setName(userDto.getFirstName() + " " + userDto.getLastName());
	        user.setEmail(userDto.getEmail());
	        user.setPassword(userDto.getPassword());
	        Role role = roleRepository.findByName("ROLE_ADMIN");
	        logger.info("INSIDE SAVE_USER METHOD PASSWORD PROVIDED OCCURRED " +userDto.getInvCode());
	        if((userDto.getInvCode()).equals(INV_CODE)) {
	        	logger.info("INSIDE SET INV IF BLOCK" +userDto.getInvCode());
	        	user.setInviteCode(userDto.getInvCode());
	        	
	        }
	        if(role == null){
	            role = checkRoleExist();
	        }
	        user.setRoles(Arrays.asList(role));
	        userRepository.save(user);
	    }

	    @Override
	    public User findByEmail(String email) {
	        return userRepository.findByEmail(email);
	    }

	    @Override
	    public List<UserDto> findAllUsers() {
	        List<User> users = userRepository.findAll();
	        return users.stream().map((user) -> convertEntityToDto(user))
	                .collect(Collectors.toList());
	    }

	    private UserDto convertEntityToDto(User user){
	        UserDto userDto = new UserDto();
	        String[] name = user.getName().split(" ");
	        userDto.setFirstName(name[0]);
	        userDto.setLastName(name[1]);
	        userDto.setEmail(user.getEmail());
	        userDto.setInvCode(user.getInviteCode());
	        return userDto;
	    }

	    private Role checkRoleExist() {
	        Role role = new Role();
	        role.setName("ROLE_ADMIN");
	        return roleRepository.save(role);
	    }

}
