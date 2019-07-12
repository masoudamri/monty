package com.ors.junk.monty.persistence.model;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import com.orientechnologies.orient.core.id.ORID;
import com.ors.junk.monty.domain.model.Deck;

@Entity
public class DeckEntity implements Deck, Persistable{
	
	@Id
	ORID id;
	
	@Column(unique=true)
	public String bId=UUID.randomUUID().toString();
	
	@Override
	public ORID getId() {	
		return id;
	};


	public void setId(ORID id) {	
		this.id=id;
	};

	
	@Override
	public String getBId() {
		return bId;
	}
	
	public void setBId(String bId) {	
		this.bId=bId;
	};

}
