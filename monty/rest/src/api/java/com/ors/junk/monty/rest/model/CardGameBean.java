package com.ors.junk.monty.rest.model;

import java.util.Set;
import java.util.stream.Collectors;

import com.ors.junk.monty.domain.model.CardGame;

public class CardGameBean  implements CardGame {

	public String bId;

	public String  name;
	
	public Set<PlayerBean> players;
	
	public GameDeckBean gameDeck;



	public CardGameBean(CardGame cardGame) {
		this.bId=cardGame.getBId();
		this.name=cardGame.getName();
		this.players=cardGame.getPlayers().stream().map(p->new PlayerBean(p)).collect(Collectors.toSet());
		this.gameDeck=new GameDeckBean(cardGame.getGameDeck());
	}

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
	public Set<PlayerBean> getPlayers() {
		return players;
	}

	public void setPlayers(Set<PlayerBean> players) {
		this.players = players;
	}

	public GameDeckBean getGameDeck() {
		return gameDeck;
	}

	public void setGameDeck(GameDeckBean gameDeck) {
		this.gameDeck = gameDeck;
	}
		
}
