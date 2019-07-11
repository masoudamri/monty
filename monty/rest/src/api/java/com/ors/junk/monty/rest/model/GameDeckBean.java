package com.ors.junk.monty.rest.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.ors.junk.monty.domain.model.GameDeck;

public class GameDeckBean implements GameDeck{
	
	List<CardBean> cards=new ArrayList<>();
	
	public GameDeckBean(GameDeck gameDeck) {
		cards=gameDeck.getCards().stream().map(c->new CardBean(c)).collect(Collectors.toList());
	}

	@SuppressWarnings("unchecked")
	public List<CardBean> getCards() {
		return cards;
	}

	public void setCards(List<CardBean> cards) {
		this.cards = cards;
	}



}
