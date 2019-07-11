package com.ors.junk.monty.rest.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ors.junk.monty.domain.model.Card;
import com.ors.junk.monty.domain.model.Deck;

public class CardBean  implements Card{	

	Deck deck;
	
	Suite suite;

	Face face;

	
	
	public CardBean(Card c) {
		this.deck=c.getDeck();
		this.suite=c.getSuite();
		this.face=c.getFace();
	}
	
	@JsonIgnore
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


	@Override
	public Face getFace() {
		return face;
	}


	public void setFace(Face face) {
		this.face = face;
	}
	
}
