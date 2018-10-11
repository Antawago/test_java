/**
 * 
 */
package com.antawa.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

/**
 * @author Victor Quimbiamba <vicanall@gmail.com>.
 *
 */
@Entity
@Table(name = "user_information")
public class UserInformation implements Serializable {

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

	@ManyToOne(optional = false)
	@NotNull
	@JoinColumn(name = "id_user")
	private User user;

	@Temporal(TemporalType.DATE)
	private Date birthdate;

	private Boolean requiredCar;

	@Lob
	private byte[] avatar;

	@Lob
	private byte[] pdfFile;

	/**
	 * Constructor.
	 */
	public UserInformation() {
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
	 * @return the birthdate
	 */
	public Date getBirthdate() {
		return birthdate;
	}

	/**
	 * @param birthdate the birthdate to set
	 */
	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	/**
	 * @return the requiredCar
	 */
	public Boolean getRequiredCar() {
		return requiredCar;
	}

	/**
	 * @param requiredCar the requiredCar to set
	 */
	public void setRequiredCar(Boolean requiredCar) {
		this.requiredCar = requiredCar;
	}

	/**
	 * @return the avatar
	 */
	public byte[] getAvatar() {
		return avatar;
	}

	/**
	 * @param avatar the avatar to set
	 */
	public void setAvatar(byte[] avatar) {
		this.avatar = avatar;
	}

	/**
	 * @return the pdfFile
	 */
	public byte[] getPdfFile() {
		return pdfFile;
	}

	/**
	 * @param pdfFile the pdfFile to set
	 */
	public void setPdfFile(byte[] pdfFile) {
		this.pdfFile = pdfFile;
	}

}
