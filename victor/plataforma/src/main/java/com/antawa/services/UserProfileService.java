/**
 * 
 */
package com.antawa.services;

import java.util.List;

import com.antawa.enums.ProfileEnum;
import com.antawa.enums.StatusEnum;
import com.antawa.model.UserProfile;

/**
 * @author Victor Quimbiamba <vicanall@gmail.com>.
 *
 */
public interface UserProfileService {

	/**
	 * Find a list
	 * 
	 * @param profile
	 * @param status
	 * @return
	 */
	List<UserProfile> findByStatusAndProfileCode(ProfileEnum profile, StatusEnum status);

	/**
	 * This method find an userprofile.
	 * 
	 * @param id
	 * @return
	 */
	UserProfile findById(Long id);

	/**
	 * This method find an userprofile by user.
	 * 
	 * @param id
	 * @param profile
	 * @return
	 */
	UserProfile findByUserUuid(String uuid, ProfileEnum profile);
	
	/**
	 * 
	 * @param uuid
	 * @param profile
	 * @return
	 */
	UserProfile createNewUserProfile(String uuid, ProfileEnum profile);

	/**
	 * 
	 * @param type
	 * @param id
	 */
	void updateTypeById(String type, Long id);

	void saveAndFlush(UserProfile up);

	
}
