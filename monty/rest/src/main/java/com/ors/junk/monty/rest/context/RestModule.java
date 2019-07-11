package com.ors.junk.monty.rest.context;

import javax.inject.Singleton;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;

import org.aeonbits.owner.ConfigFactory;

import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import com.fasterxml.jackson.module.mrbean.MrBeanModule;
import com.google.inject.AbstractModule;
import com.google.inject.Binding;
import com.google.inject.Provides;
import com.google.inject.TypeLiteral;
import com.google.inject.matcher.AbstractMatcher;
import com.ors.finance.fyaat.rest.config.RestConfig;
import com.ors.finance.fyaat.servlet.context.binding.DefaultPathHandler;
import com.ors.junk.monty.rest.provision.SwaggerUiDeployer;
import com.ors.junk.monty.rest.resource.impl.CardGameResourceImpl;
import com.ors.junk.monty.rest.resource.impl.PlayerResourceImpl;

import io.swagger.v3.jaxrs2.integration.resources.OpenApiResource;
import io.undertow.server.handlers.PathHandler;

public class RestModule extends AbstractModule {

	RestConfig restConfig=ConfigFactory.create(RestConfig.class);

	
	@Override
	protected void configure() {
		bind(CardGameResourceImpl.class);
		bind(PlayerResourceImpl.class);
		bind(OpenApiResource.class);
		bindListener(new AbstractMatcher<Binding<?>>() {
			final TypeLiteral<PathHandler> type = new TypeLiteral<PathHandler>() {
			};

			@Override
			public boolean matches(Binding<?> t) {
				return type.getRawType().isAssignableFrom(t.getKey().getTypeLiteral().getRawType())
						&& t.getKey().getAnnotationType().getName().equals(DefaultPathHandler.class.getName());
			}
		}, new SwaggerUiDeployer(restConfig));
	}
	
	@Provides
	@Singleton
	ObjectMapper objectMapper() {
		ObjectMapper mapper = new ObjectMapper();
		mapper.registerModule(new SimpleModule());
		mapper.registerModule(new MrBeanModule());
		mapper.enable(MapperFeature.ACCEPT_CASE_INSENSITIVE_ENUMS);
		mapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
		mapper.disable(SerializationFeature.FAIL_ON_SELF_REFERENCES);
		return mapper;
	}

	@Provides
	@Singleton
	@Consumes({"application/json", "application/*+json", "text/json"})
	@Produces({"application/json", "application/*+json", "text/json"})
	JacksonJsonProvider jacksonJsonProvider(ObjectMapper mapper) {
		JacksonJsonProvider provider = new JacksonJsonProvider();
		provider.setMapper(mapper);
		return provider;
	}
}
