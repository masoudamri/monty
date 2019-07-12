package com.ors.junk.monty.rest.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import com.ors.junk.monty.domain.model.Card;

public class UndealtCardsBean {
	

	Map<Card.Suite,Integer> undealtCards=new HashMap<>();

	public Map<Card.Suite, Integer> getUndealtCards() {
		return undealtCards;
	}

	public void setUndealtCards(Map<Card.Suite, Integer> undealtCards) {
		this.undealtCards = undealtCards;
	}

}
