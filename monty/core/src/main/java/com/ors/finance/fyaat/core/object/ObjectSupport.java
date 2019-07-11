package com.ors.finance.fyaat.core.object;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.AbstractMap.SimpleEntry;
import java.util.Arrays;
import java.util.List;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.ors.finance.fyaat.core.support.IntrospectionSupport;

public interface ObjectSupport<T> {

	static class Factory<T> {

		public static <T> ObjectSupport<T> introspect(Class<T> objectType) {
			return introspect(objectType, objectType);
		}

		public static <T> ObjectSupport<T> introspect(Class<? super T> introspectType, Class<T> objectType) {
			List<Entry<String, Method>> props;
			props = IntrospectionSupport.propertyDescriptors(introspectType)
					.map(pd -> new SimpleEntry<String, Method>(pd.getName(), pd.getReadMethod()))
					.filter(e -> !"class".equals(e.getKey()) && e.getValue() != null).sequential()
					.collect(Collectors.<Entry<String, Method>, String, Method>toMap(pd -> pd.getKey(),
							pd -> pd.getValue(), (a, b) -> b))
					.entrySet().stream().sorted((e1, e2) -> e1.getKey().compareTo(e2.getKey()))
					.collect(Collectors.toList());

			@SuppressWarnings("unchecked")
			Function<Object, Object>[] accesors = props.stream().map(Entry::getValue).map(Factory::methodToFunction)
					.toArray(Function[]::new);

			Function<Object, Object[]> characterizer = o -> {
				return Arrays.stream(accesors).map(f -> f.apply(o)).toArray();
			};

			String[] labels = props.stream().map(Entry::getKey).toArray(String[]::new);

			return create(labels, characterizer, objectType);
		}

		@SuppressWarnings("unchecked")
		public static <T> ObjectSupport<T> fromLabelledCharacterizer(Object[] labelledCharactrizer,
				Class<T> objectType) {
			if (labelledCharactrizer.length % 2 == 1) {
				throw new RuntimeException(
						"Format of labelled characterizer should be {label1,accessor1,label2,accessor2...}");
			}
			String[] labels = new String[labelledCharactrizer.length / 2];
			Function<Object, Object>[] accesors = new Function[labelledCharactrizer.length / 2];

			for (int i = 0; i < labelledCharactrizer.length; i += 2) {
				labels[i] = (String) labelledCharactrizer[i];
				accesors[i] = (Function<Object, Object>) labelledCharactrizer[i + 1];
			}

			Function<Object, Object[]> characterizer = t -> {
				Object o[] = new Object[accesors.length];
				for (int i = 0; i < o.length; i++) {
					o[i] = accesors[i].apply(t);
				}
				return o;
			};
			return create(labels, characterizer, objectType);
		}

		private static <T> ObjectSupport<T> create(String[] labels, Function<Object, Object[]> characterizer,
				Class<T> objectType) {
			return new ObjectSupport<T>() {

				@Override
				public int hashCode(T object) {
					return Objects.hash(characterizer.apply(object));
				}

				@Override
				@SuppressWarnings("unchecked")
				public boolean equals(T object, Object other) {
					if (object == null || other == null) {
						return object == other; // true iff both are null
					} else if (!objectType.getClass().isAssignableFrom(other.getClass()) || !doCanEquals(object, other)
							|| !doCanEquals(other, object)) {
						return false;
					}
					return Arrays.equals(characterizer.apply(object), characterizer.apply((T) other));
				}

				@Override
				public String toString(T object) {
					StringBuilder sb = new StringBuilder(String.format("{ %s: {", objectType.getName()));
					Object[] fields = characterizer.apply(object);
					for (int i = 0; i < fields.length; i++) {
						sb.append(String.format(" %s: %s,", labels[i], fields[i]));
					}
					sb.setCharAt(sb.length() - 1, '}');
					sb.append('}');
					return sb.toString();
				}
			};
		}

		private static boolean doCanEquals(Object object, Object other) {
			if (object instanceof HasCanEquals) {
				return ((HasCanEquals) object).canEquals(other.getClass());
			} else {
				return object.getClass().isAssignableFrom(other.getClass());
			}
		}

		private static Function<Object, Object> methodToFunction(Method m) {
			return o -> {
				Object result;
				try {
					result = m.invoke(o);
				} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
					throw new RuntimeException(e);
				}
				return result;
			};
		}

	}

	public int hashCode(T object);

	public boolean equals(T object, Object other);

	public String toString(T object);
}
