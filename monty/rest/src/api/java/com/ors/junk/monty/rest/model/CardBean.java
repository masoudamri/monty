package com.ors.junk.monty.rest.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ors.junk.monty.domain.model.Card;
import com.ors.junk.monty.domain.model.Deck;

public class CardBean implements Card {

	Deck deck;

	Suite suite;

	Face face;

	public CardBean(Card c) {
		this.deck = c.getDeck();
		this.suite = c.getSuite();
		this.face = c.getFace();
	}

	public CardBean() {
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
	

	@Override
	public String toString() {
		return "CardBean [deck=" + deck + ", suite=" + suite + ", face=" + face + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((deck == null) ? 0 : deck.hashCode());
		result = prime * result + ((face == null) ? 0 : face.hashCode());
		result = prime * result + ((suite == null) ? 0 : suite.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CardBean other = (CardBean) obj;
		if (deck == null) {
			if (other.deck != null)
				return false;
		} else if (!deck.equals(other.deck))
			return false;
		if (face != other.face)
			return false;
		if (suite != other.suite)
			return false;
		return true;
	}

}
