package com.ors.finance.fyaat.core.mutator;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class  Mutator<T,M extends Mutations<T>>  {
	
	Consumer<M> modifier = m->{};
	
	
	public <U> Mutator<T,M> andThen(BiConsumer<M,U> operation, U argument){
		modifier = modifier.andThen(m -> operation.accept(m, argument));
		return this;
	}

	public <U> Mutator<T,M> andIfThen(boolean condition, BiConsumer<M,U> operation, U argument){
		if(condition) {
			modifier = modifier.andThen(m -> operation.accept(m, argument));
		}
		return this;
	}

	public 	Consumer<M> build(){
		return modifier;
	};
	
	public static <U,N extends Mutations<U>> Mutator<U,N> of() {
		return new Mutator<>();
	}
	
	public static <U,N extends Mutations<U>> Mutator<U,N> of(Class<? extends N> clazz) {
		return new Mutator<>();
	}
}
