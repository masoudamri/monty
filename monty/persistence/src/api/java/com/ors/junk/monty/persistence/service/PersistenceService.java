package com.ors.junk.monty.persistence.service;

import com.orientechnologies.orient.core.id.ORID;
import com.ors.junk.monty.persistence.model.Persistable;


public interface PersistenceService {
	
	<T extends Persistable> T persist(T newEntityClass);

	<T extends Persistable> T get(ORID id, Class<T> entity);

	<T extends Persistable> T find(String bId, Class<T> entity);

	<T extends Persistable> void delete(String bid, Class<T> entity);

	<T extends Persistable> T findByName(String name, Class<T> entityClass);

}
