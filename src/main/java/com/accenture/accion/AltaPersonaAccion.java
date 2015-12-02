package com.accenture.accion;


import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;
import org.springframework.beans.factory.annotation.Autowired;

import com.accenture.model.Persona;
import com.accenture.servicios.ServiciosPersona;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;


public class AltaPersonaAccion extends ActionSupport implements ModelDriven<Persona>,SessionAware{

	private static final long serialVersionUID = 1L;
	private Persona persona=new Persona();
	private ServiciosPersona serviciosPersona;
	private Integer idResponsable;
	private Integer idPersona;
	private Integer idFilter;
	private Integer idPerEdit=null;
	private Integer idPerRemove=null;

	// estos son campos de un contactso
	private String descripcion;
	private Boolean disponible=false;
	private Integer idPersonaContacto;
	private Map<String, Object> session;
	
	public AltaPersonaAccion() {}
	
	@Override
	public String execute() throws Exception {
		if (persona.getNombre()!=null &&persona.getApellido()!=null) {// se esta creando una nueva persona
			serviciosPersona.actualizarDatosPersona(persona);
			session.remove("idPerEdit");
		}else if(descripcion!=null && !idPersonaContacto.equals(-1)){// se creara un nuevo contacto para una persona
			serviciosPersona.crearNuevoContacto(descripcion,disponible,idPersonaContacto);
		}else if(idPersona!=null && idResponsable!=null) {// a una persona se le asigna un responsable
			if (!idPersona.equals(-1) && !idResponsable.equals(-1)) {
				serviciosPersona.asignarResponsable(idPersona, idResponsable);
			}	
		}
		session.put("idFilter", idFilter); // lo guardo simpre pq sino queda grabada siempre en la session
		
		return SUCCESS;
	}
	
	public String editarPersona(){
		session.put("idPerEdit", idPerEdit);
		return SUCCESS;
	}
	public String borrarPersona(){
		serviciosPersona.eliminarPersona(idPerRemove);
		return SUCCESS;
	}
	
//	gets and sets
	public ServiciosPersona getServiciosPersona() {
		return serviciosPersona;
	}
	
	@Autowired
	public void setServiciosPersona(ServiciosPersona serviciosPersona) {
		this.serviciosPersona = serviciosPersona;
	}



	public Persona getPersona() {
		return persona;
	}


	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	
	public Integer getIdResponsable() {
		return idResponsable;
	}


	public void setIdResponsable(Integer idResponsable) {
		this.idResponsable = idResponsable;
	}

	public Integer getIdPersona() {
		return idPersona;
	}


	public void setIdPersona(Integer idPersona) {
		this.idPersona = idPersona;
	}





	@Override
	public Persona getModel() {
		return persona;
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


	public Integer getIdPersonaContacto() {
		return idPersonaContacto;
	}


	public void setIdPersonaContacto(Integer idPersonaContacto) {
		this.idPersonaContacto = idPersonaContacto;
	}



	@Override
	public void setSession(Map<String, Object> session) {
		this.session=session;
	}


	public Integer getIdFilter() {
		return idFilter;
	}


	public void setIdFilter(Integer idFilter) {
		this.idFilter = idFilter;
	}

	public Integer getIdPerEdit() {
		return idPerEdit;
	}

	public void setIdPerEdit(Integer idPerEdit) {
		this.idPerEdit = idPerEdit;
	}

	public Integer getIdPerRemove() {
		return idPerRemove;
	}

	public void setIdPerRemove(Integer idPerRemove) {
		this.idPerRemove = idPerRemove;
	}


}
