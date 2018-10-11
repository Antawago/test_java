/**
 * 
 */
package com.antawa.util;

import java.io.Serializable;

import org.springframework.http.HttpStatus;

/**
 * @author Victor Quimbiamba <vicanall@gmail.com>.
 *
 */
public class ResponseObject<T> implements Serializable {

	private HttpStatus status;

	private T response;

	private String message;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	public ResponseObject() {
		super();
		this.status = HttpStatus.OK;
	}

	/**
	 * @return the status
	 */
	public HttpStatus getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(HttpStatus status) {
		this.status = status;
	}

	/**
	 * @return the response
	 */
	public T getResponse() {
		return response;
	}

	/**
	 * @param response the response to set
	 */
	public void setResponse(T response) {
		this.response = response;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

}
