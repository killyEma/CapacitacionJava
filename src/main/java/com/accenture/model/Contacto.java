package com.accenture.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name="contactos")
public class Contacto implements Serializable{

	private static final long serialVersionUID = 1L;
	private Integer idNumero;
	private String descripcion;
	private Boolean disponible;
	private Persona persona;
	
	public Contacto() {
	}
	
	public Contacto(String descripcion, Boolean disponible) {
		super();
		this.descripcion = descripcion;
		this.disponible = disponible;
	}
	
	@Override
	public String toString() {
		return idNumero.toString();
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer getIdNumero() {
		return idNumero;
	}
	void setIdNumero(Integer idNumero) {
		this.idNumero = idNumero;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public Boolean getDisponible() {
		return disponible;
	}
	public void setDisponible(Boolean disponible) {
		this.disponible = disponible;
	}

	@ManyToOne
	@JoinColumn
	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}
	
	
}
