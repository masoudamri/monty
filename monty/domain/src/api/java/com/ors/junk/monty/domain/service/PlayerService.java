package com.ors.junk.monty.domain.service;

import com.ors.junk.monty.domain.model.Player;

public interface PlayerService {
	
	
	Player create(String name);

	Player get(String name);

	void deletePlayer(String name);

}
