package com.antawa.services;

import java.util.List;

import org.hibernate.service.spi.ServiceException;
import org.springframework.data.repository.query.Param;

import com.antawa.model.User;
import com.antawa.model.UserDocument;
import com.antawa.vo.DocumentStructureVO;

public interface UserDocumentService {
	/**
	 * 
	 * @param doc
	 * @param usr
	 * @throws ServiceException
	 */
	void saveDocument(DocumentStructureVO doc, User usr) throws ServiceException;

	/**
	 * 
	 * @param docs
	 * @throws ServiceException
	 */
	void updateDriveDocumentAndSendValidate(DocumentStructureVO[] docs) throws ServiceException;

	/**
	 * 
	 * @param documentData
	 * @throws ServiceException
	 */
	void updateStatusById(DocumentStructureVO documentData) throws ServiceException;

	/**
	 * 
	 * @param id
	 * @throws ServiceException
	 */
	void findByUserId(Long id) throws ServiceException;

	/**
	 * 
	 * @param id
	 * @return
	 * @throws ServiceException
	 */
	String findDocumentContent(Long id) throws ServiceException;

	/**
	 * 
	 * @param status
	 * @param id
	 * @return
	 * @throws ServiceException
	 */
	List<UserDocument> findByUserIdAndStatus(String status, Long id) throws ServiceException;

}
