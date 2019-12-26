/*
 * MIT License
 *
 * Copyright (c) 2019 i509VCB
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
