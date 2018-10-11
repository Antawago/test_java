/**
 * 
 */
package com.antawa.enums;

/**
 * Enum about status of models of systema.
 * 
 * @author Victor Quimbiamba <vicanall@gmail.com>.
 * 
 */
public enum StatusEnum {
	/**
	 * Type.
	 */
	ACTIVE("ACT"),

	/**
	 * Type.
	 */
	REGISTERED("RGT"),

	/**
	 * Type.
	 */
	VALID("VLD"),
	
	

	/**
	 * Type.
	 */
	INACTIVE("INA");

	/**
	 * Code.
	 */
	private final String code;

	/**
	 * Constructor.
	 * 
	 * @param code code
	 */
	StatusEnum(final String code) {
		this.code = code;
	}

	/**
	 * 
	 * @param code
	 * @return
	 */
	public static final StatusEnum getbyCode(final String code) {
		for (StatusEnum e : StatusEnum.values()) {
			if (code.equals(e.getCode())) {
				return e;
			}
		}
		return null;
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

}
