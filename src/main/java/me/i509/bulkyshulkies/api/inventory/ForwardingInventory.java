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

package me.i509.bulkyshulkies.api.inventory;

import java.util.Set;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public interface ForwardingInventory extends Inventory {
	Inventory getInventory();

	// INVENTORY METHODS

	@Override
	default int size() {
		return this.getInventory().size();
	}

	@Override
	default boolean isEmpty() {
		return this.getInventory().isEmpty();
	}

	@Override
	default ItemStack getStack(int slot) {
		return this.getInventory().getStack(slot);
	}

	@Override
	default ItemStack removeStack(int slot, int amount) {
		return this.getInventory().removeStack(slot, amount);
	}

	@Override
	default ItemStack removeStack(int slot) {
		return this.getInventory().removeStack(slot);
	}

	@Override
	default void setStack(int slot, ItemStack stack) {
		this.getInventory().setStack(slot, stack);
	}

	@Override
	default int getMaxCountPerStack() {
		return this.getInventory().getMaxCountPerStack();
	}

	@Override
	default void markDirty() {
		this.getInventory().markDirty();
	}

	@Override
	default boolean canPlayerUse(PlayerEntity player) {
		return this.getInventory().canPlayerUse(player);
	}

	@Override
	default void onOpen(PlayerEntity player) {
		this.getInventory().onOpen(player);
	}

	@Override
	default void onClose(PlayerEntity player) {
		this.getInventory().onClose(player);
	}

	@Override
	default boolean isValid(int slot, ItemStack stack) {
		return this.getInventory().isValid(slot, stack);
	}

	@Override
	default int count(Item item) {
		return this.getInventory().count(item);
	}

	@Override
	default boolean containsAny(Set<Item> items) {
		return this.getInventory().containsAny(items);
	}

	@Override
	default void clear() {
		this.getInventory().clear();
	}
}
