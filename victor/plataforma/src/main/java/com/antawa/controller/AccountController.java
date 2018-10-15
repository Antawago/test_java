package com.antawa.controller;

import java.security.Principal;

import javax.servlet.http.HttpSession;

import org.hibernate.service.spi.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.antawa.enums.ProfileEnum;
import com.antawa.model.User;
import com.antawa.model.UserInformation;
import com.antawa.model.UserProfile;
import com.antawa.model.repository.UserInformationRepository;
import com.antawa.services.UserInformationService;
import com.antawa.services.UserProfileService;
import com.antawa.services.UserService;
import com.antawa.util.ResponseObject;

/**
 * 
 * @author Victor Quimbiamba <vicanall@gmail.com>
 *
 */
@RestController
@RequestMapping("account")
public class AccountController {

	public static final Logger logger = LoggerFactory.getLogger(AccountController.class);

	/**
	 * Service of User.
	 */
	@Autowired
	private UserService userService;

	/**
	 * Service of UserInformation.
	 */
	@Autowired
	private UserInformationService userInformationService;

	/**
	 * Service of UserProfile.
	 */
	@Autowired
	private UserProfileService userProfileService;

	/**
	 * Service of UserInformation .
	 */
	@Autowired
	private UserInformationRepository userInformationRepository;

	/**
	 * request method to create a new account by a guest.
	 * 
	 * @param newUser
	 * @param type
	 * @return
	 */
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ResponseObject<?> createUser(@RequestBody User newUser,
			@RequestParam(value = "type", defaultValue = "DVR") String type) {
		ResponseObject<UserProfile> resp = new ResponseObject<>();

		logger.error(newUser.getUsername());
		if (userService.countByUsername(newUser.getUsername()) > 0l) {
			logger.error("username Already exist " + newUser.getUsername());
			resp.setResponse(null);
			resp.setStatus(HttpStatus.CONFLICT);
			resp.setMessage("REGISTERED_USER");
		} else if (userService.countByCellphone(newUser.getCellphone()) > 0l) {
			logger.error("cell phon Already exist " + newUser.getUsername());
			resp.setResponse(null);
			resp.setStatus(HttpStatus.CONFLICT);
			resp.setMessage("REGISTERED_PHONE");
		} else {
			try {
				newUser.setRole(User.Role.USER.name());
				UserProfile ur = userService.saveNewUser(newUser, ProfileEnum.getbyCode(type));
				resp.setResponse(ur);
				resp.setStatus(HttpStatus.OK);

			} catch (ServiceException e) {
				resp.setMessage(e.getMessage());
				resp.setStatus(HttpStatus.CONFLICT);
			}
		}

		return resp;
	}

	/**
	 * 
	 * @param userInformation
	 * @return
	 */
	@RequestMapping(value = "/updateuserinformation", method = RequestMethod.POST)
	public ResponseObject<?> updateUserInformation(@RequestBody UserInformation userInformation) {
		ResponseObject<String> respuesta = new ResponseObject<>();
		System.out.println("Objeto:: ");
		System.out.println(userInformation.getId());
		System.out.println("Objeto");
		UserInformation ett = userInformationRepository.findOne(userInformation.getId());
		System.out.println(ett.getRequiredCar());

		ett.setAvatar(userInformation.getAvatar());
		ett.setPdfFile(userInformation.getPdfFile());
		ett.setRequiredCar(userInformation.getRequiredCar());
		userInformationRepository.saveAndFlush(ett);
//		userInformationRepository.updateInformatio(userInformation.getRequiredCar(), userInformation.getAvatar(),
//				userInformation.getPdfFile(), userInformation.getId());
//	
		respuesta.setResponse("Correcto");
		return respuesta;
	}

	// this is the login api/service

	/**
	 * 
	 * @param principal
	 * @return
	 */
	@RequestMapping("/login/{type}")
	public ResponseObject<UserProfile> login(@PathVariable("type") String type, Principal principal) {
		ResponseObject<UserProfile> response = new ResponseObject<>();
		System.out.println("login: " + type);
		if (principal instanceof UsernamePasswordAuthenticationToken) {
			UsernamePasswordAuthenticationToken usr = (UsernamePasswordAuthenticationToken) principal;
			User user = ((User) usr.getPrincipal());
			ProfileEnum profile = ProfileEnum.getbyCode(type);
			try {
				System.out.println("Buscando ");
				UserProfile up = userProfileService.findByUserUuid(user.getUuid(), profile);
				if (up == null) {
					up = userProfileService.createNewUserProfile(user.getUuid(), profile);
					response.setMessage("WELCOME_NEW_PROFILE");
					System.out.println("WELCOME_NEW_PROFILE: CREADO ");
				}

				response.setResponse(up);
			} catch (Exception e) {
				response.setStatus(HttpStatus.CONFLICT);
				response.setMessage("Perfil no se pudo crear");
			}
			String username = ((User) usr.getPrincipal()).getUsername();
			System.out.println("SI: " + username);
		} else {
			String username = principal.toString();
			System.out.println("NO" + username);
		}

		return response;
	}

	/**
	 * 
	 * @param uuid
	 * @return
	 */
	@RequestMapping(value = "/userinformation", method = RequestMethod.GET)
	public ResponseObject<?> getAllDriversByZobe(@RequestParam(value = "uuid") String uuid) {
		ResponseObject<UserInformation> respuesta = new ResponseObject<>();
		try {
			UserInformation usr = userInformationService.findByUuid(uuid);
			respuesta.setResponse(usr);
			return respuesta;
		} catch (Exception e) {
			respuesta.setMessage("Error obtain information");
			respuesta.setStatus(HttpStatus.CONFLICT);
			return respuesta;
		}
	}

	/**
	 * 
	 * @param userProfile
	 * @return
	 */
	@RequestMapping(value = "/updatetypedriver", method = RequestMethod.POST)
	public ResponseObject<?> updateDriverType(@RequestBody UserProfile userProfile) {
		ResponseObject<UserProfile> respuesta = new ResponseObject<>();
		try {
			System.out.println(userProfile.getType());
			System.out.println(userProfile.getId());
			userProfileService.updateTypeById(userProfile.getType(), userProfile.getId());
			respuesta.setResponse(userProfile);
			return respuesta;
		} catch (Exception e) {
			respuesta.setMessage("Error update information");
			respuesta.setStatus(HttpStatus.CONFLICT);
			e.printStackTrace();
			return respuesta;
		}
	}

	@RequestMapping(value = "/getUserProfile", method = RequestMethod.GET)
	public ResponseObject<?> getUserProfile(@RequestParam(value = "type") String type,
			@RequestParam(value = "uuid") String uuid) {
		ResponseObject<UserProfile> respuesta = new ResponseObject<>();
		try {
			uuid = uuid.replace("\"", "");
			System.out.println("getUserProfile: " + uuid);

			User user = userService.findByUUID(uuid);
			if (user == null) {
				respuesta.setMessage("No user register");
				respuesta.setStatus(HttpStatus.NO_CONTENT);

				return respuesta;
			}
			ProfileEnum profile = ProfileEnum.getbyCode(type);
			UserProfile up = userProfileService.findByUserUuid(uuid, profile);
			if (up == null) {
				up = userProfileService.createNewUserProfile(uuid, profile);
				respuesta.setMessage("WELCOME_NEW_PROFILE");
			}

			respuesta.setResponse(up);
			return respuesta;
		} catch (Exception e) {
			respuesta.setMessage("Error update information");
			respuesta.setStatus(HttpStatus.CONFLICT);
			e.printStackTrace();
			return respuesta;
		}
	}

	@RequestMapping(value = "/getUser", method = RequestMethod.GET)
	public ResponseObject<?> getUser(@RequestParam(value = "uuid") String uuid) {
		ResponseObject<User> respuesta = new ResponseObject<>();
		try {
			uuid = uuid.replace("\"", "");
			System.out.println("getUser: " + uuid);

			User user = userService.findByUUID(uuid);
			if (user == null) {
				respuesta.setMessage("No user register");
				respuesta.setStatus(HttpStatus.NO_CONTENT);

				return respuesta;
			}

			respuesta.setResponse(user);
			return respuesta;
		} catch (Exception e) {
			respuesta.setMessage("Error get user information");
			respuesta.setStatus(HttpStatus.CONFLICT);
			e.printStackTrace();
			return respuesta;
		}
	}

	/**
	 * 
	 * @param session
	 */
	// @CrossOrigin
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void logout(HttpSession session) {

		if (session != null) {
			logger.info("Cerrando session " + session);
			System.out.println("Cerrando session............................................");
			session.invalidate();
		}
	}

}
