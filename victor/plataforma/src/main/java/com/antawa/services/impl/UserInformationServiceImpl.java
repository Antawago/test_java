/**
 * 
 */
package com.antawa.services.impl;

import java.util.List;

import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.antawa.enums.ProfileEnum;
import com.antawa.enums.StatusEnum;
import com.antawa.model.User;
import com.antawa.model.UserInformation;
import com.antawa.model.repository.UserInformationRepository;
import com.antawa.model.repository.UserRepository;
import com.antawa.services.UserInformationService;

/**
 * @author Victor Quimbiamba <vicanall@gmail.com>.
 *
 */
@Service
public class UserInformationServiceImpl implements UserInformationService {

	@Autowired
	private UserInformationRepository userInformationRepository;

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserInformation findByUuid(String uuid) {
		try {
			User usr = userRepository.findOneByUuid(uuid);
			UserInformation ui = userInformationRepository.findOneByUserId(usr.getId());
			if (ui == null) {
				ui = new UserInformation();
				ui.setUser(usr);
				userInformationRepository.saveAndFlush(ui);
				System.err.println("---->> NO TIENE, pero está creado");
			}
			return ui;
		} catch (Exception e) {
			throw new ServiceException("Error obtain user and information", e);
		}
	}

	@Override
	public List<UserInformation> findByStatusAndProfile(StatusEnum status, ProfileEnum profile) {
		// TODO Auto-generated method stub
		return null;
	}
}
