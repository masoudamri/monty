package com.ors.junk.monty.domain.service.impl;

import javax.inject.Inject;

import com.ors.junk.monty.domain.model.Player;
import com.ors.junk.monty.domain.service.PlayerService;
import com.ors.junk.monty.persistence.model.PlayerEntity;
import com.ors.junk.monty.persistence.service.PersistenceService;

public class PlayerServiceImpl implements PlayerService {

	PersistenceService persistenceService;
	
	@Inject
	public PlayerServiceImpl(PersistenceService persistenceService) {
		this.persistenceService=persistenceService;
	}
	
	@Override
	public Player create(String name) {
		PlayerEntity player= new PlayerEntity();
		player.setName(name);
		return persistenceService.persist(player);
	}

	@Override
	public Player get(String name) {
		return persistenceService.findByName(name, PlayerEntity.class);
	}
}
