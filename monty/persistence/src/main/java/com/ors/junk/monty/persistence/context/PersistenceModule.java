package com.ors.junk.monty.persistence.context;

import com.google.inject.AbstractModule;
import com.ors.junk.monty.persistence.service.PersistenceService;
import com.ors.junk.monty.persistence.service.impl.PersistenceServiceImpl;

public class PersistenceModule extends AbstractModule{
	
	@Override
	protected void configure() {
		bind(PersistenceService.class).to(PersistenceServiceImpl.class);
	}

}
