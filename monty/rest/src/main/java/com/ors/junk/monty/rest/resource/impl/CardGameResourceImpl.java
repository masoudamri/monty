package com.ors.junk.monty.rest.resource.impl;

import java.util.List;

import javax.inject.Inject;

import com.ors.junk.monty.domain.model.CardGame;
import com.ors.junk.monty.domain.service.CardGameService;
import com.ors.junk.monty.rest.model.CardGameBean;
import com.ors.junk.monty.rest.resource.CardGameResource;

public class CardGameResourceImpl implements CardGameResource {
	
	private CardGameService cardGameService;

	@Inject
	public CardGameResourceImpl(CardGameService cardGameService) {
		this.cardGameService=cardGameService;
	}

	
	@Override
	public List<CardGame> readAll() {
		return null;
	}

	@Override
	public CardGame read(String name) {
		return new CardGameBean(cardGameService.get(name));
	}

	@Override
	public CardGame create(String name) {
		return new CardGameBean(cardGameService.create(name));
	}

	@Override
	public void shuffle(String name) {
		cardGameService.shuffle(name);
	}
	
	@Override
	public void addPlayer(String cardGameName, String playerName) {
		cardGameService.addPlayer(playerName, cardGameName);
	}

	@Override
	public void deal(String cardGameName, String playerName) {
		cardGameService.dealCard(playerName, cardGameName);
	}

	@Override
	public void addDeck(String name) {
		cardGameService.addNewDeck(name);
	}
	
}
