package com.antawa.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Type;

import com.antawa.enums.EvaluationStatusEnum;
import com.antawa.enums.StatusEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 
 * @author Victor Quimbiamba <vicanall@gmail.com>.
 *
 */
@Entity
@Table(name = "user_document")
public class UserDocument implements Serializable {

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

	@Column(length = 5, name = "doc_type")
	private String docType;

	@Column(length = 50, name = "format_file")
	private String formatFile;

	// @Lob
	@Type(type = "text")
	private String content;
	
	@Column(length = 3)
	private String status;
	
	private String comment;

	public UserDocument() {
		super();
		this.status = EvaluationStatusEnum.SIN_VALORACION.getCode();
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
	@JsonIgnore
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
	 * @return the docType
	 */
	public String getDocType() {
		return docType;
	}

	/**
	 * @param docType the docType to set
	 */
	public void setDocType(String docType) {
		this.docType = docType;
	}

	/**
	 * @return the formatFile
	 */
	public String getFormatFile() {
		return formatFile;
	}

	/**
	 * @param formatFile the formatFile to set
	 */
	public void setFormatFile(String formatFile) {
		this.formatFile = formatFile;
	}

	/**
	 * @return the content
	 */
	@JsonIgnore
	public String getContent() {
		return content;
	}

	/**
	 * @param content the content to set
	 */
	public void setContent(String content) {
		this.content = content;
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

	/**
	 * @return the comment
	 */
	public String getComment() {
		return comment;
	}

	/**
	 * @param comment the comment to set
	 */
	public void setComment(String comment) {
		this.comment = comment;
	}

}
