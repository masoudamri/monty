package com.ors.junk.monty.persistence.model;

import java.util.List;

import com.ors.junk.monty.domain.model.Card;
import com.ors.junk.monty.domain.model.GameDeck;

public class GameDeckEntity implements GameDeck{
	
	List<Card> cards;
	
	public List<Card> getCards() {
		return cards;
	}

	public void setCards(List<Card> cards) {
		this.cards = cards;
	}



}
