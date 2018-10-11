package com.antawa.enums;

public enum TipoDocumentoEnum {
	/**
	 * Type.
	 */
	MATRICULA("MAT", "Matrícula"),
	/**
	 * Type.
	 */
	LICENCIA("LIC", "Licencia");

	/**
	 * Code.
	 */
	private final String code;

	/**
	 * Description.
	 */
	private final String description;

	/**
	 * Constructor.
	 * 
	 * @param code code
	 */
	TipoDocumentoEnum(final String code, final String description) {
		this.code = code;
		this.description = description;
	}

	/**
	 * 
	 * @param code
	 * @return
	 */
	public static final TipoDocumentoEnum getDocumentByCode(String code) {
		TipoDocumentoEnum type = null;
		for (TipoDocumentoEnum tipo : TipoDocumentoEnum.values()) {
			if (tipo.getCode().equals(code)) {
				type = tipo;
				break;
			}
		}
		return type;
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
}
