package com.ors.junk.monty.domain.context;

import com.google.inject.AbstractModule;
import com.ors.junk.monty.domain.service.CardGameService;
import com.ors.junk.monty.domain.service.PermuteService;
import com.ors.junk.monty.domain.service.PlayerService;
import com.ors.junk.monty.domain.service.impl.CardGameServiceImpl;
import com.ors.junk.monty.domain.service.impl.PermuteServiceImpl;
import com.ors.junk.monty.domain.service.impl.PlayerServiceImpl;

public class DomainModule extends AbstractModule{
	
	@Override
	protected void configure() {
		bind(CardGameService.class).to(CardGameServiceImpl.class);
		bind(PlayerService.class).to(PlayerServiceImpl.class);
		bind(PermuteService.class).to(PermuteServiceImpl.class);
	}

}
