package com.accenture.dao.impl;

import org.springframework.stereotype.Repository;

import com.accenture.dao.ContactoDAO;
import com.accenture.model.Contacto;

@Repository
public class ContactoDaoImpl extends GenericDaoJPAImpl<Contacto, Integer> implements ContactoDAO{

}
