package com.ors.junk.monty.rest.resource.impl;

import javax.inject.Inject;

import com.ors.junk.monty.domain.model.Player;
import com.ors.junk.monty.domain.service.PlayerService;
import com.ors.junk.monty.rest.model.PlayerBean;
import com.ors.junk.monty.rest.resource.PlayerResource;

public class PlayerResourceImpl implements PlayerResource{
	
	private PlayerService playerService;

	@Inject
	public PlayerResourceImpl(PlayerService playerService) {
		this.playerService=playerService;
	}

	@Override
	public Player read(String name) {
		return new PlayerBean(playerService.get(name));
	}

	@Override
	public Player create(String name) {
		return new PlayerBean(playerService.create(name));
	}

	@Override
	public void deletePlayer(String name) {
		playerService.deletePlayer(name);
	}

}
