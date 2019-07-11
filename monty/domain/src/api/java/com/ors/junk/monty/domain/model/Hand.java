package com.ors.junk.monty.domain.model;

import java.util.Set;

public interface Hand {

	public Player getPlayer();

	public CardGame getCardGame();

	public <T extends Card> Set<T> getCards();

}
