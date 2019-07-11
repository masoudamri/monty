package com.ors.junk.monty.persistence.model;

import com.ors.junk.monty.domain.model.Card;
import com.ors.junk.monty.domain.model.Deck;

public class CardEntity  implements Card{
	Deck deck;
	Suite suite;
	public Deck getDeck() {
		return deck;
	}
	public void setDeck(Deck deck) {
		this.deck = deck;
	}
	public Suite getSuite() {
		return suite;
	}
	public void setSuite(Suite suite) {
		this.suite = suite;
	}
	
}
