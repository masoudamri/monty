package com.ors.junk.monty.rest.model;

import java.util.LinkedHashMap;

public class PlayerScoresBean {

	
	LinkedHashMap<String,Integer> playerScores=new LinkedHashMap<>();

	public LinkedHashMap<String, Integer> getPlayerScores() {
		return playerScores;
	}

	public void setPlayerScores(LinkedHashMap<String, Integer> playerScores) {
		this.playerScores = playerScores;
	}
}
