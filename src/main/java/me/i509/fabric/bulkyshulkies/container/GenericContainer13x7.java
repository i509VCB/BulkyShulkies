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

package me.i509.fabric.bulkyshulkies.container;

import net.minecraft.container.Container;
import net.minecraft.container.Slot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;

import me.i509.fabric.bulkyshulkies.BulkyShulkies;
import me.i509.fabric.bulkyshulkies.api.SlotFactory;

public class GenericContainer13x7 extends Container {
	private final Inventory inventory;
	private final Text name;

	public GenericContainer13x7(int syncId, SlotFactory slotFactory, PlayerInventory playerInventory, Inventory inventory, Text containerName) {
		super(null, syncId);
		this.inventory = inventory;
		this.name = containerName;
		inventory.onInvOpen(playerInventory.player);

		int i = 63;

		for (int n = 0; n < 7; ++n) {
			for (int m = 0; m < 13; ++m) {
				//this.addSlot(new ShulkerBoxSlot(inventory, m + n * 9, 7 + m * 18, 18 + n * 18));
				this.addSlot(slotFactory.create(inventory, m + n * 9, 7 + m * 18, 18 + n * 18));
			}
		}

		for (int n = 0; n < 3; ++n) {
			for (int m = 0; m < 9; ++m) {
				this.addSlot(new Slot(playerInventory, m + n * 9 + 9, 8 + m * 18, 103 + n * 18 + i));
			}
		}

		for (int n = 0; n < 9; ++n) {
			this.addSlot(new Slot(playerInventory, n, 8 + n * 18, 161 + i));
		}
	}

	@Override
	public boolean canUse(PlayerEntity player) {
		return this.inventory.canPlayerUseInv(player);
	}

	public ItemStack transferSlot(PlayerEntity player, int invSlot) {
		ItemStack itemStack = ItemStack.EMPTY;
		Slot slot = this.slotList.get(invSlot);

		if (slot != null && slot.hasStack()) {
			ItemStack itemStack2 = slot.getStack();
			itemStack = itemStack2.copy();

			if (invSlot < 91) {
				if (!this.insertItem(itemStack2, 7 * 9, this.slotList.size(), true)) {
					return ItemStack.EMPTY;
				}
			} else if (!this.insertItem(itemStack2, 0, 91, false)) {
				return ItemStack.EMPTY;
			}

			if (itemStack2.isEmpty()) {
				slot.setStack(ItemStack.EMPTY);
			} else {
				slot.markDirty();
			}
		}

		return itemStack;
	}

	public void close(PlayerEntity player) {
		super.close(player);
		this.inventory.onInvClose(player);
	}

	public Inventory getInventory() {
		return this.inventory;
	}

	public Text getDisplayName() {
		return this.name;
	}

	public boolean canInsertIntoSlot(ItemStack stack, Slot slot) {
		return BulkyShulkies.getInstance().canInsertItem(stack);
	}
}
