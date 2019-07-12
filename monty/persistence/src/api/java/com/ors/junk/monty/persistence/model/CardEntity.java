package com.ors.junk.monty.persistence.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.orientechnologies.orient.core.id.ORID;
import com.ors.junk.monty.domain.model.Card;

@Entity
public class CardEntity  implements Card, Persistable{
	
	@Id
	ORID id;
	
	@ManyToOne
	DeckEntity deck;
	
	Suite suite;

	Face face;

	
	@Override
	public ORID getId() {	
		return id;
	}


	public void setId(ORID id) {	
		this.id=id;
	}

	
	@Override
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
