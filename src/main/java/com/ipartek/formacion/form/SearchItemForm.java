package com.ipartek.formacion.form;

public class SearchItemForm {

	private String dni;

	/**
	 * @param dni
	 */
	public SearchItemForm(String dni) {
		super();
		this.dni = dni;
	}

	/**
	 * 
	 */
	public SearchItemForm() {
		super();
		this.dni = "";
	}

	/**
	 * @return the dni
	 */
	public String getDni() {
		return this.dni;
	}

	/**
	 * @param dni
	 *            the dni to set
	 */
	public void setDni(String dni) {
		this.dni = dni;
	}

}
