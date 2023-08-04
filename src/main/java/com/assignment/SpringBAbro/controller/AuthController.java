package com.assignment.SpringBAbro.controller;

import java.util.List;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.assignment.SpringBAbro.dao.UserDto;
import com.assignment.SpringBAbro.entity.User;
import com.assignment.SpringBAbro.service.UserService;
import org.springframework.stereotype.Controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import jakarta.validation.Valid;

@Controller
public class AuthController {
	
	Logger logger =LoggerFactory.getLogger(AuthController.class);
	private UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("index")
    public String home(){
        return "index";
    }

    @GetMapping("/login")
    public String loginForm() {
    	
    	logger.info("LOGGIN PAGE CALLED.....!!");
        return "login";
    }

    @GetMapping("register")
    public String showRegistrationForm(Model model){
        UserDto user = new UserDto();
        model.addAttribute("user", user);
        logger.info("REGISTERATION PAGE CALLED.....!!");
        return "register";
    }

    // handler method to handle register user form submit request
    @PostMapping("/register/save")
    public String registration(@Valid @ModelAttribute("user") UserDto user,
                               BindingResult result,
                               Model model){
        User existing = userService.findByEmail(user.getEmail());
        String password1= user.getPassword();
        //logger.info("PASSWORD PROVIDED OCCURRED " +user.getPassword());
        logger.info("INVITATION CODE PROVIDED OCCURRED " +user.getInvCode());
        if (existing != null) {
            result.rejectValue("email", null, "There is already an account registered with that email");
        }
        if (result.hasErrors()) {
        	
        	logger.info("ERROR OCCURRED.....!!");
            model.addAttribute("user", user);
            return "register";
        }
        
        logger.info("AFTER ERROR CHECK ");
        userService.saveUser(user);
        logger.info("USER DETAILS ENTRING  PAGE CALLED.....!!");
        return "redirect:/register?success";
    }

    @GetMapping("/users")
    public String listRegisteredUsers(Model model){
    	List<UserDto> users = userService.findAllUsers();
        model.addAttribute("users", users);
        return "users";
    }

}
