package com.ors.junk.monty.persistence.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.orientechnologies.orient.core.id.ORID;
import com.ors.junk.monty.domain.model.Hand;

@Entity
public class HandEntity implements Hand, Persistable {

	
	@Id
	ORID id;
	
	@ManyToOne
	PlayerEntity player;

	@OneToMany
	Set<CardEntity> cards=new HashSet<>();

	
	@Override
	public ORID getId() {	
		return id;
	};


	public void setId(ORID id) {	
		this.id=id;
	};

	
	public PlayerEntity getPlayer() {
		return player;
	}

	public void setPlayer(PlayerEntity player) {
		this.player = player;
	}

	@SuppressWarnings("unchecked")
	public Set<CardEntity> getCards() {
		return cards;
	}

	public void setCards(Set<CardEntity> cards) {
		this.cards = cards;
	}

}
