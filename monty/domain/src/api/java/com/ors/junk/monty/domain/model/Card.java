package com.ors.junk.monty.domain.model;

public interface Card {
	
	enum Suite{
		HEARTS, DIAMONDS,SPADES,CLOVERS
	}
	
	enum Face{
		ACE(1),
		TWO(2),
		THREE(3),
		FOUR(4), 
		FIVE(5),
		SIX(6), 
		SEVEN(7),
		EIGHT(8),
		NINE(9), 
		TEN(10),
		JACK(11),
		QUEEN(12),
		KING(13);
		int value;
		Face(int value) {
			this.value=value;
		}
	}

	
	Deck getDeck();
	
	Suite getSuite();
	
	
}