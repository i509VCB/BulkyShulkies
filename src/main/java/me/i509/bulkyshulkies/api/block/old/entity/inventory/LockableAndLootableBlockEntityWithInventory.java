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

package me.i509.bulkyshulkies.api.block.old.entity.inventory;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;

import me.i509.bulkyshulkies.api.inventory.ForwardingInventory;
import me.i509.bulkyshulkies.api.inventory.SerializableInventory;

public interface LockableAndLootableBlockEntityWithInventory extends ForwardingInventory, LockableAndLootableBlockEntity, SerializableInventory {
	@Override
	default boolean isEmpty() {
		this.checkLootInteraction(null);
		return ForwardingInventory.super.isEmpty();
	}

	@Override
	default ItemStack getStack(int slot) {
		this.checkLootInteraction(null);
		return ForwardingInventory.super.getStack(slot);
	}

	@Override
	default ItemStack removeStack(int slot, int amount) {
		this.checkLootInteraction(null);
		return ForwardingInventory.super.removeStack(slot, amount);
	}

	@Override
	default ItemStack removeStack(int slot) {
		this.checkLootInteraction(null);
		return ForwardingInventory.super.removeStack(slot);
	}

	@Override
	default void setStack(int slot, ItemStack stack) {
		this.checkLootInteraction(null);
		ForwardingInventory.super.setStack(slot, stack);
	}

	default void lootTableOrInventoryFromTag(CompoundTag tag) {
		this.lockFromTag(tag);

		if (!this.lootTableFromTag(tag)) {
			this.inventoryFromTag(tag);
		}
	}

	default CompoundTag lootTableOrInventoryToTag(CompoundTag tag) {
		this.lockToTag(tag);

		if (!this.lootTableToTag(tag)) {
			return this.inventoryToTag(tag);
		}

		return tag;
	}
}
