package com.ors.junk.monty.rest.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ors.junk.monty.domain.model.CardGame;
import com.ors.junk.monty.domain.model.Player;

public class PlayerBean implements Player{
	

	UUID Id;
	
	String name;
	
	Map<CardGameBean,HandBean> hands=new HashMap<>();
	
	public PlayerBean() {
		
	}
	
	public PlayerBean(Player player){
		this.name=player.getName();
		this.Id=player.getId();
	}
	
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
	@JsonIgnore 
	@SuppressWarnings("unchecked")
	public <T extends CardGame> Set<T> getGames() {
		return (Set<T>) hands.keySet();
	}
	
	@Override
	public HandBean getHand(CardGame game) {
		return hands.get(game);
	}

	@JsonIgnore 
	public Map<CardGameBean, HandBean> getHands() {
		return hands;
	}

	public void setHands(Map<CardGameBean, HandBean> hands) {
		this.hands = hands;
	}

}
