package com.ors.junk.monty.domain.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.inject.Inject;

import com.ors.junk.monty.domain.model.Card;
import com.ors.junk.monty.domain.model.CardGame;
import com.ors.junk.monty.domain.service.CardGameService;
import com.ors.junk.monty.domain.service.PermuteService;
import com.ors.junk.monty.persistence.model.CardEntity;
import com.ors.junk.monty.persistence.model.CardGameEntity;
import com.ors.junk.monty.persistence.model.DeckEntity;
import com.ors.junk.monty.persistence.model.GameDeckEntity;
import com.ors.junk.monty.persistence.model.HandEntity;
import com.ors.junk.monty.persistence.model.PlayerEntity;
import com.ors.junk.monty.persistence.service.PersistenceService;

public class CardGameServiceImpl implements CardGameService {

	private PermuteService permuteService;
	private PersistenceService persistenceService;

	@Inject
	CardGameServiceImpl(PermuteService permuteService, PersistenceService persistenceService) {
		this.permuteService = permuteService;
		this.persistenceService = persistenceService;
	}

	@Override
	public CardGame create(String name) {
		CardGameEntity cardGame = persistenceService.newEntity(CardGameEntity.class);
		cardGame.setName(name);
		return persistenceService.update(cardGame);
	}

	@Override
	public void shuffle(String cardGameName) {
		CardGameEntity cardGame = persistenceService.findByName(cardGameName, CardGameEntity.class);
		GameDeckEntity deck = cardGame.getGameDeck();
		int size = deck.getCards().size();
		if (size <= 1) {
			return;
		}
		List<Integer> permutation = permuteService.permute(size - 1);
		List<Card> cards = new ArrayList<>(size);
		for (int i = 0; i < permutation.size(); i++) {
			cards.set(i, deck.getCards().get(permutation.get(i)));
		}
		persistenceService.update(deck);
	}

	@Override
	public CardGame get(String name) {
		return persistenceService.findByName(name, CardGameEntity.class);
	}

	@Override
	public void addPlayer(String playerName, String cardGameName) {
		PlayerEntity player = persistenceService.findByName(playerName, PlayerEntity.class);
		CardGameEntity cardGame = persistenceService.findByName(playerName, CardGameEntity.class);
		if (player == null || cardGame == null) {
			throw new RuntimeException("Player or game not found");
		}
		if (cardGame.getPlayers().contains(player)) {
			throw new RuntimeException("Player already in game");
		}
		cardGame.getPlayers().add(player);
		player.newHand(cardGame);
		persistenceService.update(cardGame);
		persistenceService.update(player);
	}

	@Override
	public void dealCard(String playerName, String cardGameName) {
		PlayerEntity player = persistenceService.findByName(playerName, PlayerEntity.class);
		CardGameEntity cardGame = persistenceService.findByName(playerName, CardGameEntity.class);
		if (player == null || cardGame == null) {
			throw new RuntimeException("Player or game not found");
		}
		if (!cardGame.getPlayers().contains(player)) {
			throw new RuntimeException("Player not in game");
		}
		HandEntity hand = player.getHand(cardGame);
		GameDeckEntity gameDeck = cardGame.getGameDeck();
		int size = gameDeck.getCards().size();
		if (size == 0) {
			throw new RuntimeException("No more cards!");
		}
		hand.getCards().add(gameDeck.getCards().remove(size - 1));
		persistenceService.update(hand);
		persistenceService.update(cardGame);
	}

	@Override
	public UUID addNewDeck(String cardGameName) {
		GameDeckEntity gameDeck = persistenceService.findByName(cardGameName, CardGameEntity.class).getGameDeck();
		DeckEntity deck = persistenceService.newEntity(DeckEntity.class);
		for (Card.Suite suite : Card.Suite.values()) {
			for (Card.Face face : Card.Face.values()) {
				CardEntity cardEntity = persistenceService.newEntity(CardEntity.class);
				cardEntity.setDeck(deck);
				cardEntity.setSuite(suite);
				cardEntity.setFace(face);
				persistenceService.update(cardEntity);
				gameDeck.getCards().add(cardEntity);
			}
		}
		persistenceService.update(deck);
		persistenceService.update(gameDeck);
		return deck.getId();
	}
}