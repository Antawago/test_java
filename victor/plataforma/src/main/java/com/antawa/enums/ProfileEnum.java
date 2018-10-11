/**
 * 
 */
package com.antawa.enums;

/**
 * Enum about profiles of systema.
 * 
 * @author Victor Quimbiamba <vicanall@gmail.com>.
 * 
 */
public enum ProfileEnum {
	
	/**
	 * Type.
	 */
	RIDER("RDR"),

	/**
	 * Type.
	 */
	DRIVER("DVR");

	/**
	 * Code.
	 */
	private final String code;

	/**
	 * Constructor.
	 * 
	 * @param code code
	 */
	ProfileEnum(final String code) {
		this.code = code;
	}

	/**
	 * 
	 * @param code
	 * @return
	 */
	public static final ProfileEnum getbyCode(final String code) {
		for (ProfileEnum e : ProfileEnum.values()) {
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
