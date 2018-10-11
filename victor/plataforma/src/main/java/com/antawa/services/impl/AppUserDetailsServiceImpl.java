package com.antawa.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.antawa.model.User;

/**
 * This Service class for providing the user credentials from the database.
 * 
 * @author Victor Quimbiamba <vicanall@gmail.com>.
 *
 */
@Service
public class AppUserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserServiceImpl userService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		System.out.println("THE USER NAME:    --->" + username);
		User user = userService.find(username);
		if (user == null) {
			user = new User();
		}
		return user;
	}

}
