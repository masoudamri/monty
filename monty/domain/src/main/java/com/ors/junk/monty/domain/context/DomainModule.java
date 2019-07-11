package com.ors.junk.monty.domain.context;

import com.google.inject.AbstractModule;
import com.ors.junk.monty.domain.service.CardGameService;
import com.ors.junk.monty.domain.service.impl.CardGameServiceImpl;

public class DomainModule extends AbstractModule{
	
	@Override
	protected void configure() {
		bind(CardGameService.class).to(CardGameServiceImpl.class);
	}

}
