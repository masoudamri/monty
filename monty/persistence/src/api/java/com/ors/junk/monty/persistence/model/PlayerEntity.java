package com.ors.junk.monty.persistence.model;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.orientechnologies.orient.core.id.ORID;
import com.ors.junk.monty.domain.model.Hand;
import com.ors.junk.monty.domain.model.Player;

@Entity
public class PlayerEntity implements Player, Persistable{

	@Id
	ORID id;
	
	@Column(unique=true)
	String bId=UUID.randomUUID().toString();
	
	@Column(unique=true)
	String name;
	
	@OneToOne
	Map<String, Hand> hands=new HashMap<>();
	
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
	}


	@Override
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name=name;
	}
	

	
	public Map<String, Hand> getHands() {
		return hands;
	}

	public void setHands(Map<String, Hand> hands) {
		this.hands = hands;
	}

}
