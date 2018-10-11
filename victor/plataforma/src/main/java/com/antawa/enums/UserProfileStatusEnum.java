package com.antawa.enums;

public enum UserProfileStatusEnum {
	/**
	 * Estado.
	 */
	EN_VALIDACION("VLD"),

	/**
	 * Estado.
	 */
	ACTIVO("ACT"),

	/**
	 * Estado.
	 */
	DEVUELTO("DEV"),

	/**
	 * Estado.
	 */
	RECHAZADO("RCH"),

	/**
	 * Estado.
	 */
	REGISTRADO("RGT");

	/**
	 * Code.
	 */
	private final String code;

	/**
	 * Constructor.
	 * 
	 * @param code code
	 */

	UserProfileStatusEnum(final String code) {
		this.code = code;
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}
}
