package me.i509.fabric.bulkyshulkies.api;

import java.util.List;

import me.i509.fabric.bulkyshulkies.BulkyShulkies;

import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;

public final class Magnetism {
	public static final Event<BeforeMagnetismCollection> BEFORE_MAGNETISM_COLLECTION = EventFactory.createArrayBacked(BeforeMagnetismCollection.class, (callbacks) -> ((targettedItems, world, pos, collectorBlockEntity) -> {
		for (BeforeMagnetismCollection callback : callbacks) {
			callback.beforeMagnetismCollection(targettedItems, world, pos, collectorBlockEntity);
		}
	}));

	public static Vec3d getDirectionalBoxOffset(Direction direction) {
		final int size = BulkyShulkies.getInstance().getConfig().getPlatinumMagnetMaxRange() - 1;

		switch (direction) {
		case NORTH:
			return new Vec3d(-size / 2.0D, -size / 2.0D, -size);
		case SOUTH:
			return new Vec3d(-size / 2.0D, -size / 2.0D, 0);
		case WEST:
			return new Vec3d(-size, -size / 2.0D, -size / 2.0D);
		case EAST:
			return new Vec3d(0, -size / 2.0D, -size / 2.0D);
		case DOWN:
			return new Vec3d(-size / 2.0D, -size, -size / 2.0D);
		case UP:
		default:
			return new Vec3d(-size / 2.0D, 0, -size / 2.0D);
		}
	}

	private Magnetism() {
	}

	public interface BeforeMagnetismCollection {
		/**
		 * Fired when a MagneticCollector BlockEntity fires it's magnetism tick.
		 * @param targetedItems The item entities to be collected.
		 * @param pos The BlockPosition of the BlockEntity
		 * @param collectingBlockEntity The blockEntity which engaged this event.
		 * To cancel the pickup of items, remove them from the targettedItems list.
		 */
		void beforeMagnetismCollection(List<ItemEntity> targetedItems, World world, BlockPos pos, BlockEntity collectingBlockEntity);
	}
}
