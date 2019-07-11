package com.ors.junk.monty.persistence.model;

import javax.persistence.Id;

import com.orientechnologies.orient.core.id.ORID;
import com.ors.junk.monty.domain.model.Card;

public class CardEntity  implements Card, Persistable{
	
	@Id
	ORID orId;
	
	DeckEntity deck;
	
	Suite suite;

	Face face;

	
	@Override
	public ORID getOrId() {	
		return orId;
	}


	public void setOrId(ORID orId) {	
		this.orId=orId;
	}

	
	
	public DeckEntity getDeck() {
		return deck;
	}
	public void setDeck(DeckEntity deck) {
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
