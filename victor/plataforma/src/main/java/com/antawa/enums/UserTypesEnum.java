/**
 * 
 */
package com.antawa.enums;

/**
 * @author Victor Quimbiamba <vicanall@gmail.com>.
 *
 */
public enum UserTypesEnum {
	
	/**
	 * Type.
	 */
	NONE("ND"),

	/**
	 * Type.
	 */
	ONLY_DRIVER("OD"),

	/**
	 * Type.
	 */
	DRIVER_OWNER("DO"),

	/**
	 * Type.
	 */
	OWNER("OW"),

	/**
	 * Type.
	 */
	USER("US");

	/**
	 * Code.
	 */
	private final String code;

	UserTypesEnum(final String code) {
		this.code = code;
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}
}
