package com.ors.junk.monty.persistence.service.impl;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.google.inject.persist.PersistService;
import com.orientechnologies.orient.core.id.ORID;
import com.orientechnologies.orient.core.sql.executor.OResult;
import com.orientechnologies.orient.object.db.OObjectDatabaseTx;
import com.orientechnologies.orient.object.jpa.OJPAEntityManager;
import com.ors.junk.monty.persistence.model.Persistable;
import com.ors.junk.monty.persistence.service.PersistenceService;

public class PersistenceServiceImpl implements PersistenceService, AutoCloseable {

	Provider<EntityManager> emp;
	PersistService persistService;

	@Inject
	public PersistenceServiceImpl(Provider<EntityManager> emp, PersistService persistService) {
		this.emp = emp;
		this.persistService = persistService;
		persistService.start();
	}

	@Override
	public <T extends Persistable> T persist(T entity) {
		EntityManager em = emp.get();
		OObjectDatabaseTx tx = (OObjectDatabaseTx) em.getDelegate();
		return tx.save(entity);
	}

	@Override
	public <T extends Persistable> T get(ORID id, Class<T> entityClass) {
		EntityManager em = emp.get();
		T entity = em.find(entityClass, id);
		return entity;
	}

	@Override
	public <T extends Persistable> T find(String id, Class<T> entityClass) {
		EntityManager em = emp.get();
		em.getTransaction().begin();
		TypedQuery<T> query = em.createQuery(
				String.format("select from %s where bId=%?", entityClass.getSimpleName(), id), entityClass);
		T entity = query.getSingleResult();
		return entity;
	}

	@Override
	public <T extends Persistable> T findByName(String name, Class<T> entityClass) {
		OJPAEntityManager em = (OJPAEntityManager) emp.get();
		OObjectDatabaseTx tx = (OObjectDatabaseTx) em.getDelegate();

		OResult res = tx.query(String.format("select from %s where name=?", entityClass.getSimpleName()), name).next();
		T entity = get(res.getElement().get().getRecord().getIdentity(), entityClass);
		return entity;
	}

	@Override
	public <T extends Persistable> void delete(String id, Class<T> entityClass) {
		EntityManager em = emp.get();
		em.remove(find(id, entityClass));
	}

	@Override
	public void close() throws InterruptedException {
		emp.get().close();
	}

}
