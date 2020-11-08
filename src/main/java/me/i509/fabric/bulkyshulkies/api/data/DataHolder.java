package me.i509.fabric.bulkyshulkies.api.data;

import java.util.Collection;
import java.util.Map;

import me.i509.fabric.bulkyshulkies.api.DataKey;

public interface DataHolder {
	<V> V getValue(DataKey<V> key) throws UnsupportedOperationException;

	<V> void setValue(DataKey<V> key, V value) throws UnsupportedOperationException;

	Collection<DataKey<?>> getKeys();

	Map<DataKey<?>, ?> getValues();

	boolean supports(DataKey<?> key);
}
