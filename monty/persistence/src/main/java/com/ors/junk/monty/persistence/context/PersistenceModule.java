package com.ors.junk.monty.persistence.context;

import java.util.Map;
import java.util.Properties;

import com.google.inject.AbstractModule;
import com.google.inject.persist.jpa.JpaPersistModule;
import com.orientechnologies.orient.core.db.ODatabaseType;
import com.orientechnologies.orient.core.db.OrientDBConfig;
import com.orientechnologies.orient.core.metadata.schema.OClass.INDEX_TYPE;
import com.orientechnologies.orient.object.db.OObjectDatabasePool;
import com.orientechnologies.orient.object.db.OObjectDatabaseTx;
import com.orientechnologies.orient.object.db.OrientDBObject;
import com.ors.junk.monty.persistence.model.CardGameEntity;
import com.ors.junk.monty.persistence.model.Persistable;
import com.ors.junk.monty.persistence.model.PlayerEntity;
import com.ors.junk.monty.persistence.service.PersistenceService;
import com.ors.junk.monty.persistence.service.impl.PersistenceServiceImpl;

@SuppressWarnings("deprecation")
public class PersistenceModule extends AbstractModule{
	
	@Override
	protected void configure() {
		startup();
		install(new JpaPersistModule("montyJpaUnit").properties(orientDBProp()));
		bind(PersistenceService.class).to(PersistenceServiceImpl.class);
	}

	
	private static Properties orientDBProp() {
		return new Properties(){{
	        setProperty("javax.persistence.jdbc.url", "plocal:/tmp/monty");
	        setProperty("javax.persistence.jdbc.user", "admin");
	        setProperty("javax.persistence.jdbc.password", "admin");
	        setProperty("com.orientdb.entityClasses", Persistable.class.getPackage().getName());
	    }};
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

		tx.commit();
		db.close();
	}

}
