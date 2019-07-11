package com.ors.junk.monty.rest.provision;

import com.google.inject.spi.ProvisionListener;
import com.ors.finance.fyaat.rest.config.RestConfig;

import io.undertow.Handlers;
import io.undertow.server.handlers.PathHandler;
import io.undertow.server.handlers.resource.ClassPathResourceManager;

public class SwaggerUiDeployer implements ProvisionListener {

  private static final String SWAGGERUI_RESOURCES_BASE = "swaggerui";

  private final RestConfig restConfig;

  public SwaggerUiDeployer(RestConfig restConfig) {
    this.restConfig = restConfig;
  }

  @Override
  public <T> void onProvision(ProvisionInvocation<T> provision) {
    PathHandler pathHandler = (PathHandler) provision.provision();
    pathHandler.addPrefixPath(restConfig.swaggerUiResourcesPrefixPath(), Handlers.resource(
        new ClassPathResourceManager(getClass().getClassLoader(), SWAGGERUI_RESOURCES_BASE)));

  }

}
