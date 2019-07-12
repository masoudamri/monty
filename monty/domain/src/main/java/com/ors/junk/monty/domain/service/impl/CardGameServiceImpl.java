package com.ors.junk.monty.domain.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import com.ors.junk.monty.domain.model.Card;
import com.ors.junk.monty.domain.model.CardGame;
import com.ors.junk.monty.domain.model.Hand;
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
		try {
			get(name);
		} catch (RuntimeException e) {

			CardGameEntity cardGame = new CardGameEntity();
			cardGame.setName(name);
			GameDeckEntity gameDeck = persistenceService.persist(new GameDeckEntity());
			cardGame.setGameDeck(gameDeck);
			return persistenceService.persist(cardGame);
		}
		throw new RuntimeException("game with this name already exists");
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
		List<CardEntity> cards = new ArrayList<>(size);
		for (int i = 0; i < permutation.size(); i++) {
			cards.add(deck.getCards().get(permutation.get(i)));
		}
		deck.setCards(cards);
		persistenceService.persist(deck);
	}

	@Override
	public CardGame get(String name) {
		return persistenceService.findByName(name, CardGameEntity.class);
	}

	@Override
	public void addPlayer(String playerName, String cardGameName) {
		PlayerEntity player = persistenceService.findByName(playerName, PlayerEntity.class);
		CardGameEntity cardGame = persistenceService.findByName(cardGameName, CardGameEntity.class);
		if (player == null || cardGame == null) {
			throw new RuntimeException("Player or game not found");
		}
		if (cardGame.getPlayers().stream().anyMatch(p -> p.getBId().equals(player.getBId()))) {
			throw new RuntimeException("Player already in game");
		}
		cardGame.getPlayers().add(player);
		HandEntity hand = new HandEntity();
		hand.setPlayer(player);
		player.getHands().put(cardGameName, (Hand) hand);
		persistenceService.persist(hand);
		persistenceService.persist(player);
	}

	@Override
	public void dealCard(String playerName, String cardGameName) {
		PlayerEntity player = persistenceService.findByName(playerName, PlayerEntity.class);
		CardGameEntity cardGame = persistenceService.findByName(cardGameName, CardGameEntity.class);
		if (player == null || cardGame == null) {
			throw new RuntimeException("Player or game not found");
		}
		if (!cardGame.getPlayers().contains(player)) {
			throw new RuntimeException("Player not in game");
		}
		HandEntity hand = (HandEntity) player.getHands().get(cardGameName);
		GameDeckEntity gameDeck = cardGame.getGameDeck();
		int size = gameDeck.getCards().size();
		if (size == 0) {
			throw new RuntimeException("No more cards!");
		}
		hand.getCards().add(gameDeck.getCards().remove(size - 1));
		persistenceService.persist(hand);
		persistenceService.persist(cardGame);
	}

	@Override
	public String addNewDeck(String cardGameName) {
		CardGameEntity cardGame = persistenceService.findByName(cardGameName, CardGameEntity.class);
		GameDeckEntity gameDeck = cardGame.getGameDeck();
		DeckEntity deckEntity = new DeckEntity();
		persistenceService.persist(deckEntity);
		for (Card.Suite suite : Card.Suite.values()) {
			for (Card.Face face : Card.Face.values()) {
				CardEntity cardEntity = new CardEntity();
				cardEntity.setDeck(deckEntity);
				cardEntity.setSuite(suite);
				cardEntity.setFace(face);
				persistenceService.persist(cardEntity);
				gameDeck.getCards().add(cardEntity);
			}
		}
		persistenceService.persist(gameDeck);
		persistenceService.persist(cardGame);
		return deckEntity.getBId();
	}

	@Override
	public void deleteDeck(String name) {
		persistenceService.delete(persistenceService.findByName(name, CardGameEntity.class).getBId(),
				CardGameEntity.class);

	}

}