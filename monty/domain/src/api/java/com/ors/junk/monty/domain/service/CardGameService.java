package com.ors.junk.monty.domain.service;

import java.util.UUID;

import com.ors.junk.monty.domain.model.CardGame;

public interface CardGameService {

	CardGame create(String name);

	CardGame get(String name);
	
	UUID addNewDeck(String cardGameName);

	void addPlayer(String playerName, String cardGameName);

	void dealCard(String playerName, String cardGameName);

	void shuffle(String cardGameName);
}
