/**
 * 
 */
package com.antawa.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.antawa.enums.ProfileEnum;
import com.antawa.model.Profile;

/**
 * @author Victor Quimbiamba <vicanall@gmail.com>.
 *
 */
@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long> {
	
	/**
	 * Find a Profile by code.
	 * 
	 * @param code {@link ProfileEnum}
	 * @return {@link Profile}
	 */
	Profile findByCode(String code);
}
