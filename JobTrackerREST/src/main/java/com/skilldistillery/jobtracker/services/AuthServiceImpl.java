package com.skilldistillery.jobtracker.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.skilldistillery.jobtracker.entites.User;
import com.skilldistillery.jobtracker.repositories.UserRepository;

@Service
public class AuthServiceImpl implements AuthService{
	@Autowired
    private UserRepository userRepo;
    
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	
	public void registerUser(User user) {
		user.setRole("standard");
		user.setEnabled(true);
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepo.saveAndFlush(user);
	}
	
}
