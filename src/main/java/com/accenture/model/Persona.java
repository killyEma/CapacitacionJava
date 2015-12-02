package com.accenture.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="personas")
public class Persona implements Serializable{


	private static final long serialVersionUID = 1L;
	private Integer id;
	private String nombre;
	private String apellido;
	private Date fechaDate;
	private List<Contacto> contactos;
	private Persona responsable;
	private List<Persona> personas;
	
	public Persona() {
		contactos= new ArrayList<Contacto>();
		personas= new ArrayList<Persona>();
	}
	
	public Persona(String nombre, String apellido, Date fechaDate) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.fechaDate = fechaDate;
		contactos= new ArrayList<Contacto>();
		personas= new ArrayList<Persona>();
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	@Temporal(TemporalType.DATE)
	@Column
	public Date getFechaDate() {
		return fechaDate;
	}
	public void setFechaDate(Date fechaDate) {
		this.fechaDate = fechaDate;
	}
	
	
	@OneToMany(mappedBy="persona")
	public List<Contacto> getContactos() {
		return contactos;
	}
	public void setContactos(List<Contacto> contactos) {
		this.contactos = contactos;
	}
	
	@ManyToOne(cascade={CascadeType.ALL})
	@JoinColumn(name="responsable_id")
	public Persona getResponsable() {
		return responsable;
	}
	public void setResponsable(Persona responsable) {
		this.responsable = responsable;
	}
	
	@OneToMany(mappedBy="responsable")
	public List<Persona> getPersonas() {
		return personas;
	}
	public void setPersonas(List<Persona> personas) {
		this.personas = personas;
	}
	
	
	public void add(Contacto contacto){
		contactos.add(contacto);
	}
	public void remove(Contacto contacto){
		contactos.remove(contacto);
	}
	
	
	
	
}
