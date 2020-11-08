/*
 * MIT License
 *
 * Copyright (c) 2019-2020 i509VCB
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

package me.i509.fabric.bulkyshulkies.api.block.old.entity.inventory;

import java.util.Iterator;
import java.util.stream.IntStream;

import com.google.common.base.Preconditions;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.block.entity.LootableContainerBlockEntity;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.SidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.Direction;

public abstract class AbstractSidedInventoryBlockEntity extends LootableContainerBlockEntity implements SidedInventory {
	protected final int[] availableSlots;
	protected DefaultedList<ItemStack> inventory;

	protected AbstractSidedInventoryBlockEntity(BlockEntityType<? extends AbstractSidedInventoryBlockEntity> type, int slots) {
		super(type, null, null);
		Preconditions.checkArgument(slots > 0, "Maximum available slots cannot be less than 1");
		this.availableSlots = IntStream.range(0, slots).toArray();
		this.inventory = DefaultedList.ofSize(this.availableSlots.length, ItemStack.EMPTY);
	}

	// BlockEntity methods

	@Override
	public void fromTag(CompoundTag input) {
		super.fromTag(input);
		this.deserializeInventory(input);
	}

	@Override
	public CompoundTag toTag(CompoundTag output) {
		super.toTag(output);
		return this.serializeInventory(output);
	}

	public void deserializeInventory(CompoundTag tag) {
		this.inventory = DefaultedList.ofSize(this.size(), ItemStack.EMPTY);

		if (!this.deserializeLootTable(tag) && tag.contains("Items", 9)) {
			Inventories.fromTag(tag, this.inventory);
		}
	}

	public CompoundTag serializeInventory(CompoundTag output) {
		if (!this.serializeLootTable(output)) {
			Inventories.toTag(output, this.inventory, false);
		}

		return output;
	}

	// Inventory methods

	@Override
	public int size() {
		return this.inventory.size();
	}

	@Override
	public boolean isEmpty() {
		Iterator<ItemStack> stackIterator = this.inventory.iterator();

		ItemStack currentStack;

		do {
			if (!stackIterator.hasNext()) {
				return true;
			}

			currentStack = stackIterator.next();
		} while (currentStack.isEmpty());

		return false;
	}

	@Override
	protected DefaultedList<ItemStack> getInvStackList() {
		return this.inventory;
	}

	@Override
	protected void setInvStackList(DefaultedList<ItemStack> list) {
		this.inventory = list;
	}

	@Override
	public int[] getAvailableSlots(Direction direction) {
		return this.availableSlots;
	}
}
