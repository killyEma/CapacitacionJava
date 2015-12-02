package com.accenture.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;




import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.accenture.dao.GenericDao;

public abstract class GenericDaoJPAImpl <T, Id extends Serializable> implements GenericDao<T, Id>{

	private Class <T> claseDePersistencia;
	@PersistenceContext
	protected EntityManager manager;
	
	@SuppressWarnings("unchecked")
	public GenericDaoJPAImpl() {
		this.claseDePersistencia= (Class<T>)((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}

	public T buscarPorClave(Id id) {
		return getManager().find(claseDePersistencia,id);
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly=true)
	public List<T> buscarTodos() {
		List<T> listaDeDatos=null;
		TypedQuery<T> consulta=(TypedQuery<T>) manager.createQuery("SELECT o FROM "+claseDePersistencia.getSimpleName()+" o");
		listaDeDatos=consulta.getResultList();
		return listaDeDatos;
	}
	
	@Transactional(propagation=Propagation.REQUIRED)
	public void salvar(T objeto) {
		getManager().merge(objeto);
//		getJpaTemplate().flush();
	}

	@Transactional  //(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void borrar(T objeto) {
		getManager().remove(getManager().merge(objeto));
	}
	
	@Transactional
	public void insertar(T objeto) {
		getManager().persist(objeto);
		
	}

	public EntityManager getManager() {
		return manager;
	}

	public void setManager(EntityManager manager) {
		this.manager = manager;
	}




}
