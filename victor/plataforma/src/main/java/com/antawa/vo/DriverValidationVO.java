/**
 * 
 */
package com.antawa.vo;

import java.io.Serializable;
import java.util.List;

import com.antawa.model.UserDocument;
import com.antawa.model.UserEvaluation;

/**
 * @author Victor Quimbiamba <vicanall@gmail.com>.
 *
 */
public class DriverValidationVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Documents.
	 */
	private List<UserDocument> documents;

	/**
	 * Evaluarions parameters.
	 */
	private List<UserEvaluation> userEvaluations;

	/**
	 * Constructor.
	 */
	public DriverValidationVO() {
	}

	/**
	 * @return the documents
	 */
	public List<UserDocument> getDocuments() {
		return documents;
	}

	/**
	 * @param documents the documents to set
	 */
	public void setDocuments(List<UserDocument> documents) {
		this.documents = documents;
	}

	/**
	 * @return the userEvaluations
	 */
	public List<UserEvaluation> getUserEvaluations() {
		return userEvaluations;
	}

	/**
	 * @param userEvaluations the userEvaluations to set
	 */
	public void setUserEvaluations(List<UserEvaluation> userEvaluations) {
		this.userEvaluations = userEvaluations;
	}

}
