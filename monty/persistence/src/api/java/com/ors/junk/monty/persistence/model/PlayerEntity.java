package com.ors.junk.monty.persistence.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import com.orientechnologies.orient.core.id.ORID;
import com.ors.junk.monty.domain.model.CardGame;
import com.ors.junk.monty.domain.model.Player;

import javax.persistence.Column;
import javax.persistence.Id;

public class PlayerEntity implements Player, Persistable{
	

	@Id
	ORID orId;
	
	@Column(unique=true)
	UUID Id=UUID.randomUUID();
	
	@Column(unique=true)
	String name;
	
	Map<CardGameEntity,HandEntity> hands=new HashMap<>();


	@Override
	public UUID getId() {
		return Id;
	}
	
	public void setId(UUID Id) {
		this.Id=Id;
	}


	@Override
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name=name;
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public <T extends CardGame> Set<T> getGames() {
		return (Set<T>) hands.keySet();
	}

	public HandEntity newHand(CardGameEntity game) {
		if(hands.get(game)!=null) {
			throw new RuntimeException("Player already in this game!");
		}
		hands.put(game, new HandEntity());
		return hands.get(game);
	}

	
	@Override
	public HandEntity getHand(CardGame game) {
		return hands.get(game);
	}

	public Map<CardGameEntity, HandEntity> getHands() {
		return hands;
	}

	public void setHands(Map<CardGameEntity, HandEntity> hands) {
		this.hands = hands;
	}

	@Override
	public ORID getOrId() {	
		return orId;
	};


	public void setOrId(ORID orId) {	
		this.orId=orId;
	};

}
