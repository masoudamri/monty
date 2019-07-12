package com.ors.junk.monty.persistence.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.orientechnologies.orient.core.id.ORID;

public interface Persistable {
	
	@JsonIgnore
	public ORID getId();

	@JsonIgnore
	public void setId(ORID id);

}
