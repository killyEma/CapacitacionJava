package com.accenture.dao;

import java.util.List;

import com.accenture.model.Persona;

public interface PersonaDAO extends GenericDao<Persona, Integer>{

	List<Persona> buscarSubordinados(Integer idFilter);

}
