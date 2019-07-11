package com.ors.finance.fyaat.core.mutator;

import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.Function;

public class ComplexMutator<T, M extends Mutations<T>> {

	BiConsumer<T, M> modifier = (t, m) -> {
	};

	public <U> ComplexMutator<T, M> andThen(BiConsumer<M, U> operation, U argument) {
		return andThen(t -> operation, argument);
	}

	public <U> ComplexMutator<T, M> andThen(Function<T, BiConsumer<M, U>> operation, U argument) {
		modifier = modifier.andThen((t, m) -> operation.apply(t).accept(m, argument));
		return this;
	}

	public <U> ComplexMutator<T, M> andIfThen(boolean condition, BiConsumer<M, U> operation, U argument) {
		return andIfThen(condition, t -> operation, argument);
	}

	public <U> ComplexMutator<T, M> andIfThen(boolean condition, Function<T, BiConsumer<M, U>> operation, U argument) {
		if (condition) {
			modifier = modifier.andThen((t, m) -> operation.apply(t).accept(m, argument));
		}
		return this;
	}

	public <U> ComplexMutator<T, M> andIfDifferent(Function<T, U> accessor, BiConsumer<M, U> operation, U argument) {
		return andIfThen(t -> Objects.equals(accessor.apply(t), argument), t -> operation, argument);
	}

	public <U> ComplexMutator<T, M> andIfDifferent(boolean condition,Function<T, U> accessor, BiConsumer<M, U> operation, U argument) {
		if(condition) {
			return andIfThen(t -> Objects.equals(accessor.apply(t), argument), t -> operation, argument);
		}
		return this;
	}

	public <U> ComplexMutator<T, M> andIfDifferent(Function<T, Boolean> condition,Function<T, U> accessor, BiConsumer<M, U> operation, U argument) {
		return andIfThen(t -> Objects.equals(accessor.apply(t), argument)&&condition.apply(t), t -> operation, argument);
	}

	public <U> ComplexMutator<T, M> andIfThen(Function<T, Boolean> condition, BiConsumer<M, U> operation, U argument) {
		return andIfThen(condition, t -> operation, argument);
	}

	public <U> ComplexMutator<T, M> andIfThen(Function<T, Boolean> condition, Function<T, BiConsumer<M, U>> operation,
			U argument) {
		modifier = modifier.andThen((t, m) -> {
			if (condition.apply(t))
				operation.apply(t).accept(m, argument);
		});
		return this;
	}

	public BiConsumer<T, M> build() {
		return modifier;
	};

	public static <U, N extends Mutations<U>> ComplexMutator<U, N> of() {
		return new ComplexMutator<>();
	}

	public static <U, N extends Mutations<U>> ComplexMutator<U, N> of(Class<? extends N> clazz) {
		return new ComplexMutator<>();
	}
}
