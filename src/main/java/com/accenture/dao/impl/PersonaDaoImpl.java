package com.accenture.dao.impl;

import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.accenture.dao.PersonaDAO;
import com.accenture.model.Persona;

@Repository
public class PersonaDaoImpl extends GenericDaoJPAImpl<Persona,Integer> implements PersonaDAO{

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly=true)
	public List<Persona> buscarSubordinados(Integer idFilter) {
		TypedQuery<Persona> consulta=(TypedQuery<Persona>) getManager().createQuery("SELECT p FROM Persona p WHERE p.responsable.id= :idResponsable");
		consulta.setParameter("idResponsable", idFilter);
		return consulta.getResultList();
	}

}
