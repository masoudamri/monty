package com.ors.junk.monty.persistence.service.impl;

import java.util.UUID;

import com.orientechnologies.orient.core.db.ODatabaseType;
import com.orientechnologies.orient.core.db.OrientDBConfig;
import com.orientechnologies.orient.core.id.ORID;
import com.orientechnologies.orient.core.metadata.schema.OClass.INDEX_TYPE;
import com.orientechnologies.orient.object.db.OObjectDatabasePool;
import com.orientechnologies.orient.object.db.OObjectDatabaseTx;
import com.orientechnologies.orient.object.db.OrientDBObject;
import com.ors.junk.monty.persistence.model.Persistable;
import com.ors.junk.monty.persistence.model.PlayerEntity;
import com.ors.junk.monty.persistence.service.PersistenceService;

public class PersistenceServiceImpl implements PersistenceService {
	private OObjectDatabasePool db;

	static {
		OrientDBObject orientDb = new OrientDBObject("plocal:junk", OrientDBConfig.defaultConfig());
		if (orientDb.exists("monty")) {
			orientDb.drop("monty");
		}
		orientDb.create("monty", ODatabaseType.PLOCAL);
		orientDb.close();

		OObjectDatabasePool db = new OObjectDatabasePool("plocal:junk/monty", "admin", "admin");

		OObjectDatabaseTx tx = db.acquire();

		tx.setAutomaticSchemaGeneration(true);
		tx.getEntityManager().registerEntityClasses(Persistable.class.getPackage().getName());
		tx.getMetadata().getSchema().synchronizeSchema();
		tx.getMetadata().getSchema().getClass(PlayerEntity.class).getProperty("name").createIndex(INDEX_TYPE.UNIQUE);
		System.out.println(tx.getMetadata().getSchema().getClass(PlayerEntity.class).getProperty("name"));
		tx.commit();

	}

	public PersistenceServiceImpl() {

		db = new OObjectDatabasePool("plocal:junk/monty", "admin", "admin");
	}

	@Override
	public <T extends Persistable> T newEntity(Class<T> newEntityClass) {
		OObjectDatabaseTx tx = null;
		try {
			tx = db.acquire().begin();
			T object = (T) tx.newInstance(newEntityClass);
			return tx.save(object);
		} finally {
			tx.commit();
		}
	}

	@Override
	public <T extends Persistable> void update(T entity) {
		OObjectDatabaseTx tx = null;
		try {
			tx = db.acquire().begin();
			tx.save(entity);
		} finally {
			tx.commit();
		}
	}

	@Override
	public <T extends Persistable> T get(ORID id, Class<T> entity) {
		OObjectDatabaseTx tx = null;
		try {
			tx = db.acquire().begin();
			return tx.load(id);
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
	public <T extends Persistable> boolean delete(UUID id, Class<T> entity) {
		return false;
	}

}
