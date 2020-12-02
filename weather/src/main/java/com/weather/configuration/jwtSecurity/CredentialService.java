package com.weather.configuration.jwtSecurity;

import java.util.ArrayList;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;




@Service
public class CredentialService implements UserDetailsService {
	
	
	@Override
	public UserDetails loadUserByUsername(String userName)
			throws UsernameNotFoundException {
		 
		if ("admin".equals(userName)) {
			return new User("admin", "$2a$10$cejO7IgB28t99LgtLLpetuYxIKJM/22L6OP4KvBa2G3lZTBsrCHTy",new ArrayList<>());
		} else {
			throw new UsernameNotFoundException("User not found with username: " + userName);
		}
		
		
	}
}

