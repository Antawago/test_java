/**
 * 
 */
package com.antawa.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import com.antawa.enums.EvaluationStatusEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author Victor Quimbiamba <vicanall@gmail.com>.
 *
 */
@Entity
@Table(name = "user_evaluation")
public class UserEvaluation implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Description of the property id.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@NotNull
	@JoinColumn(name = "id_user")
	private User user;

	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	@NotNull
	@JoinColumn(name = "id_evaluation_parameter")
	private EvaluationParameter evaluationParameter;



	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedDate;

	@Column(name = "user_update")
	private String userUpdate;
	
	private String status;

	public UserEvaluation() {
		super();
		this.status=EvaluationStatusEnum.SIN_VALORACION.getCode();
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the user
	 */
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	public User getUser() {
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * @return the evaluationParameter
	 */
	public EvaluationParameter getEvaluationParameter() {
		return evaluationParameter;
	}

	/**
	 * @param evaluationParameter the evaluationParameter to set
	 */
	public void setEvaluationParameter(EvaluationParameter evaluationParameter) {
		this.evaluationParameter = evaluationParameter;
	}

	/**
	 * @return the updatedDate
	 */
	public Date getUpdatedDate() {
		return updatedDate;
	}

	/**
	 * @param updatedDate the updatedDate to set
	 */
	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	/**
	 * @return the userUpdate
	 */
	public String getUserUpdate() {
		return userUpdate;
	}

	/**
	 * @param userUpdate the userUpdate to set
	 */
	public void setUserUpdate(String userUpdate) {
		this.userUpdate = userUpdate;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

}
