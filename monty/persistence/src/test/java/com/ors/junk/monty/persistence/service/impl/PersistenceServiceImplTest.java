package com.ors.junk.monty.persistence.service.impl;

import org.embulk.guice.Bootstrap;
import org.embulk.guice.LifeCycleInjector;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.orientechnologies.orient.core.storage.ORecordDuplicatedException;
import com.ors.junk.monty.persistence.context.PersistenceModule;
import com.ors.junk.monty.persistence.model.PlayerEntity;

public class PersistenceServiceImplTest {

	PersistenceServiceImpl persistenceService;

	
	@BeforeEach
	public void before(){		
		LifeCycleInjector inj=new Bootstrap(new PersistenceModule()).requireExplicitBindings(false).initialize();
		persistenceService=inj.getInstance(PersistenceServiceImpl.class);
	}
	
	//@Test
	public void simplePlayerTest() {
		PlayerEntity player =new PlayerEntity();
		player.setName("mike");
		System.out.println(persistenceService.get(player.getOrId(), PlayerEntity.class).getName());
		persistenceService.persist(player);
	}

	@Test
	public void simplePlayerTest2() {
		Assertions.assertThrows(ORecordDuplicatedException.class, () -> {
			PlayerEntity player = new PlayerEntity();
			player.setName("mike");

			persistenceService.persist(player);

			PlayerEntity player2 = new PlayerEntity();			
			player2.setName("mike");
			persistenceService.persist(player2);

			System.out.println(persistenceService.get(player.getOrId(), PlayerEntity.class).getName());
		});
	}
	
	@AfterEach
	public void after() throws InterruptedException {
		persistenceService.close();
	}

}
