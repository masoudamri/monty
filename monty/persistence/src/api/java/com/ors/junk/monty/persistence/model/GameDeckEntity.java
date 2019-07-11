package com.ors.junk.monty.persistence.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Id;

import com.orientechnologies.orient.core.id.ORID;
import com.ors.junk.monty.domain.model.GameDeck;

public class GameDeckEntity implements GameDeck, Persistable{
	
	@Id
	ORID orId;
	
	List<CardEntity> cards=new ArrayList<>();
	

	@Override
	public ORID getOrId() {	
		return orId;
	};


	public void setOrId(ORID orId) {	
		this.orId=orId;
	};

	
	@SuppressWarnings("unchecked")
	public List<CardEntity> getCards() {
		return cards;
	}

	public void setCards(List<CardEntity> cards) {
		this.cards = cards;
	}



}
