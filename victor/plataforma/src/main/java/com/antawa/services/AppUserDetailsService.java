package com.antawa.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface AppUserDetailsService {
	
	/**
	 * This method load an user by username - email.
	 * 
	 * @param username email
	 * @return {@link UserDetails}
	 * @throws UsernameNotFoundException
	 */
	UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
}
