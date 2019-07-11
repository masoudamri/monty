package com.ors.junk.monty.persistence.model;

import java.util.UUID;

import javax.persistence.Id;

import com.orientechnologies.orient.core.id.ORID;
import com.ors.junk.monty.domain.model.Deck;

public class DeckEntity implements Deck, Persistable{
	
	@Id
	ORID orId;
	
	public UUID id=UUID.randomUUID();

	@Override
	public ORID getOrId() {	
		return orId;
	};


	public void setOrId(ORID orId) {	
		this.orId=orId;
	};

	
	@Override
	public UUID getId() {
		return id;
	}
}
