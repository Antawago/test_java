package com.antawa.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.antawa.enums.EvaluationStatusEnum;
import com.antawa.enums.ParameterEvaluationEnum;
import com.antawa.enums.UserProfileStatusEnum;
import com.antawa.model.EvaluationParameter;
import com.antawa.model.User;
import com.antawa.model.UserEvaluation;
import com.antawa.model.repository.EvaluationParameterRepository;
import com.antawa.model.repository.UserEvaluationRepository;
import com.antawa.model.repository.UserProfileRepository;
import com.antawa.services.UserEvaluationService;
import com.antawa.vo.DocumentStructureVO;

@Service
public class UserEvaluationServiceImpl implements UserEvaluationService {

	@Autowired
	private UserEvaluationRepository userEvaluationRepository;

	@Autowired
	private UserProfileRepository userProfileRepository;

	@Autowired
	private EvaluationParameterRepository evaluationParameterRepository;

	@Override
	public List<UserEvaluation> findByUserId(Long idUser) {
		return userEvaluationRepository.findByUserId(idUser);
	}

	@Override
	public void updateStatusById(DocumentStructureVO documentStructureVO) {
		if (documentStructureVO.getStatus().equals(EvaluationStatusEnum.NO_APROBADO.getCode())) {
			userProfileRepository.updateStatusById(UserProfileStatusEnum.RECHAZADO.getCode(),
					documentStructureVO.getUpk());
		}
		userEvaluationRepository.updateStatusById(documentStructureVO.getStatus(), documentStructureVO.getIdDoc());
	}

	@Override
	public UserEvaluation findByUserIdType(Long userId, ParameterEvaluationEnum param) {
		return userEvaluationRepository.findByUserIdAndEvaluationParameterCode(userId, param.getCode());
	}

	@Override
	public UserEvaluation createByUserIdType(User user, ParameterEvaluationEnum param) {
		EvaluationParameter ep = evaluationParameterRepository.findByCode(param.getCode());
		UserEvaluation ue = new UserEvaluation();
		ue.setEvaluationParameter(ep);
		ue.setUser(user);
		userEvaluationRepository.saveAndFlush(ue);
		return ue;
	}
}
