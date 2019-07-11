package com.ors.junk.monty.rest.model;

import java.util.UUID;

import com.ors.junk.monty.domain.model.Deck;

public class DeckBean implements Deck{
	public UUID id;

	
	@Override
	public UUID getId() {
		return id;
	}
}
