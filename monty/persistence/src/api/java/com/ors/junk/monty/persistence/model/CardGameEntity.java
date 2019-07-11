package com.ors.junk.monty.persistence.model;

import java.util.Set;
import java.util.UUID;

import com.ors.junk.monty.domain.model.CardGame;

public class CardGameEntity  implements CardGame{

	public UUID Id;

	public String  name;
	
	public Set<PlayerEntity> players;
	
	public GameDeckEntity gameDeck;

	public UUID getId() {
		return Id;
	}

	public void setId(UUID id) {
		Id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@SuppressWarnings("unchecked")
	public Set<PlayerEntity> getPlayers() {
		return players;
	}

	public void setPlayers(Set<PlayerEntity> players) {
		this.players = players;
	}

	public GameDeckEntity getGameDeck() {
		return gameDeck;
	}

	public void setGameDeck(GameDeckEntity gameDeck) {
		this.gameDeck = gameDeck;
	}
		
}
