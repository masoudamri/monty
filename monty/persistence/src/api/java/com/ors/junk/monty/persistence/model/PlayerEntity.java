package com.ors.junk.monty.persistence.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.persistence.Id;

import com.orientechnologies.orient.core.id.ORID;
import com.ors.junk.monty.domain.model.CardGame;
import com.ors.junk.monty.domain.model.Player;

public class PlayerEntity implements Player, Persistable{
	

	@Id
	ORID orId;
	
	UUID Id=UUID.randomUUID();
	
	String name;
	
	Map<CardGameEntity,HandEntity> hands=new HashMap<>();

	@Override
	public ORID getOrId() {	
		return orId;
	};


	public void setOrId(ORID orId) {	
		this.orId=orId;
	};

	
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

}
