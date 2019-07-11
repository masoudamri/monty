package com.ors.junk.monty.persistence.service.impl;

import java.util.UUID;

import com.orientechnologies.orient.core.db.ODatabaseType;
import com.orientechnologies.orient.core.db.OrientDBConfig;
import com.orientechnologies.orient.core.db.object.ODatabaseObject;
import com.orientechnologies.orient.core.id.ORID;
import com.orientechnologies.orient.object.db.OrientDBObject;
import com.ors.junk.monty.persistence.model.Persistable;
import com.ors.junk.monty.persistence.service.PersistenceService;

public class PersistenceServiceImpl implements PersistenceService {

	private final OrientDBObject orientDb;
	private ODatabaseObject db;

	public PersistenceServiceImpl() {
		// orientDB = new OrientDBObject("plocal:monty",
		// OrientDBConfig.defaultConfig());
		// db = orientDB.open("monty", "admin", "admin_passwd");

		orientDb = new OrientDBObject("memory:monty", OrientDBConfig.defaultConfig());
		orientDb.create("monty", ODatabaseType.MEMORY);

		db.getEntityManager().registerEntityClasses(Persistable.class.getPackage().getName());
		db.commit();
	}

	@Override
	public <T extends Persistable> T newEntity(Class<T> newEntityClass) {
		try {
			db.begin();
			T object = (T) db.newInstance(newEntityClass);
			return db.save(object);
		} finally {
			db.commit();
		}
	}

	@Override
	public <T extends Persistable> void update(T entity) {
		try {
			db.begin();
			db.save(entity);
		} finally {
			db.commit();
		}
	}

	@Override
	public <T extends Persistable> T get(ORID id, Class<T> entity) {
		try {
			db.begin();
			return db.load(id);
		} finally {
			db.commit();
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	public <T extends Persistable> T find(UUID id, Class<T> entityClass) {
		return (T) db.query(String.format("select from %s where id=?", entityClass.getSimpleName()), id);
	}

	@Override
	public <T extends Persistable> boolean delete(UUID id, Class<T> entity) {
		// TODO Auto-generated method stub
		return false;
	}

}
