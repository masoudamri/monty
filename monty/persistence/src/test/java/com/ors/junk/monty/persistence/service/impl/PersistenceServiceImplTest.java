package com.ors.junk.monty.persistence.service.impl;

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
		new PersistenceModule().startup();
		persistenceService=new PersistenceServiceImpl();
	}
	
	//@Test
	public void simplePlayerTest() {
		PlayerEntity player = persistenceService.newEntity(PlayerEntity.class);
		player.setName("mike");
		persistenceService.update(player);

		System.out.println(persistenceService.get(player.getOrId(), PlayerEntity.class).getName());
	}

	@Test
	public void simplePlayerTest2() {
		Assertions.assertThrows(ORecordDuplicatedException.class, () -> {
			PlayerEntity player = persistenceService.newEntity(PlayerEntity.class);
			player.setName("mike");
			persistenceService.update(player);

			PlayerEntity player2 = persistenceService.newEntity(PlayerEntity.class);
			player2.setName("mike");
			persistenceService.update(player2);

			System.out.println(persistenceService.get(player.getOrId(), PlayerEntity.class).getName());
		});
	}
	
	@AfterEach
	public void after() throws InterruptedException {
		persistenceService.close();
	}

}
