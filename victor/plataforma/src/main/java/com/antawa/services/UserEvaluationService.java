package com.antawa.services;

import java.util.List;

import com.antawa.enums.ParameterEvaluationEnum;
import com.antawa.model.User;
import com.antawa.model.UserEvaluation;
import com.antawa.vo.DocumentStructureVO;

public interface UserEvaluationService {

	/**
	 * 
	 * @param idUser
	 * @return
	 */
	List<UserEvaluation> findByUserId(Long idUser);
	
	/**
	 * 
	 * @param uuid
	 * @param param
	 * @return
	 */
	UserEvaluation findByUserIdType(Long userId,ParameterEvaluationEnum param);
	
	/**
	 * 
	 * @param user
	 * @param param
	 * @return
	 */
	UserEvaluation createByUserIdType(User user,ParameterEvaluationEnum param);

	/**
	 * 
	 * @param documentStructureVO
	 */
	void updateStatusById(DocumentStructureVO documentStructureVO);
}
