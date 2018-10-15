/**
 * 
 */
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

import com.antawa.enums.EvaluationStatusEnum;
import com.antawa.enums.ParameterEvaluationEnum;
import com.antawa.enums.ProfileEnum;
import com.antawa.enums.StatusEnum;
import com.antawa.model.User;
import com.antawa.model.UserDocument;
import com.antawa.model.UserEvaluation;
import com.antawa.model.UserProfile;
import com.antawa.model.repository.UserDocumentRepository;
import com.antawa.services.UserDocumentService;
import com.antawa.services.UserEvaluationService;
import com.antawa.services.UserProfileService;
import com.antawa.services.UserService;
import com.antawa.util.ResponseObject;
import com.antawa.vo.DocumentStructureVO;
import com.antawa.vo.DriverValidationVO;

/**
 * @author Victor Quimbiamba <vicanall@gmail.com>.
 *
 */
@RestController
@RequestMapping("validation")
public class ValidationController {

	public static final Logger LOG = LoggerFactory.getLogger(AccountController.class);

	@Autowired
	private UserProfileService userProfileService;

	@Autowired
	private UserEvaluationService evaluationService;

	@Autowired
	private UserDocumentRepository documentRepository;

	@Autowired
	private UserDocumentService userDocumentService;

	@Autowired
	private UserEvaluationService userEvaluationService;

	@Autowired
	private UserService userService;

	/**
	 * Thsasdasaas
	 * 
	 * @return
	 */
	@RequestMapping(value = "/driverstovalidate", method = RequestMethod.GET)
	public ResponseObject<?> getAllDriversByZone() {
		ResponseObject<List<UserProfile>> respuesta = new ResponseObject<>();
		try {
			List<UserProfile> result = userProfileService.findByStatusAndProfileCode(ProfileEnum.DRIVER,
					StatusEnum.VALID);
			respuesta.setResponse(result);
			LOG.error("All is OK in getAllDriversByZone()");
		} catch (Exception e) {
			respuesta.setMessage("Error obtain information");
			respuesta.setStatus(HttpStatus.CONFLICT);
			LOG.error("Error in getAllDriversByZone()", e);
		}

		return respuesta;
	}

	@RequestMapping(value = "/getDocumentDriver", method = RequestMethod.GET)
	public ResponseObject<?> getDocumentsForDriver(@RequestParam(value = "id") Long id) {
		ResponseObject<List<UserDocument>> respuesta = new ResponseObject<>();
		try {
			System.out.println("id");
			System.out.println(id);
			List<UserDocument> result = documentRepository.findByUserIdOrderById(id);
			respuesta.setResponse(result);
			return respuesta;
		} catch (Exception e) {
			e.printStackTrace();
			respuesta.setMessage("Error obtain information");
			respuesta.setStatus(HttpStatus.CONFLICT);
			return respuesta;
		}
	}

	@RequestMapping(value = "/getTestEvaluationParameter", method = RequestMethod.GET)
	public ResponseObject<?> getTestEvaluationParameter(@RequestParam(value = "uuid") String uuid) {
		ResponseObject<UserEvaluation> respuesta = new ResponseObject<>();
		try {
			System.out.println("uuid: " + uuid);
			User user = userService.findByUUID(uuid);
			if (user == null) {
				respuesta.setMessage("User not exist");
				respuesta.setStatus(HttpStatus.CONFLICT);
			} else {
				UserEvaluation ue = userEvaluationService.findByUserIdType(user.getId(),
						ParameterEvaluationEnum.PSYCHOLOGICAL_TEST);
				if (ue == null) {
					System.out.println("No hay");
					ue = userEvaluationService.createByUserIdType(user, ParameterEvaluationEnum.PSYCHOLOGICAL_TEST);
				} else {
					System.out.println("Si hay");
					ue.setUser(user);
				}
				respuesta.setResponse(ue);
			}
			return respuesta;
		} catch (Exception e) {
			e.printStackTrace();
			respuesta.setMessage("Error obtain information");
			respuesta.setStatus(HttpStatus.CONFLICT);
			return respuesta;
		}
	}

	@RequestMapping(value = "/getValidationDriver", method = RequestMethod.GET)
	public ResponseObject<?> getValidationDriver(@RequestParam(value = "id") Long id) {
		ResponseObject<DriverValidationVO> respuesta = new ResponseObject<>();
		try {
			System.out.println("id");
			System.out.println(id);

			DriverValidationVO resp = new DriverValidationVO();

			List<UserDocument> documents = documentRepository.findByUserIdOrderById(id);
			resp.setDocuments(documents);

			List<UserEvaluation> userEvaluations = evaluationService.findByUserId(id);
			resp.setUserEvaluations(userEvaluations);

			respuesta.setResponse(resp);
			return respuesta;
		} catch (Exception e) {
			e.printStackTrace();
			respuesta.setMessage("Error obtain information");
			respuesta.setStatus(HttpStatus.CONFLICT);
			return respuesta;
		}
	}

	@RequestMapping(value = "/updateDocumentStatus", method = RequestMethod.POST)
	public ResponseObject<?> updateDocumentStatus(@RequestBody DocumentStructureVO documentData) {
		ResponseObject<?> respuesta = new ResponseObject<>();
		try {
			System.out.println(documentData.getStatus() + " ... ValidationController.updateDocumentStatus()"
					+ documentData.getIdDoc());
			userDocumentService.updateStatusById(documentData);
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

	@RequestMapping(value = "/updateParameterStatus", method = RequestMethod.POST)
	public ResponseObject<?> updateParameterStatus(@RequestBody DocumentStructureVO documentData) {
		ResponseObject<?> respuesta = new ResponseObject<>();
		try {
			System.out.println(documentData.getStatus() + " ... ValidationController.updateDocumentStatus()"
					+ documentData.getIdDoc());
			userEvaluationService.updateStatusById(documentData);
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

	@RequestMapping(value = "/getDocumentsContent", method = RequestMethod.GET)
	public ResponseObject<?> getDocumentsContent(@RequestParam(value = "id") Long id) {
		ResponseObject<String> respuesta = new ResponseObject<>();
		try {
			System.out.println("getDocumentsContent---> id");
			System.out.println(id);
			String result = documentRepository.findDocumentContent(id);
			// System.out.println("getDocumentsContent---> content: " + result);
			respuesta.setResponse(result);
			return respuesta;
		} catch (Exception e) {
			e.printStackTrace();
			respuesta.setMessage("Error obtain information");
			respuesta.setStatus(HttpStatus.CONFLICT);
			return respuesta;
		}
	}

	@RequestMapping(value = "/getDriverDocumentsError", method = RequestMethod.GET)
	public ResponseObject<?> getDriverDocumentsError(@RequestParam(value = "id") Long id) {
		ResponseObject<List<UserDocument>> respuesta = new ResponseObject<>();
		try {
			System.out.println("getDriverDocumentsError---> id");
			System.out.println(id);
			List<UserDocument> ud = userDocumentService
					.findByUserIdAndStatus(EvaluationStatusEnum.NO_APROBADO.getCode(), id);
			// System.out.println("getDocumentsContent---> content: " + result);
			respuesta.setResponse(ud);
			return respuesta;
		} catch (Exception e) {
			e.printStackTrace();
			respuesta.setMessage("Error obtain information");
			respuesta.setStatus(HttpStatus.CONFLICT);
			return respuesta;
		}
	}
}
