package me.i509.fabric.bulkyshulkies.api.event;

import java.util.List;

import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;

public interface MagnetismCollectionCallback {
	Event<MagnetismCollectionCallback> EVENT = EventFactory.createArrayBacked(MagnetismCollectionCallback.class, (callbacks) -> ((targettedItems, world, pos, collectorBlockEntity) -> {
		for (MagnetismCollectionCallback callback : callbacks) {
			callback.onMagnetismTick(targettedItems, world, pos, collectorBlockEntity);
		}
	}));

	/**
	 * Fired when a MagneticCollector BlockEntity fires it's magnetism tick.
	 * @param targettedItems The item entities to be collected.
	 * @param pos The BlockPosition of the BlockEntity
	 * @param collectorBlockEntity The BlockEntity which engaged this event.
	 * To cancel the pickup of items, remove them from the targettedItems list.
	 */
	void onMagnetismTick(List<ItemEntity> targettedItems, World world, BlockPos pos, BlockEntity collectorBlockEntity);
}
