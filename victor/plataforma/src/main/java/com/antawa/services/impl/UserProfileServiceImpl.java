/**
 * 
 */
package com.antawa.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.antawa.enums.ProfileEnum;
import com.antawa.enums.StatusEnum;
import com.antawa.enums.UserTypesEnum;
import com.antawa.model.Profile;
import com.antawa.model.User;
import com.antawa.model.UserProfile;
import com.antawa.model.repository.ProfileRepository;
import com.antawa.model.repository.UserProfileRepository;
import com.antawa.model.repository.UserRepository;
import com.antawa.services.UserProfileService;

/**
 * @author Victor Quimbiamba <vicanall@gmail.com>.
 *
 */
@Service
public class UserProfileServiceImpl implements UserProfileService {

	@Autowired
	private UserProfileRepository userProfileRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ProfileRepository profileRepository;

	@Override
	public List<UserProfile> findByStatusAndProfileCode(ProfileEnum profile, StatusEnum status) {
		return userProfileRepository.findByStatusAndProfileCode(profile.getCode(), status.getCode());
	}

	@Override
	public UserProfile findById(Long id) {
		return userProfileRepository.findOne(id);
	}

	@Override
	public void updateTypeById(String type, Long id) {
		userProfileRepository.updateTypeById(type, id);
	}

	@Override
	public UserProfile findByUserUuid(String uuid, ProfileEnum profile) {
		return userProfileRepository.findOneByUserUuid(profile.getCode(), uuid);
	}

	@Override
	@Transactional
	public UserProfile createNewUserProfile(String uuid, ProfileEnum profile) {
		uuid=uuid.replace("\"", "");
		System.out.println("uuid::::::::::::::::::::::::::___>" + uuid);
		User user = userRepository.findOneByUuid(uuid);
		Profile profileE = profileRepository.findByCode(profile.getCode());
		UserProfile up = new UserProfile();
		up.setProfile(profileE);
		up.setUser(user);
		up.setType(UserTypesEnum.NONE.getCode());
		userProfileRepository.saveAndFlush(up);
		return up;

	}

	@Override
	public void saveAndFlush(UserProfile up) {
		userProfileRepository.saveAndFlush(up);
	}
}
