package com.skilldistillery.jobtracker.services;

import static java.util.Collections.emptyList;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.skilldistillery.jobtracker.repositories.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	 private UserRepository userRepository;

	    public UserDetailsServiceImpl(UserRepository userRepository) {
	        this.userRepository = userRepository;
	    }

	    @Override
	    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	        com.skilldistillery.jobtracker.entites.User applicationUser = userRepository.findByUsername(username);
	        
	        if (applicationUser == null) {
	            throw new UsernameNotFoundException(username);
	        }
	        return new User(applicationUser.getUsername(), applicationUser.getPassword(), emptyList()); // user details user class
	    }
}
