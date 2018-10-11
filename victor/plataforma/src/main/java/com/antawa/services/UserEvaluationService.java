package com.antawa.services;

import java.util.List;

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
	 * @param documentStructureVO
	 */
	void updateStatusById(DocumentStructureVO documentStructureVO);
}
