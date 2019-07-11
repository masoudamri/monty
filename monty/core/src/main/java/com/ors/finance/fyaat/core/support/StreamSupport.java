package com.ors.finance.fyaat.core.support;

import java.util.Arrays;
import java.util.function.Function;
import java.util.stream.Stream;

public class StreamSupport {
	
	@SafeVarargs
	public static <S> Stream<S> concat(Stream<S>... streams){
		return Arrays.stream(streams).flatMap(Function.identity());
	}

}
