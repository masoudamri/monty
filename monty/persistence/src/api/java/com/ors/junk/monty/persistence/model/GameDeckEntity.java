package com.ors.junk.monty.persistence.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.orientechnologies.orient.core.id.ORID;
import com.ors.junk.monty.domain.model.GameDeck;

@Entity
public class GameDeckEntity implements GameDeck, Persistable{
	
	@Id
	ORID id;
	
	@OneToMany
	List<CardEntity> cards=new ArrayList<>();

	@Override
	public ORID getId() {	
		return id;
	};


	public void setId(ORID id) {	
		this.id=id;
	};

	
	@SuppressWarnings("unchecked")
	public List<CardEntity> getCards() {
		return cards;
	}

	public void setCards(List<CardEntity> cards) {
		this.cards = cards;
	}



}
