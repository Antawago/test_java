package com.antawa.enums;

public enum ParameterEvaluationEnum {

	/**
	 * Type.
	 */
	PSYCHOLOGICAL_TEST("PSTEST");
	
	/**
	 * Code.
	 */
	private final String code;

	/**
	 * Constructor.
	 * 
	 * @param code code
	 */

	ParameterEvaluationEnum(final String code) {
		this.code = code;
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}
}
