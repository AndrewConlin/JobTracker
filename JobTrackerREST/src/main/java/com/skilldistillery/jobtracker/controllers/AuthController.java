package com.skilldistillery.jobtracker.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.jobtracker.entites.User;
import com.skilldistillery.jobtracker.services.AuthService;

@RestController
@RequestMapping("/auth")
@CrossOrigin({"*", "http://localhost:4200"})
public class AuthController {
	@Autowired
	private AuthService authService;

    @RequestMapping(path="/register", method=RequestMethod.POST)
    public void signUp(@RequestBody User user) {
        authService.registerUser(user);
    }
}
