package com.ors.junk.monty.persistence.service.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.orientechnologies.orient.core.storage.ORecordDuplicatedException;
import com.ors.junk.monty.persistence.model.PlayerEntity;
import com.ors.junk.monty.persistence.service.PersistenceService;

public class PersistenceServiceImplTest {

	PersistenceService persistenceService = new PersistenceServiceImpl();

	@Test
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

}
