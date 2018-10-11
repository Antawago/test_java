package com.antawa.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.antawa.model.User;

/**
 * 
 * @author Victor Quimbiamba <vicanall@gmail.com>.
 * 
 *         this the user Repository interface
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	/**
	 * This method find an user by username.
	 * 
	 * @param username String
	 * @return {@link User}
	 */
	User findOneByUsername(String username);
	
	Long countByCellphone(String cellphone);
	
	Long countByUsername(String username);
	
	/**
	 * 
	 * @param uuid
	 * @return
	 */
	User findOneByUuid(String uuid);
	
}
