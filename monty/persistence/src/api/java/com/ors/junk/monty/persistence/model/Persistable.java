package com.ors.junk.monty.persistence.model;

import com.orientechnologies.orient.core.id.ORID;

public interface Persistable {
	
	public ORID getOrId();

	public void setOrId(ORID orId);

}
