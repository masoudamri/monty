package com.ors.junk.monty.persistence.service.impl;

import java.util.UUID;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.orientechnologies.orient.core.id.ORID;
import com.ors.junk.monty.persistence.model.Persistable;
import com.ors.junk.monty.persistence.service.PersistenceService;

public class PersistenceServiceImpl implements PersistenceService, AutoCloseable {

	Provider<EntityManager> emp;

	@Inject
	public PersistenceServiceImpl(	Provider<EntityManager> emp) {
		this.emp=emp;
	}

	@Override
	public <T extends Persistable> T persist(T newEntityClass) {
		EntityManager em = emp.get();
		try {
			em.getTransaction().begin();
			em.persist(newEntityClass);
			em.getTransaction().commit();
			return newEntityClass;
		} finally {
			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
		}
	}

	@Override
	public <T extends Persistable> T update(T entity) {
		EntityManager em = emp.get();
		try {
			em.getTransaction().begin();
			T merged = em.merge(entity);
			em.getTransaction().commit();
			return merged;
		} finally {
			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
		}
	}

	@Override
	public <T extends Persistable> T get(ORID id, Class<T> entityClass) {
		EntityManager em = emp.get();
		try {
			em.getTransaction().begin();
			T entity = em.find(entityClass, id);
			em.getTransaction().commit();
			return entity;
		} finally {
			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
		}
	}

	@Override
	public <T extends Persistable> T find(UUID id, Class<T> entityClass) {
		EntityManager em = emp.get();
		try {
			em.getTransaction().begin();
			TypedQuery<T> query = em.createQuery(
					String.format("select from %s where id=%s", entityClass.getSimpleName(), id), entityClass);
			T entity = query.getSingleResult();
			em.getTransaction().commit();
			return entity;
		} finally {
			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
		}
	}

	@Override
	public <T extends Persistable> T findByName(String name, Class<T> entityClass) {
		EntityManager em = emp.get();
		try {
			em.getTransaction().begin();
			TypedQuery<T> query = em.createQuery(
					String.format("select from %s where name=%s", entityClass.getSimpleName(), name), entityClass);
			T entity = query.getSingleResult();
			em.getTransaction().commit();
			return entity;
		} finally {
			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
		}
	}

	@Override
	public <T extends Persistable> void delete(UUID id, Class<T> entityClass) {
		EntityManager em = emp.get();
		try {
			em.getTransaction().begin();
			em.remove(find(id, entityClass));
			em.getTransaction().commit();
		} finally {
			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
		}
	}

	@Override
	public void close() throws InterruptedException {

	}

}
