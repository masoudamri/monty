package com.ors.junk.monty.domain.model;

import java.util.Set;
import java.util.UUID;

public interface CardGame {

	public UUID getId();

	public String  getName();
	
	public <T extends Player> Set<T> getPlayers();
	
	public GameDeck getGameDeck();
		
}
