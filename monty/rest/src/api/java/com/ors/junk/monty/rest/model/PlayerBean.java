package com.ors.junk.monty.rest.model;

import java.util.HashMap;
import java.util.Map;

import com.ors.junk.monty.domain.model.Hand;
import com.ors.junk.monty.domain.model.Player;

public class PlayerBean implements Player{
	

	String bId;
	
	String name;
	

	Map<String, Hand> hands;
	
	public PlayerBean() {
		
	}
	
	public PlayerBean(Player player){
		this.name=player.getName();
		this.bId=player.getBId();
		this.hands=new HashMap<>();
		for(String game:player.getHands().keySet()) {
			hands.put(game, new HandBean(player.getHands().get(game)));
		}
	}
	
	@Override
	public String getBId() {
		return bId;
	}
	
	public void setId(String bId) {
		this.bId=bId;
	}


	@Override
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name=name;
	}


	@Override
	public Map<String,  Hand> getHands() {
		return hands;
	}

	public void setName(Map<String, Hand> hands) {
		this.hands=hands;
	}
}
