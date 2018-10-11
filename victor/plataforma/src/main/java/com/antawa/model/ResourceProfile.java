package com.antawa.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * 
 * @author Victor Quimbiamba <vicanall@gmail.com>.
 *
 */
@Entity
@Table(name = "resource_profile")
public class ResourceProfile implements Serializable {
	
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
	
	@ManyToOne(optional=false)
	@NotNull
	@JoinColumn(name="id_resource")
	private Resource resource;
	
	@ManyToOne(optional=false)
	@NotNull
	@JoinColumn(name="id_profile")
	private Profile profile;
	
	private String status;

	public ResourceProfile() {
		// TODO Auto-generated constructor stub
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
	 * @return the resource
	 */
	public Resource getResource() {
		return resource;
	}

	/**
	 * @param resource the resource to set
	 */
	public void setResource(Resource resource) {
		this.resource = resource;
	}

	/**
	 * @return the profile
	 */
	public Profile getProfile() {
		return profile;
	}

	/**
	 * @param profile the profile to set
	 */
	public void setProfile(Profile profile) {
		this.profile = profile;
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
