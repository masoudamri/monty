package com.ors.junk.monty.persistence.model;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.orientechnologies.orient.core.id.ORID;
import com.ors.junk.monty.domain.model.CardGame;

@Entity
public class CardGameEntity  implements CardGame, Persistable{

	@Id
	ORID id;

	@Column(unique=true)
	public String bId=UUID.randomUUID().toString();

	@Column(unique=true)
	public String  name;
	
	@OneToMany
	public Set<PlayerEntity> players=new HashSet<>();
	
	@OneToOne
	public GameDeckEntity gameDeck;
	
	
	@Override
	public ORID getId() {	
		return id;
	};


	public void setId(ORID id) {	
		this.id=id;
	};

	@Override
	public String getBId() {
		return bId;
	}

	public void setBId(String bId) {
		this.bId = bId;
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
