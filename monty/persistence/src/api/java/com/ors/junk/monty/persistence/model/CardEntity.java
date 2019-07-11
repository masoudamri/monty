package com.ors.junk.monty.persistence.model;

import javax.persistence.Id;

import com.orientechnologies.orient.core.id.ORID;
import com.ors.junk.monty.domain.model.Card;
import com.ors.junk.monty.domain.model.Deck;

public class CardEntity  implements Card, Persistable{
	
	@Id
	ORID orId;
	
	Deck deck;
	
	Suite suite;

	Face face;

	
	@Override
	public ORID getOrId() {	
		return orId;
	}


	public void setOrId(ORID orId) {	
		this.orId=orId;
	}

	
	
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
