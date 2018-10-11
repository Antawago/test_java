/**
 * 
 */
package com.antawa.enums;

/**
 * @author Victor Quimbiamba <vicanall@gmail.com>.
 *
 */
public enum EvaluationStatusEnum {

	SIN_VALORACION("SNV"),

	APROBADO("APR"),

	SOLICITA_NUEVO_DOCUMENTO("SND"),

	NO_APROBADO("NAP");

	/**
	 * Code.
	 */
	private final String code;

	/**
	 * Constructor.
	 * 
	 * @param code code
	 */

	EvaluationStatusEnum(final String code) {
		this.code = code;
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

}
