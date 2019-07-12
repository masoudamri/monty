package com.ors.junk.monty.domain.model;

import java.util.Set;

public interface CardGame {

	public String getBId();

	public String  getName();
	
	public <T extends Player> Set<T> getPlayers();
	
	public GameDeck getGameDeck();
		
}
