package com.ors.junk.monty.rest.model;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ors.junk.monty.domain.model.Card;
import com.ors.junk.monty.domain.model.Hand;

public class HandBean implements Hand {

	
	PlayerBean player;

	CardGameBean cardGame;

	Set<CardBean> cards;
	
	public HandBean(Hand hand) {
		cards=new HashSet<>();
		hand.getCards().forEach(c->cards.add(new CardBean(c)));
	}

	@JsonIgnore
	public PlayerBean getPlayer() {
		return player;
	}

	public void setPlayer(PlayerBean player) {
		this.player = player;
	}

	@JsonIgnore
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
	
	public int score() {
		return cards.stream().map(Card::getFace)
			.collect(Collectors.summingInt(Card.Face::getScore));
	}

}
