package com.ors.finance.fyaat.servlet.config;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.Config.Sources;

@Sources("classpath:ServletInfoConfig.properties")
public interface ServletInfoConfig extends Config  {
	
	public int port();
	public String bindIp();
	public String context();
	public String appName();
	public String servletName();
	public String servletMapping();

}
