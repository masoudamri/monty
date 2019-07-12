package com.ors.junk.monty.servlet.context;

import javax.inject.Singleton;

import org.aeonbits.owner.ConfigFactory;
import org.jboss.resteasy.plugins.guice.GuiceResteasyBootstrapServletContextListener;
import org.jboss.resteasy.plugins.guice.ext.JaxrsModule;
import org.jboss.resteasy.plugins.server.servlet.HttpServletDispatcher;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.servlet.GuiceFilter;
import com.ors.finance.fyaat.servlet.config.ServletInfoConfig;
import com.ors.finance.fyaat.servlet.context.binding.DefaultPathHandler;
import com.ors.junk.monty.servlet.undertow.UndertowRunner;

import io.undertow.Handlers;
import io.undertow.Undertow;
import io.undertow.server.handlers.PathHandler;
import io.undertow.server.handlers.error.SimpleErrorPageHandler;
import io.undertow.servlet.Servlets;
import io.undertow.servlet.api.DeploymentInfo;
import io.undertow.servlet.api.DeploymentManager;
import io.undertow.servlet.api.InstanceFactory;
import io.undertow.servlet.api.InstanceHandle;
import io.undertow.servlet.api.ListenerInfo;
import io.undertow.servlet.util.ImmediateInstanceHandle;

public class UndertowModule extends AbstractModule {
	
	ServletInfoConfig servletConfig=ConfigFactory.create(ServletInfoConfig.class);
	
	@Override
	protected void configure() {
		bind(UndertowRunner.class).asEagerSingleton();
		install(new JaxrsModule());
		install(new GuiceFilterModule());
	}

	@Provides
	@Singleton
	DeploymentInfo deploymentInfo(GuiceResteasyBootstrapServletContextListener listener) {
		return Servlets.deployment().setClassLoader(UndertowRunner.class.getClassLoader())
				.setContextPath(servletConfig.context()).setDeploymentName(servletConfig.appName())
				.addFilter(Servlets.filter(GuiceFilter.class))
				.addServlets(Servlets.servlet(servletConfig.servletName(), HttpServletDispatcher.class)
						.addMapping(servletConfig.servletMapping()))
				.addListener(new ListenerInfo(GuiceResteasyBootstrapServletContextListener.class,
						new InstanceFactory<GuiceResteasyBootstrapServletContextListener>() {

							@Override
							public InstanceHandle<GuiceResteasyBootstrapServletContextListener> createInstance()
									throws InstantiationException {
								return new ImmediateInstanceHandle<>(listener);
							}

						}));
	}
	
	
	@Provides
	@Singleton
	DeploymentManager manager(DeploymentInfo deploymentInfo) {
		return Servlets.defaultContainer().addDeployment(deploymentInfo);
	}

	@Provides
	@Singleton
	Undertow.Builder server() {
		return Undertow.builder().addHttpListener(servletConfig.port(), servletConfig.bindIp());
	}
	
	@Provides
	@Singleton
	@DefaultPathHandler
	PathHandler defaultPathHandler() {
		return Handlers.path(new SimpleErrorPageHandler());
	}

}
