package com.ors.junk.monty.rest.resource.impl;

import java.util.List;

import javax.inject.Inject;

import com.ors.finance.fyaat.rest.resource.PlayerResource;
import com.ors.junk.monty.domain.model.Player;
import com.ors.junk.monty.domain.service.PlayerService;

public class PlayerResourceImpl implements PlayerResource{
	
	private PlayerService playerService;

	@Inject
	public PlayerResourceImpl(PlayerService playerService) {
		this.playerService=playerService;
	}

	@Override
	public List<Player> readAll() {
		return null;
	}

	@Override
	public Player read(String name) {
		return playerService.get(name);
	}

	@Override
	public Player create(String name) {
		return playerService.create(name);
	}

}
