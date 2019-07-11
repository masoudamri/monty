package com.ors.junk.monty.domain.service;

import com.ors.junk.monty.domain.model.CardGame;
import com.ors.junk.monty.domain.model.Player;

public interface CardGameService {

	CardGame create(String name);

	CardGame get(long id);

	void addPlayer(Player player, CardGame cardGame);

	void dealCard(Player player, CardGame cardGame);

	void shuffle(CardGame cardGame);
}
