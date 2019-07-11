package com.ors.junk.monty.persistence.model;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import javax.persistence.Id;

import com.orientechnologies.orient.core.id.ORID;
import com.ors.junk.monty.domain.model.CardGame;

public class CardGameEntity  implements CardGame, Persistable{

	@Id
	ORID orId;

	public UUID Id=UUID.randomUUID();

	public String  name;
	
	public Set<PlayerEntity> players=new HashSet<>();
	
	public GameDeckEntity gameDeck=new GameDeckEntity();

	
	
	@Override
	public ORID getOrId() {	
		return orId;
	};


	public void setOrId(ORID orId) {	
		this.orId=orId;
	};

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
