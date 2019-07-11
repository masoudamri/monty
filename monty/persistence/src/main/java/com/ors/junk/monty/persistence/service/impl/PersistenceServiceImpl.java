package com.ors.junk.monty.persistence.service.impl;

import java.util.UUID;

import com.orientechnologies.orient.core.id.ORID;
import com.orientechnologies.orient.object.db.OObjectDatabasePool;
import com.orientechnologies.orient.object.db.OObjectDatabaseTx;
import com.ors.junk.monty.persistence.model.Persistable;
import com.ors.junk.monty.persistence.service.PersistenceService;

@SuppressWarnings("deprecation")
public class PersistenceServiceImpl implements PersistenceService, AutoCloseable {
	private OObjectDatabasePool db;


	public PersistenceServiceImpl() {
		db = new OObjectDatabasePool("plocal:junk/monty", "admin", "admin");
	}

	@Override
	public <T extends Persistable> T newEntity(Class<T> newEntityClass) {
		OObjectDatabaseTx tx = null;
		try {
			tx = db.acquire().begin();
			return (T) tx.newInstance(newEntityClass);
		} finally {
			tx.commit();
		}
	}

	@Override
	public <T extends Persistable> T update(T entity) {
		OObjectDatabaseTx tx = null;
		try {
			tx = db.acquire().begin();
			return tx.save(entity);
		} finally {
			tx.commit();
		}
	}

	@Override
	public <T extends Persistable, O> T get(O id, Class<T> entity) {
		OObjectDatabaseTx tx = null;
		try {
			tx = db.acquire().begin();
			return tx.load((ORID)id);
		} finally {
			tx.commit();
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	public <T extends Persistable> T find(UUID id, Class<T> entityClass) {
		OObjectDatabaseTx tx = null;
		try {
			tx = db.acquire().begin();
			return (T) tx.query(String.format("select from %s where id=?", entityClass.getSimpleName()), id);
		} finally {
			tx.commit();
		}
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public <T extends Persistable> T findByName(String name, Class<T> entityClass) {
		OObjectDatabaseTx tx = null;
		try {
			tx = db.acquire().begin();
			return (T) tx.query(String.format("select from %s where name=?", entityClass.getSimpleName()), name);
		} finally {
			tx.commit();
		}
	}

	@Override
	public <T extends Persistable> boolean delete(UUID id, Class<T> entity) {
		return false;
	}
	
	@Override
	public void close() throws InterruptedException {
		db.close();
		Thread.sleep(5000);
	}

}
