package com.ors.junk.monty.persistence.model;

import java.util.Set;

import com.ors.junk.monty.domain.model.Hand;

public class HandEntity implements Hand {

	PlayerEntity player;

	CardGameEntity cardGame;

	Set<CardEntity> cards;

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
