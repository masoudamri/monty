package com.ors.junk.monty.persistence.model;

import java.util.UUID;

import com.ors.junk.monty.domain.model.Deck;

public class DeckEntity implements Deck{
	public UUID id;

	@Override
	public UUID getId() {
		return id;
	}
}
