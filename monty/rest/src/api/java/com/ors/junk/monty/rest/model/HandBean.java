package com.ors.junk.monty.rest.model;

import java.util.Set;

import com.ors.junk.monty.domain.model.Hand;

public class HandBean implements Hand {

	
	PlayerBean player;

	CardGameBean cardGame;

	Set<CardBean> cards;
	
	public PlayerBean getPlayer() {
		return player;
	}

	public void setPlayer(PlayerBean player) {
		this.player = player;
	}

	public CardGameBean getCardGame() {
		return cardGame;
	}

	public void setCardGame(CardGameBean cardGame) {
		this.cardGame = cardGame;
	}

	@SuppressWarnings("unchecked")
	public Set<CardBean> getCards() {
		return cards;
	}

	public void setCards(Set<CardBean> cards) {
		this.cards = cards;
	}

}
