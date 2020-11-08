package me.i509.fabric.bulkyshulkies.api;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public final class DataKey<V> {
	private final String key;
	private final Class<V> type;
	private final List<V> supportedValues;
	private final Function<String, Optional<V>> parser;

	private DataKey(String key, Class<V> type, List<V> supportedValues, Function<String, Optional<V>> parser) {
		this.key = key;
		this.type = type;
		// Copy original map and then make unmodifiable copy
		this.supportedValues = Collections.unmodifiableList(new ArrayList<>(supportedValues));
		this.parser = parser;
	}

	public static <T> DataKey<T> create(String key, Class<T> type, T[] values, Function<String, Optional<T>> parser) {
		return create(key, type, Arrays.asList(values), parser);
	}

	public static <T> DataKey<T> create(String key, Class<T> type, List<T> values, Function<String, Optional<T>> parser) {
		return new DataKey<>(key, type, values, parser);
	}

	public String getKey() {
		return this.key;
	}

	public boolean supports(Object value) {
		if (value.getClass().isAssignableFrom(this.type)) {
			return this.supportedValues.contains(this.type.cast(value));
		}

		throw new ClassCastException("Invalid class type checked!"); // TODO: Better message
	}

	public List<V> getSupportedValues() {
		return this.supportedValues;
	}

	public Optional<V> parse(String string) {
		return this.parser.apply(string);
	}
}
