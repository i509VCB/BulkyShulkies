package me.i509.fabric.bulkyshulkies.api.data;

import java.util.Collection;
import java.util.Map;

import me.i509.fabric.bulkyshulkies.api.DataKey;

public interface DefaultedDataHolder {
	<V> V getDefaultValue(DataKey<V> key) throws UnsupportedOperationException;

	Collection<DataKey<?>> getDefaultKeys();

	Map<DataKey<?>, ?> getDefaultValues();

	boolean supports(DataKey<?> key);
}
