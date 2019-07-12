package com.ors.junk.monty.servlet.undertow;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Inject;
import javax.servlet.ServletException;

import org.aeonbits.owner.ConfigFactory;

import com.ors.finance.fyaat.servlet.config.ServletInfoConfig;
import com.ors.finance.fyaat.servlet.context.binding.DefaultPathHandler;

import io.undertow.Undertow;
import io.undertow.server.handlers.PathHandler;
import io.undertow.servlet.api.DeploymentManager;

public class UndertowRunner {

	private final ServletInfoConfig servletInfoConfig=ConfigFactory.create(ServletInfoConfig.class);
	
	private final Undertow.Builder undertow;
	private final PathHandler pathHandler;
	private final DeploymentManager manager;
	private Undertow server;

	@Inject
	public UndertowRunner(@DefaultPathHandler PathHandler pathHandler,
			 DeploymentManager manager, Undertow.Builder undertow) {
		this.pathHandler=pathHandler;
		this.manager = manager;
		this.undertow = undertow;
	}

	@PostConstruct
	public void start() throws ServletException, InterruptedException {

		manager.deploy();

		server = undertow.setHandler(pathHandler.addPrefixPath(servletInfoConfig.context(), manager.start())).build();

		server.start();
	}

	@PreDestroy
	public void stop() {
		server.stop();
	}

}
