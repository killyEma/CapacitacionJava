package com.accenture.accion;

import java.io.Serializable;



import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;
import org.springframework.beans.factory.annotation.Autowired;

import com.accenture.model.Contacto;
import com.accenture.model.Persona;
import com.accenture.servicios.ServiciosPersona;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class InicioAccion extends ActionSupport implements Serializable,SessionAware,ModelDriven<Persona>{

	private static final long serialVersionUID = 1L;
	
	private List<Persona> personas;
	private List<Persona> perSinResponsables;
	private List<Persona> responsables;
	private List<Persona> personasFiltradas;
	private List<Contacto> contactos;
	private ServiciosPersona serviciosPersona;
	private Map<String, Object> session;
	private Persona persona= new Persona();


	public InicioAccion() {
		System.out.println("en el inicio");
		perSinResponsables= new ArrayList<>();
		responsables= new ArrayList<>();
		
	}
	
	
	@Override
	public String execute() throws Exception {
		personas=serviciosPersona.todasLasPersonas();
		for (Persona p : personas) {
			if (p.getResponsable()==null) {
				perSinResponsables.add(p);
			}else{
				responsables.add(p);
			}
		}
		Integer idFilter=(Integer) session.get("idFilter");
		if (idFilter!=null) { 													// existe fitro, entonces busco por el
			System.out.println("se filtro");
			personasFiltradas=serviciosPersona.busquedaPorResponsale(idFilter);
		}else{																	//no existe filtro, entonces muestro todas las personas
			System.out.println("no se filtro");
			personasFiltradas=personas;
		}
		Integer idPerEdit=(Integer) session.get("idPerEdit");
		if (idPerEdit!=null) {
			persona=serviciosPersona.buscarPersonaPorId(idPerEdit);
		}
		
		contactos=serviciosPersona.todosLosContactos();
		session.put("personas", personas);
		session.put("perSinResponsables", perSinResponsables);
		session.put("contactos", contactos);
		session.put("responsables", responsables);
		session.put("personasFiltradas", personasFiltradas);
		session.put("persona", persona);
		return SUCCESS;
	}
	
	
	
//	sets and gets
	@Autowired
	public void setServiciosPersona(ServiciosPersona serviciosPersona) {
		this.serviciosPersona = serviciosPersona;
	}


	public List<Persona> getPersonas() {
		return personas;
	}



	public void setPersonas(List<Persona> personas) {
		this.personas = personas;
	}



	@Override
	public void setSession(Map<String, Object> session) {
		this.session=session;
	}

	public String getopcionVasia(){
		return "";
	}



	public List<Contacto> getContactos() {
		return contactos;
	}



	public void setContactos(List<Contacto> contactos) {
		this.contactos = contactos;
	}

	public List<Persona> getPersonasFiltradas() {
		return personasFiltradas;
	}


	public void setPersonasFiltradas(List<Persona> personasFiltradas) {
		this.personasFiltradas = personasFiltradas;
	}


	@Override
	public Persona getModel() {
		return persona;
	}

	
	public Persona getPersona() {
		return persona;
	}


	public void setPersona(Persona persona) {
		this.persona = persona;
	}


	
}
