package com.ors.junk.monty.servlet.context;

import com.google.inject.persist.PersistFilter;
import com.google.inject.persist.jpa.JpaPersistModule;
import com.google.inject.servlet.ServletModule;

public class GuiceFilterModule extends ServletModule{

    @Override
    protected void configureServlets() {
		install(new JpaPersistModule("montyJpaUnit"));
    	filter("/*").through(PersistFilter.class);
   }
    

}
