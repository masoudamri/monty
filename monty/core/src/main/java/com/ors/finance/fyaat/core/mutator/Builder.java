package com.ors.finance.fyaat.core.mutator;

import java.util.function.BiConsumer;
import java.util.function.Function;

public class Builder<T,S extends Mutations<T>> {

	private Function<Mutator<T, S >, T> constructor;
	private Mutator<T, S > mutator;

	Builder(Function<Mutator<T, S >, T>  constructor) {
		this.mutator = new Mutator<T,S>();
		this.constructor = constructor;
	}

	public <U> Builder<T, S> andThen(BiConsumer<S,U> operation, U argument) {
		this.mutator.andThen(operation, argument);
		return this;
	}

	public T build() {
		return constructor.apply(mutator);
	}

	public static <U, V extends Mutations<U>> Builder<U,V> of(Function<Mutator<U,V>,U> constructor){
		return new Builder<U, V>(constructor);
	}

}
