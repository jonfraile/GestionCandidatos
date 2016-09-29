package com.ipartek.formacion.domain;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import com.ipartek.formacion.validator.Nif;

public class Candidato implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotNull
	private long id;

	@NotEmpty
	@Size(min = 2, max = 50)
	private String nombre;

	@Nif
	private String dni;

	private Timestamp fecha_alta;
	private Timestamp fecha_modificacion;
	private Timestamp fecha_eliminacion;

	public Candidato() {
		super();
		this.id = 0;
		this.nombre = "";
		this.dni = "";

	}

	/* GETTERS AND SETTERS */

	/**
	 * @return the id
	 */
	public long getId() {
		return this.id;
	}

	/**
	 * @return the fecha_alta
	 */
	public Timestamp getFecha_alta() {
		return this.fecha_alta;
	}

	/**
	 * @param fecha_alta
	 *            the fecha_alta to set
	 */
	public void setFecha_alta(Timestamp fecha_alta) {
		this.fecha_alta = fecha_alta;
	}

	/**
	 * @return the fecha_modificacion
	 */
	public Timestamp getFecha_modificacion() {
		return this.fecha_modificacion;
	}

	/**
	 * @param fecha_modificacion
	 *            the fecha_modificacion to set
	 */
	public void setFecha_modificacion(Timestamp fecha_modificacion) {
		this.fecha_modificacion = fecha_modificacion;
	}

	/**
	 * @return the fecha_eliminacion
	 */
	public Timestamp getFecha_eliminacion() {
		return this.fecha_eliminacion;
	}

	/**
	 * @param fecha_eliminacion
	 *            the fecha_eliminacion to set
	 */
	public void setFecha_eliminacion(Timestamp fecha_eliminacion) {
		this.fecha_eliminacion = fecha_eliminacion;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return this.nombre;
	}

	/**
	 * @param nombre
	 *            the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
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

	public boolean isNew() {
		return (this.id == 0) ? true : false;
	}

	/* TO STRING */

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Candidato [id=" + this.id + ", nombre=" + this.nombre + ", dni=" + this.dni + ", fecha_alta="
				+ this.fecha_alta + ", fecha_modificacion=" + this.fecha_modificacion + ", fecha_eliminacion="
				+ this.fecha_eliminacion + "]";
	}

}
