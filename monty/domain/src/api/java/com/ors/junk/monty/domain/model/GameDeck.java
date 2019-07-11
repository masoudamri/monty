package com.ors.junk.monty.domain.model;

import java.util.List;

public interface GameDeck {
	
	<T extends Card> List<T> getCards();

}
