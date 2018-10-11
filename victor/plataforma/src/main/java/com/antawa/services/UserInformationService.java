/**
 * 
 */
package com.antawa.services;

import java.util.List;

import com.antawa.enums.ProfileEnum;
import com.antawa.enums.StatusEnum;
import com.antawa.model.UserInformation;

/**
 * @author Victor Quimbiamba <vicanall@gmail.com>.
 *
 */
public interface UserInformationService {

	/**
	 * this method find an user by uuid.
	 * 
	 * @param id
	 * @return
	 */
	UserInformation findByUuid(String uuid);

	/**
	 * 
	 * @param status
	 * @param profile
	 * @return
	 */
	List<UserInformation> findByStatusAndProfile(StatusEnum status, ProfileEnum profile);

}
