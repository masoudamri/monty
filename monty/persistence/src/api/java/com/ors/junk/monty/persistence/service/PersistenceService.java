package com.ors.junk.monty.persistence.service;

import java.util.UUID;

import com.orientechnologies.orient.core.id.ORID;
import com.ors.junk.monty.persistence.model.Persistable;


public interface PersistenceService {
	
	
	<T extends Persistable> T newEntity(Class<T> newEntityClass);

	<T extends Persistable> void update(T entity);

	<T extends Persistable> T get(ORID id, Class<T> entity);

	<T extends Persistable> T find(UUID id, Class<T> entity);

	<T extends Persistable> boolean delete(UUID id, Class<T> entity);


}
