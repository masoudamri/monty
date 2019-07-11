package com.ors.finance.fyaat.rest.config;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.Config.Sources;


@Sources("classpath:RestConfig.properties")
public interface RestConfig extends Config {

	public String swaggerUiResourcesPrefixPath();	
}
