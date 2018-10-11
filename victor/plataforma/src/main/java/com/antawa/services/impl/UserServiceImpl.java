package com.antawa.services.impl;

import java.util.List;
import java.util.UUID;

import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.antawa.enums.ProfileEnum;
import com.antawa.enums.StatusEnum;
import com.antawa.model.Profile;
import com.antawa.model.User;
import com.antawa.model.UserProfile;
import com.antawa.model.repository.ProfileRepository;
import com.antawa.model.repository.UserProfileRepository;
import com.antawa.model.repository.UserRepository;
import com.antawa.services.MailSender;
import com.antawa.services.UserService;

/**
 * @author Victor Quimbiamba <vicanall@gmail.com>.
 *
 */
@Service
public class UserServiceImpl implements UserService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Repository of User.
	 */
	@Autowired
	private UserRepository userRepository;

	/**
	 * Repository of Profile.
	 */
	@Autowired
	private ProfileRepository profileRepository;

	/**
	 * Repository of UserProfile.
	 */
	@Autowired
	private UserProfileRepository userProfileRepository;

	@Autowired
	@Qualifier("antawaGoMailSender")
	public MailSender mailSender;

	/**
	 * This method save a new User.
	 * 
	 * @param user    {@link User}
	 * @param profile {@link Profile}
	 * @return new {@link User}
	 */
	@Transactional
	@Override
	public UserProfile saveNewUser(final User user, final ProfileEnum profile) throws ServiceException {
		try {
			Profile p = profileRepository.findByCode(profile.getCode());
			user.setUuid(UUID.randomUUID().toString());
			user.setStatus(StatusEnum.ACTIVE.getCode());
			user.setProfileStatus(StatusEnum.REGISTERED.getCode());

			User u = userRepository.save(user);
			UserProfile up = new UserProfile();
			up.setProfile(p);
			up.setUser(u);
			up.setStatus(StatusEnum.REGISTERED.getCode());
			userProfileRepository.save(up);

			mailSender.sendMail("antawa@info.com", u.getUsername(), "Registro AntawaGo",
					"Welcome to the jungle " + u.getNames() + " " + u.getLastNames() + ", we've got fun and games...");
			return up;
		} catch (Exception e) {
			throw new ServiceException("Error trying save user", e);
		}
	}

	@Override
	public User save(User user) {
		return userRepository.saveAndFlush(user);
	}

	@Override
	public User update(User user) {
		return userRepository.save(user);
	}

	@Override
	public User find(String userName) {
		return userRepository.findOneByUsername(userName);
	}

	@Override
	public Long countByCellphone(String cellphone) {
		return userRepository.countByCellphone(cellphone);
	}

	@Override
	public Long countByUsername(String username) {
		return userRepository.countByUsername(username);
	}

	@Override
	public User find(Long id) {
		return userRepository.findOne(id);
	}

	@Override
	public User findByUUID(String uuid) {
		uuid = uuid.replace("\"", "");
		return userRepository.findOneByUuid(uuid);
	}

	@Override
	public List<User> findAll() {
		return userRepository.findAll();
	}
}
