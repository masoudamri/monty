package com.ors.finance.fyaat.servlet.config;

import org.aeonbits.owner.Config;

public interface ServletInfoConfig extends Config  {
	
	public int port();
	public String bindIp();
	public String context();
	public String appName();
	public String servletName();
	public String servletMapping();

}
