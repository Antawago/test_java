package com.antawa.controller;

import java.util.List;

import org.hibernate.service.spi.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.antawa.enums.StatusEnum;
import com.antawa.model.User;
import com.antawa.model.UserProfile;
import com.antawa.services.UserDocumentService;
import com.antawa.services.UserProfileService;
import com.antawa.services.UserService;
import com.antawa.util.ResponseObject;
import com.antawa.vo.DocumentStructureVO;

@RestController
@RequestMapping("users")
public class UserController {

	public static final Logger LOG = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserDocumentService userDocumentService;

	@Autowired	
	private UserProfileService userProfileService;

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ResponseObject<?> findAllUsers() {
		ResponseObject<List<User>> resp = new ResponseObject<>();
		try {
			List<User> users = userService.findAll();
			resp.setResponse(users);
		} catch (Exception e) {
			resp.setResponse(null);
			resp.setStatus(HttpStatus.CONFLICT);
			resp.setMessage("ERROR obt objs");
		}
		return resp;
	}

	/**
	 * 
	 * @param userProfile
	 * @return
	 */
	@RequestMapping(value = "/updateDriveDocumentsAndSendValidation", method = RequestMethod.POST)
	public ResponseObject<?> updateDriveDocumentAndSendValidate(@RequestBody DocumentStructureVO[] documentData) {
		ResponseObject<UserProfile> respuesta = new ResponseObject<>();
		try {
			userDocumentService.updateDriveDocumentAndSendValidate(documentData);
			respuesta.setResponse(null);
			return respuesta;
		} catch (ServiceException e) {
			respuesta.setMessage(e.getMessage());
			respuesta.setStatus(HttpStatus.CONFLICT);
			return respuesta;
		} catch (Exception e) {
			respuesta.setMessage("Error saving document");
			respuesta.setStatus(HttpStatus.CONFLICT);
			e.printStackTrace();
			return respuesta;
		}
	}
	
	@RequestMapping(value = "/updateProfileStatus", method = RequestMethod.POST)
	public ResponseObject<?> updateProfileStatus(@RequestBody UserProfile userProfile) {
		ResponseObject<UserProfile> respuesta = new ResponseObject<>();
		try {
			UserProfile up=userProfileService.findById(userProfile.getId());
			up.setStatus(userProfile.getStatus());	
			userProfileService.saveAndFlush(up);
			respuesta.setResponse(up);
			return respuesta;
		} catch (ServiceException e) {
			respuesta.setMessage(e.getMessage());
			respuesta.setStatus(HttpStatus.CONFLICT);
			return respuesta;
		} catch (Exception e) {
			respuesta.setMessage("Error saving document");
			respuesta.setStatus(HttpStatus.CONFLICT);
			e.printStackTrace();
			return respuesta;
		}
	}

	@RequestMapping(value = "/sendtovalid", method = RequestMethod.POST)
	public ResponseObject<?> updateUserInformation(@RequestParam(value = "id") Long id) {
		ResponseObject<String> respuesta = new ResponseObject<>();
		UserProfile up = userProfileService.findById(id);
		up.setStatus(StatusEnum.VALID.getCode());
		userProfileService.saveAndFlush(up);
		respuesta.setResponse("Correcto");
		System.out.println("updateUserInformation");
		return respuesta;
	}

}
