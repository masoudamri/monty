package com.ors.junk.monty.rest;

import java.util.Arrays;

import org.embulk.guice.Bootstrap;

import com.google.inject.Module;

public class RunModules {

	public static void main(String[] args) {
		Module[] mods=Arrays.stream(args).map(RunModules::retrieveClass).map(RunModules::newInstance)
		.toArray(Module[]::new);
		new Bootstrap(mods).requireExplicitBindings(false).initialize();
	}

	private static Module newInstance(Class<Module> c) {
		try {
			return c.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			throw new RuntimeException(e);
		}
	}

	@SuppressWarnings("unchecked")
	private static Class<Module> retrieveClass(String className) {
		try {
			return (Class<Module>)Class.forName(className);
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
	}

}
