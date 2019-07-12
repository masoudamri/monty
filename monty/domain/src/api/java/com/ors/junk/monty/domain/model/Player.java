package com.ors.junk.monty.domain.model;

import java.util.Map;

public interface Player {

	public String getBId();

	public String getName();

	public Map<String, Hand> getHands();

}
