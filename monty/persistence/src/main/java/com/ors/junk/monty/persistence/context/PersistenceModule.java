package com.ors.junk.monty.persistence.context;

import com.google.inject.AbstractModule;
import com.orientechnologies.orient.core.db.ODatabaseType;
import com.orientechnologies.orient.core.db.OrientDBConfig;
import com.orientechnologies.orient.core.metadata.schema.OClass.INDEX_TYPE;
import com.orientechnologies.orient.object.db.OObjectDatabasePool;
import com.orientechnologies.orient.object.db.OObjectDatabaseTx;
import com.orientechnologies.orient.object.db.OrientDBObject;
import com.ors.junk.monty.persistence.model.CardGameEntity;
import com.ors.junk.monty.persistence.model.DeckEntity;
import com.ors.junk.monty.persistence.model.Persistable;
import com.ors.junk.monty.persistence.model.PlayerEntity;
import com.ors.junk.monty.persistence.service.PersistenceService;
import com.ors.junk.monty.persistence.service.impl.PersistenceServiceImpl;

@SuppressWarnings("deprecation")
public class PersistenceModule extends AbstractModule {

	@Override
	protected void configure() {
		startup();
		bind(PersistenceService.class).to(PersistenceServiceImpl.class).asEagerSingleton();;
	}


	public void startup() {
		OrientDBObject orientDb = new OrientDBObject("plocal:/tmp/", OrientDBConfig.defaultConfig());
		if (orientDb.exists("monty")) {
			orientDb.drop("monty");
		}
		orientDb.create("monty", ODatabaseType.PLOCAL);
		orientDb.close();

		OObjectDatabasePool db = new OObjectDatabasePool("plocal:/tmp/monty", "admin", "admin");

		OObjectDatabaseTx tx = db.acquire();
		tx.setAutomaticSchemaGeneration(true);
		tx.getEntityManager().registerEntityClasses(Persistable.class.getPackage().getName());
		tx.getMetadata().getSchema().getClass(PlayerEntity.class).getProperty("name").createIndex(INDEX_TYPE.UNIQUE);
		tx.getMetadata().getSchema().getClass(CardGameEntity.class).getProperty("name").createIndex(INDEX_TYPE.UNIQUE);

		tx.getMetadata().getSchema().getClass(PlayerEntity.class).getProperty("bId").createIndex(INDEX_TYPE.UNIQUE);
		tx.getMetadata().getSchema().getClass(CardGameEntity.class).getProperty("bId").createIndex(INDEX_TYPE.UNIQUE);
		tx.getMetadata().getSchema().getClass(DeckEntity.class).getProperty("bId").createIndex(INDEX_TYPE.UNIQUE);

		tx.setAutomaticSchemaGeneration(true);

		tx.commit();
		db.close();

	}}
