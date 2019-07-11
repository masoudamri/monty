package com.ors.junk.monty.persistence.model;

import java.util.Set;

import javax.persistence.Id;

import com.orientechnologies.orient.core.id.ORID;
import com.ors.junk.monty.domain.model.Hand;

public class HandEntity implements Hand, Persistable {

	
	@Id
	ORID orId;
	
	PlayerEntity player;

	CardGameEntity cardGame;

	Set<CardEntity> cards;

	
	@Override
	public ORID getOrId() {	
		return orId;
	};


	public void setOrId(ORID orId) {	
		this.orId=orId;
	};

	
	public PlayerEntity getPlayer() {
		return player;
	}

	public void setPlayer(PlayerEntity player) {
		this.player = player;
	}

	public CardGameEntity getCardGame() {
		return cardGame;
	}

	public void setCardGame(CardGameEntity cardGame) {
		this.cardGame = cardGame;
	}

	@SuppressWarnings("unchecked")
	public Set<CardEntity> getCards() {
		return cards;
	}

	public void setCards(Set<CardEntity> cards) {
		this.cards = cards;
	}

}
