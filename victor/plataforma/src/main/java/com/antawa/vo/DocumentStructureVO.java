/**
 * 
 */
package com.antawa.vo;

import java.io.Serializable;

/**
 * @author Victor Quimbiamba <vicanall@gmail.com>.
 *
 */
public class DocumentStructureVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * User pk.
	 */
	private Long upk;

	/**
	 * Id Documento.
	 */
	private Long idDoc;

	/**
	 * Status.
	 */
	private String status;

	/**
	 * User profile of document
	 */
	private String userProfile;

	/**
	 * UUID of user.
	 */
	private String uuid;

	/**
	 * Document type no format.
	 */
	private String type;

	/**
	 * Contenido del archivo.
	 */
	private String content;

	/**
	 * Format file.
	 */
	private String format;

	/**
	 * File name.
	 */
	private String name;

	/**
	 * File comment.
	 */
	private String comment;

	public DocumentStructureVO() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the uuid
	 */
	public String getUuid() {
		return uuid;
	}

	/**
	 * @param uuid the uuid to set
	 */
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the content
	 */
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
	 * @return the format
	 */
	public String getFormat() {
		return format;
	}

	/**
	 * @param format the format to set
	 */
	public void setFormat(String format) {
		this.format = format;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the userProfile
	 */
	public String getUserProfile() {
		return userProfile;
	}

	/**
	 * @param userProfile the userProfile to set
	 */
	public void setUserProfile(String userProfile) {
		this.userProfile = userProfile;
	}

	/**
	 * @return the upk
	 */
	public Long getUpk() {
		return upk;
	}

	/**
	 * @param upk the upk to set
	 */
	public void setUpk(Long upk) {
		this.upk = upk;
	}

	/**
	 * @return the idDoc
	 */
	public Long getIdDoc() {
		return idDoc;
	}

	/**
	 * @param idDoc the idDoc to set
	 */
	public void setIdDoc(Long idDoc) {
		this.idDoc = idDoc;
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
