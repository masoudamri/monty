package com.ors.junk.monty.rest.resource.impl;

import java.util.AbstractMap.SimpleEntry;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.inject.Inject;

import com.ors.junk.monty.domain.model.Card;
import com.ors.junk.monty.domain.model.CardGame;
import com.ors.junk.monty.domain.model.Player;
import com.ors.junk.monty.domain.service.CardGameService;
import com.ors.junk.monty.rest.model.CardBean;
import com.ors.junk.monty.rest.model.CardGameBean;
import com.ors.junk.monty.rest.model.DeckTallyBean;
import com.ors.junk.monty.rest.model.PlayerScoresBean;
import com.ors.junk.monty.rest.model.UndealtCardsBean;
import com.ors.junk.monty.rest.resource.CardGameResource;

public class CardGameResourceImpl implements CardGameResource {

	private CardGameService cardGameService;

	@Inject
	public CardGameResourceImpl(CardGameService cardGameService) {
		this.cardGameService = cardGameService;
	}

	@Override
	public CardGame read(String name) {
		return new CardGameBean(cardGameService.get(name));
	}

	@Override
	public CardGame create(String name) {
			return new CardGameBean(cardGameService.create(name));
	}

	@Override
	public void shuffle(String name) {
		cardGameService.shuffle(name);
	}

	@Override
	public void addPlayer(String cardGameName, String playerName) {
		cardGameService.addPlayer(playerName, cardGameName);
	}

	@Override
	public void deal(String cardGameName, String playerName, Integer times) {
		if (times < 1) {
			throw new RuntimeException("must deal a positive number of time!");
		}
		for (int i = 0; i < times; i++) {
			cardGameService.dealCard(playerName, cardGameName);
		}
	}

	@Override
	public void addDeck(String name) {
		cardGameService.addNewDeck(name);
	}

	@Override
	public void deleteCardGame(String name) {
		cardGameService.deleteDeck(name);
	}

	@Override
	public UndealtCardsBean undealt(String name) {
		List<Card> deck = cardGameService.get(name).getGameDeck().getCards();
		UndealtCardsBean undealt = new UndealtCardsBean();
		undealt.getUndealtCards().put(Card.Suite.CLOVERS,0);
		undealt.getUndealtCards().put(Card.Suite.HEARTS,0);
		undealt.getUndealtCards().put(Card.Suite.DIAMONDS,0);
		undealt.getUndealtCards().put(Card.Suite.SPADES,0);
		for (int i = 0; i < deck.size(); i++) {
			Card.Suite suite = deck.get(i).getSuite();
			undealt.getUndealtCards().put(suite, undealt.getUndealtCards().get(suite) + 1);
		}
		return undealt;
	}

	@Override
	public DeckTallyBean deckTally(String name) {
		List<Card> deck = cardGameService.get(name).getGameDeck().getCards();
		DeckTallyBean tally = new DeckTallyBean();
		for (int i = 0; i < deck.size(); i++) {
			CardBean card = new CardBean();
			card.setFace(deck.get(i).getFace());
			card.setSuite(deck.get(i).getSuite());
			if (tally.getTallyMap().containsKey(card)) {
				continue;
			}
			int n = 1;
			for (int j = i+1; j < deck.size(); j++) {
				if (deck.get(j).getFace() == card.getFace() && deck.get(j).getSuite() == card.getSuite())
					n++;
			}
			tally.getTallyMap().put(card, n);
		}
		return tally;
	}

	@Override
	public PlayerScoresBean playerScores(String gameName) {
		PlayerScoresBean scores = new PlayerScoresBean();
		cardGameService.get(gameName).getPlayers().stream().map(p -> playerScore(p, gameName))
				.sorted((Map.Entry.<String, Integer>comparingByValue().reversed()))
				.peek(p->System.out.println(p))
				.collect(Collectors.toMap(Map.Entry::getKey,
						Map.Entry::getValue, (e1, e2) -> e1, scores::getPlayerScores));

		return scores;
	}

	SimpleEntry<String, Integer> playerScore(Player p, String gameName) {
		return new SimpleEntry<String, Integer>(p.getName(), p.getHands().get(gameName).getCards().stream()
				.map(Card::getFace).collect(Collectors.summingInt(Card.Face::getScore)));
	}

}
