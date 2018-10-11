package com.antawa.services;

import java.util.List;

import org.hibernate.service.spi.ServiceException;

import com.antawa.enums.ProfileEnum;
import com.antawa.model.User;
import com.antawa.model.UserProfile;

public interface UserService {

	/**
	 * This method save a new User with profile.
	 * 
	 * @param user
	 * @param profile
	 * @return
	 * @throws ServiceException
	 */
	UserProfile saveNewUser(final User user, final ProfileEnum profile) throws ServiceException;

	/**
	 * This method save a new User.
	 * 
	 * @param user
	 * @return
	 */
	User save(User user);

	/**
	 * This method update user.
	 * 
	 * @param user
	 * @return
	 */
	User update(User user);

	/**
	 * this method find an user by username.
	 * 
	 * @param userName
	 * @return
	 */
	User find(String userName);

	Long countByCellphone(String cellphone);

	Long countByUsername(String username);

	/**
	 * this method find an user by id.
	 * 
	 * @param id
	 * @return
	 */
	User find(Long id);
	
	/**
	 * 
	 * @param uuid
	 * @return
	 */
	User findByUUID(String uuid);

	/**
	 * This method find all User object's.
	 * 
	 * @return
	 */
	List<User> findAll();

}
