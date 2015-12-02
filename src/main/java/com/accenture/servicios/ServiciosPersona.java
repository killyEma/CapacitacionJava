package com.accenture.servicios;

import java.util.List;

import com.accenture.model.Contacto;
import com.accenture.model.Persona;

public interface ServiciosPersona {

	void asignarResponsable(Integer idPersona, Integer idResponsable);
	List<Persona> todasLasPersonas();
	List<Persona> busquedaPorResponsale(Integer idFilter);
	Persona buscarPersonaPorId(Integer idPersona);
	void actualizarDatosPersona(Persona persona);
	void crearNuevaPersona(Persona persona);
	void crearNuevoContacto(String descripcion, Boolean disponible,
			Integer idPersonaContacto);
	List<Contacto> todosLosContactos();
	List<Persona> todosLosResponsables();
	void eliminarPersona(Integer idPerRemove);
}
