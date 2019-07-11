package com.ors.junk.monty.domain.service;

import java.util.UUID;

import com.ors.junk.monty.domain.model.Player;

public interface PlayerService {
	
	
	Player create(String name);

	Player get(UUID id);

}
