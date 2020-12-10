/*
 * MIT License
 *
 * Copyright (c) 2019, 2020 i509VCB
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package me.i509.bulkyshulkies.api;

import java.util.List;

import me.i509.bulkyshulkies.mod.BulkyShulkiesImpl;
import me.i509.bulkyshulkies.mod.Uninstantiable;

import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;

/**
 * Utilities related to magnetism in some shulker boxes.
 */
public final class Magnetism {
	public static final Event<BeforeMagnetismCollection> BEFORE_MAGNETISM_COLLECTION = EventFactory.createArrayBacked(BeforeMagnetismCollection.class, (callbacks) -> ((targetedItems, world, pos, collectorBlockEntity) -> {
		for (BeforeMagnetismCollection callback : callbacks) {
			callback.beforeMagnetismCollection(targetedItems, world, pos, collectorBlockEntity);
		}
	}));

	public static Vec3d getDirectionalBoxOffset(Direction direction) {
		final int size = BulkyShulkiesImpl.getInstance().getConfig().getPlatinumMagnetMaxRange() - 1;

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
		Uninstantiable.whyDoIHearBossMusic(Magnetism.class);
	}

	@FunctionalInterface
	public interface BeforeMagnetismCollection {
		/**
		 * Called when a magnetic collector block entity tries to pick up items.
		 *
		 * <p>To cancel the pickup of items, remove them from the targetedItems list.
		 *
		 * @param targetedItems The item entities to be collected.
		 * @param pos The BlockPosition of the BlockEntity
		 * @param collectingBlockEntity The blockEntity which engaged this event.
		 */
		void beforeMagnetismCollection(List<ItemEntity> targetedItems, World world, BlockPos pos, BlockEntity collectingBlockEntity);
	}
}
