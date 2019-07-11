package com.ors.finance.fyaat.servlet.undertow;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Inject;
import javax.servlet.ServletException;

import com.ors.finance.fyaat.servlet.config.ServletInfoConfig;
import com.ors.finance.fyaat.servlet.context.binding.DefaultPathHandler;

import io.undertow.Undertow;
import io.undertow.server.handlers.PathHandler;
import io.undertow.servlet.api.DeploymentManager;

public class UndertowRunner {

	private final Undertow.Builder undertow;
	private final PathHandler pathHandler;
	private final ServletInfoConfig servletInfoConfig;
	private final DeploymentManager manager;
	private Undertow server;

	@Inject
	public UndertowRunner(@DefaultPathHandler PathHandler pathHandler,
			ServletInfoConfig servletInfoConfig, DeploymentManager manager, Undertow.Builder undertow) {
		this.pathHandler=pathHandler;
		this.servletInfoConfig=servletInfoConfig;
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
