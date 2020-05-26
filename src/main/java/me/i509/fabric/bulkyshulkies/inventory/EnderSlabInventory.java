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

package me.i509.fabric.bulkyshulkies.inventory;

import java.util.stream.IntStream;

import org.checkerframework.checker.nullness.qual.Nullable;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.SidedInventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.util.math.Direction;

import me.i509.fabric.bulkyshulkies.block.ShulkerBoxConstants;
import me.i509.fabric.bulkyshulkies.block.ender.EnderSlabBoxBlockEntity;

public class EnderSlabInventory extends SimpleInventory implements SidedInventory {
	@Nullable
	private EnderSlabBoxBlockEntity currentBlockEntity;

	public EnderSlabInventory() {
		super(ShulkerBoxConstants.SLAB_SLOT_COUNT);
	}

	public void setCurrentBlockEntity(EnderSlabBoxBlockEntity enderSlabBoxBE) {
		this.currentBlockEntity = enderSlabBoxBE;
	}

	public void readTags(ListTag listTag) {
		int j;

		for (j = 0; j < this.size(); ++j) {
			this.setStack(j, ItemStack.EMPTY);
		}

		for (j = 0; j < listTag.size(); ++j) {
			CompoundTag compoundTag = listTag.getCompound(j);
			int k = compoundTag.getByte("Slot") & 255;

			if (k >= 0 && k < this.size()) {
				this.setStack(k, ItemStack.fromTag(compoundTag));
			}
		}
	}

	public ListTag getTags() {
		ListTag listTag = new ListTag();

		for (int i = 0; i < this.size(); ++i) {
			ItemStack itemStack = this.getStack(i);

			if (!itemStack.isEmpty()) {
				CompoundTag compoundTag = new CompoundTag();
				compoundTag.putByte("Slot", (byte) i);
				itemStack.toTag(compoundTag);
				listTag.add(compoundTag);
			}
		}

		return listTag;
	}

	public boolean canPlayerUse(PlayerEntity player) {
		//return this.currentBlockEntity != null && !this.currentBlockEntity.canPlayerUse(player) ? false : super.canPlayerUse(player);
		return super.canPlayerUse(player); // TOOD: Fix
	}

	public void onOpen(PlayerEntity player) {
		if (this.currentBlockEntity != null) {
			this.currentBlockEntity.onOpen(player);
		}

		super.onOpen(player);
	}

	public void onClose(PlayerEntity player) {
		if (this.currentBlockEntity != null) {
			this.currentBlockEntity.onClose(player);
		}

		super.onClose(player);
		this.currentBlockEntity = null;
	}

	@Override
	public int[] getAvailableSlots(Direction side) {
		return IntStream.range(0, ShulkerBoxConstants.SLAB_SLOT_COUNT).toArray();
	}

	@Override
	public boolean canInsert(int slot, ItemStack stack, Direction dir) {
		return true;
	}

	@Override
	public boolean canExtract(int slot, ItemStack stack, Direction dir) {
		return true;
	}
}
