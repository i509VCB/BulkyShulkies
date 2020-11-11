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

import org.checkerframework.checker.nullness.qual.Nullable;

import net.minecraft.inventory.SidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.Direction;

public interface ForwardingSidedInventory extends ForwardingInventory, SidedInventory {
	@Override
	SidedInventory getInventory();

	// SIDED INVENTORY METHODS

	@Override
	default int[] getAvailableSlots(Direction side) {
		return this.getInventory().getAvailableSlots(side);
	}

	@Override
	default boolean canInsert(int slot, ItemStack stack, @Nullable Direction dir) {
		return this.getInventory().canInsert(slot, stack, dir);
	}

	@Override
	default boolean canExtract(int slot, ItemStack stack, Direction dir) {
		return this.getInventory().canExtract(slot, stack, dir);
	}
}