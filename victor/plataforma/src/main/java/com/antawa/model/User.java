package com.antawa.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.springframework.context.annotation.Scope;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

/**
 * Description of User.
 * 
 * Victor Quimbiamba <vicanall@gmail.com>.
 */
@Entity
@Table(name = "users")
@Scope("session")
public class User implements UserDetails {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static enum Role {
		USER, ADMIN
	}

	/**
	 * Description of the property id.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	/**
	 * Description of the property email.
	 */
	@Column(unique = true, nullable = false)
	@NotNull
	private String username;

	/**
	 * Description of the property password.
	 */
	@JsonProperty(access = Access.WRITE_ONLY)
	@Column(length = 100, nullable = false)
	private String password;

	/**
	 * Description of the property role , to grant authority to the user .
	 */
	private String role;

	/**
	 * Description of the property full names.
	 */
	@Column(length = 100, nullable = false)
	private String names;

	/**
	 * Description of the property last names.
	 */
	@Column(length = 100, nullable = false)
	private String lastNames;

	/**
	 * Description of the property cellphone.
	 */
	@Column(length = 20, nullable = false)
	private String cellphone;

	/**
	 * Description of the property phone.
	 */
	@Column(length = 20, nullable = true)
	private String phone;

	/**
	 * Description of the property address.
	 */
	@Column(length = 500)
	private String address;

	/**
	 * Description of the property uuid.
	 */
	@Column(length = 36)
	@NotNull
	private String uuid;

	/**
	 * Description of the property identificationNumber.
	 */
	private String identificationNumber;

	@Column(length = 3, nullable = false)
	private String status;

	@Transient
	@JsonProperty
	private String profileStatus;	
	

	@Temporal(TemporalType.TIMESTAMP)
	private Date registerDate;

	/**
	 * Constructor.
	 * 
	 * @param username
	 * @param password
	 */
	public User(final String username, final String password) {
		super();
		this.username = username;
		this.password = password;

	}

	public User() {
		super();
		registerDate = new Date();
	}

	@JsonIgnore
	@Override
	public boolean isEnabled() {
		return true;
	}

	@JsonIgnore
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@JsonIgnore
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@JsonIgnore
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@JsonIgnore
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<GrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority(role));
		return authorities;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", role=" + role + ",]";
	}

	@Override
	@JsonIgnore
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
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
	 * @return the role
	 */
	public String getRole() {
		return role;
	}

	/**
	 * @param role the role to set
	 */
	public void setRole(String role) {
		this.role = role;
	}

	/**
	 * @return the names
	 */
	public String getNames() {
		return names;
	}

	/**
	 * @param names the names to set
	 */
	public void setNames(String names) {
		this.names = names;
	}

	/**
	 * @return the lastNames
	 */
	public String getLastNames() {
		return lastNames;
	}

	/**
	 * @param lastNames the lastNames to set
	 */
	public void setLastNames(String lastNames) {
		this.lastNames = lastNames;
	}

	/**
	 * @return the cellphone
	 */
	public String getCellphone() {
		return cellphone;
	}

	/**
	 * @param cellphone the cellphone to set
	 */
	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}

	/**
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * @param phone the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
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
	 * @return the identificationNumber
	 */
	public String getIdentificationNumber() {
		return identificationNumber;
	}

	/**
	 * @param identificationNumber the identificationNumber to set
	 */
	public void setIdentificationNumber(String identificationNumber) {
		this.identificationNumber = identificationNumber;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
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
	 * @return the profileStatus
	 */
	public String getProfileStatus() {
		return profileStatus;
	}

	/**
	 * @param profileStatus the profileStatus to set
	 */
	public void setProfileStatus(String profileStatus) {
		this.profileStatus = profileStatus;
	}

	/**
	 * @return the registerDate
	 */
	public Date getRegisterDate() {
		return registerDate;
	}

	/**
	 * @param registerDate the registerDate to set
	 */
	public void setRegisterDate(Date registerDate) {
		this.registerDate = registerDate;
	}


}
