package com.ors.finance.fyaat.core.support;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.nio.file.Paths;
import java.util.Optional;

public class UriSupport {

	public static final String CLASSPATH_SCHEME = "classpath";

	public static InputStream open(URI uri) throws MalformedURLException, IOException {
		if (uri.getScheme().equals(CLASSPATH_SCHEME)) {
			return Optional.ofNullable(Thread.currentThread().getContextClassLoader())
					.orElse(UriSupport.class.getClassLoader())
					.getResourceAsStream(uri.toString().substring(CLASSPATH_SCHEME.length()+1));
		}
		return uri.toURL().openStream();
	}
	
	public static String filename(URI uri) {
		if (uri.getScheme().equals(CLASSPATH_SCHEME)) {
			return filename(URI.create(uri.toString().replaceFirst(CLASSPATH_SCHEME+":", "file:/")));
		}
		return Paths.get(uri).getFileName().toString();
	}
}
