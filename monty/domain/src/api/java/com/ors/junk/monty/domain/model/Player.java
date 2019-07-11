package com.ors.junk.monty.domain.model;

import java.util.Set;
import java.util.UUID;

public interface Player {
	
	public UUID getId();
	
	public String getName();
		
	public <T extends CardGame> Set<T> getGames();

	public Hand getHand(CardGame game);

}
