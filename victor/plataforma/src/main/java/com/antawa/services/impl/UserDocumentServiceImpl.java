package com.antawa.services.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.antawa.enums.EvaluationStatusEnum;
import com.antawa.enums.StatusEnum;
import com.antawa.enums.TipoDocumentoEnum;
import com.antawa.enums.UserProfileStatusEnum;
import com.antawa.model.EvaluationParameter;
import com.antawa.model.User;
import com.antawa.model.UserDocument;
import com.antawa.model.UserEvaluation;
import com.antawa.model.UserProfile;
import com.antawa.model.repository.EvaluationParameterRepository;
import com.antawa.model.repository.UserDocumentRepository;
import com.antawa.model.repository.UserEvaluationRepository;
import com.antawa.model.repository.UserProfileRepository;
import com.antawa.model.repository.UserRepository;
import com.antawa.services.MailSender;
import com.antawa.services.UserDocumentService;
import com.antawa.vo.DocumentStructureVO;

@Service
public class UserDocumentServiceImpl implements UserDocumentService {

	@Autowired
	private UserDocumentRepository userDocumentRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserProfileRepository userProfileRepository;

	@Autowired
	private EvaluationParameterRepository evaluationParameterRepository;

	@Autowired
	private UserEvaluationRepository userEvaluationRepository;

	@Autowired
	@Qualifier("antawaGoMailSender")
	public MailSender mailSender;

	@Override
	@Transactional
	public void saveDocument(DocumentStructureVO doc, User usr) throws ServiceException {
		Long id = userDocumentRepository.findIdByTypeAndUuid(doc.getType(), doc.getUuid());
		int index = doc.getContent().indexOf("base64");
		String emptyContent = doc.getContent();
		if (index > 0) {
			emptyContent = (doc.getContent().substring(index + 7));
		}

		if (id != null) {
			userDocumentRepository.updateById(EvaluationStatusEnum.SIN_VALORACION.getCode(), emptyContent,
					doc.getFormat(), id);
		} else {
			UserDocument docum = new UserDocument();
			docum.setUser(usr);
			docum.setFormatFile(doc.getFormat());
			docum.setDocType(doc.getType());
			docum.setContent(emptyContent);
			userDocumentRepository.saveAndFlush(docum);
			return;
		}
	}

	@Override
	@Transactional
	public void updateDriveDocumentAndSendValidate(DocumentStructureVO[] docs) throws ServiceException {
		if (docs.length == 0) {
			throw new ServiceException("No existe documentos");
		}
		User usr = userRepository.findOne(docs[0].getUpk());
		for (DocumentStructureVO doc : docs) {
			this.saveDocument(doc, usr);
		}
		UserProfile up = userProfileRepository.findOneByUserUuid(docs[0].getUserProfile(), usr.getUuid());
		up.setStatus(StatusEnum.VALID.getCode());
		userProfileRepository.saveAndFlush(up);

		List<EvaluationParameter> parametros = evaluationParameterRepository.findByStatus(StatusEnum.ACTIVE.getCode());
		List<Long> userEval = userEvaluationRepository.findEvaluationParametersByUserId(usr.getId());

		for (EvaluationParameter evaluationParameter : parametros) {
			if (!userEval.contains(evaluationParameter.getId())) {
				UserEvaluation ue = new UserEvaluation();
				ue.setUser(usr);
				ue.setEvaluationParameter(evaluationParameter);
				// ue.setValid(Boolean.FALSE);
				userEvaluationRepository.save(ue);
			}
		}
	}

	@Override
	public void updateStatusById(DocumentStructureVO documentData) throws ServiceException {

		if (documentData.getStatus().equals(EvaluationStatusEnum.NO_APROBADO.getCode())) {

			userProfileRepository.updateStatusById(UserProfileStatusEnum.DEVUELTO.getCode(), documentData.getUpk());

			User user = userRepository.findOneByUuid(documentData.getUuid());

//			userDocumentRepository.updateStatusById(documentData.getComment(), documentData.getStatus(),
//					documentData.getIdDoc());

			UserDocument ud = userDocumentRepository.findOne(documentData.getIdDoc());
			ud.setComment(documentData.getComment());
			ud.setStatus(documentData.getStatus());
			userDocumentRepository.saveAndFlush(ud);

			TipoDocumentoEnum td = TipoDocumentoEnum.getDocumentByCode(ud.getDocType());

			mailSender.sendMail("antawa@info.com", user.getUsername(), "AntawaGo - Documento no válido",
					"Estimado " + user.getNames() + " " + user.getLastNames() + ",  El documento '"
							+ td.getDescription() + "', " + documentData.getComment());

		} else {
			userDocumentRepository.updateStatusById(documentData.getStatus(), documentData.getIdDoc());
		}
	}

	@Override
	public void findByUserId(Long id) throws ServiceException {
		userDocumentRepository.findByUserIdOrderById(id);
	}

	@Override
	public String findDocumentContent(Long id) throws ServiceException {
		return userDocumentRepository.findDocumentContent(id);
	}

	@Override
	public List<UserDocument> findByUserIdAndStatus(String status, Long id) throws ServiceException {
		return userDocumentRepository.findByUserIdAndStatus(status, id);
	}
}
