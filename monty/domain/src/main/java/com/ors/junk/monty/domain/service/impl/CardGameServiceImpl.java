package com.ors.junk.monty.domain.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import com.ors.junk.monty.domain.model.Card;
import com.ors.junk.monty.domain.model.CardGame;
import com.ors.junk.monty.domain.model.GameDeck;
import com.ors.junk.monty.domain.model.Player;
import com.ors.junk.monty.domain.service.CardGameService;
import com.ors.junk.monty.domain.service.PermuteService;

public class CardGameServiceImpl implements CardGameService{
	
	private PermuteService permuteService;


	@Inject
	CardGameServiceImpl(PermuteService permuteService){
		this.permuteService=permuteService;
	}

	@Override
	public CardGame create(String name) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void shuffle(CardGame cardGame) {
		GameDeck deck=cardGame.getGameDeck();
		int size=deck.getCards().size();
		if(size<=1) {
			return;
		}
		List<Integer> permutation=permuteService.permute(size-1);
		List<Card> cards=new ArrayList<>(size);
		for(int i=0;i<permutation.size();i++) {
			cards.set(i, deck.getCards().get(permutation.get(i)));
		}
	}


	@Override
	public CardGame get(long id) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void addPlayer(Player player, CardGame cardGame) {
		player.getGames().add(cardGame);
		cardGame.getPlayers().add(player);
	}


	@Override	
	public void dealCard(Player player, CardGame cardGame) {
		int size=cardGame.getGameDeck().getCards().size();
		if(size==0) {
			throw new RuntimeException("No more cards!");
		}
		player.getHand(cardGame).getCards().add(cardGame.getGameDeck().getCards().remove(size-1));
	}

}