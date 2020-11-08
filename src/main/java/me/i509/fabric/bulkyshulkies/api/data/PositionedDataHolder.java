package me.i509.fabric.bulkyshulkies.api.data;

import java.util.Collection;
import java.util.Map;

import me.i509.fabric.bulkyshulkies.api.DataKey;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public interface PositionedDataHolder extends DefaultedDataHolder {
	<V> V getValue(DataKey<V> key, World world, BlockPos pos) throws UnsupportedOperationException;

	Collection<DataKey<?>> getKeys(World world, BlockPos pos);

	Map<DataKey<?>, ?> getValues(World world, BlockPos pos);
}
