package com.accenture.servicios.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.accenture.dao.ContactoDAO;
import com.accenture.dao.PersonaDAO;
import com.accenture.model.Contacto;
import com.accenture.model.Persona;
import com.accenture.servicios.ServiciosPersona;

@Service
public class ServiciosPersonaImpl implements ServiciosPersona{
	private PersonaDAO personaDAO;
	private ContactoDAO contactoDAO;
	
	public void asignarResponsable(Integer idPersona, Integer idResponsable) {
		Persona persona=buscarPersonaPorId(idPersona);
		Persona responsable=buscarPersonaPorId(idResponsable);
		persona.setResponsable(responsable);
		actualizarDatosPersona(persona);
	}
	
	public List<Persona> todasLasPersonas() {
		return personaDAO.buscarTodos();
	}
	
	@Autowired
	public void setPersonaDAO(PersonaDAO personaDAO) {
		this.personaDAO = personaDAO;
	}
	@Autowired
	public void setContactoDAO(ContactoDAO contactoDAO) {
		this.contactoDAO = contactoDAO;
	}
	@Override
	public Persona buscarPersonaPorId(Integer idPersona) {
		return personaDAO.buscarPorClave(idPersona);
	}

	@Override
	public void actualizarDatosPersona(Persona persona) {
		personaDAO.salvar(persona);
		
	}

	@Override
	public void crearNuevaPersona(Persona persona) {
		personaDAO.insertar(persona);
	}

	@Override
	public void crearNuevoContacto(String descripcion, Boolean disponible,
			Integer idPersonaContacto) {
		Contacto contacto=new Contacto(descripcion, disponible);
		contacto.setPersona(personaDAO.buscarPorClave(idPersonaContacto));
		contactoDAO.insertar(contacto);
	}

	@Override
	public List<Contacto> todosLosContactos() {
		return contactoDAO.buscarTodos();
	}

	@Override
	public List<Persona> todosLosResponsables() {
		return null;
	}

	@Override
	/**
	 * el idFilter es el ide de una persona
	 */
	public List<Persona> busquedaPorResponsale(Integer idFilter) {
		return personaDAO.buscarSubordinados(idFilter);
	}

	@Override
	public void eliminarPersona(Integer idPerRemove) {
		personaDAO.borrar(buscarPersonaPorId(idPerRemove));
	}


}
