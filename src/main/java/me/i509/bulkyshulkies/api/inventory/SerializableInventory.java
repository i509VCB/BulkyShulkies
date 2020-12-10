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

package me.i509.bulkyshulkies.api.inventory;

import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;

import net.fabricmc.fabric.api.util.NbtType;

public interface SerializableInventory extends Inventory {
	default void inventoryFromTag(CompoundTag tag) {
		final ListTag listTag = tag.getList("Items", NbtType.COMPOUND);
		this.readTags(listTag);
	}

	default CompoundTag inventoryToTag(CompoundTag tag) {
		final ListTag listTag = this.getTags();
		tag.put("Items", listTag);

		return tag;
	}

	default void readTags(ListTag listTag) {
		for (int slot = 0; slot < this.size(); slot++) {
			this.setStack(slot, ItemStack.EMPTY);
		}

		for (int slot = 0; slot < listTag.size(); slot++) {
			CompoundTag itemTag = listTag.getCompound(slot);
			int k = itemTag.getByte("Slot") & 255;

			if (k < this.size()) {
				this.setStack(k, ItemStack.fromTag(itemTag));
			}
		}
	}

	default ListTag getTags() {
		ListTag listTag = new ListTag();

		for (int slot = 0; slot < this.size(); slot++) {
			ItemStack itemStack = this.getStack(slot);

			if (!itemStack.isEmpty()) {
				CompoundTag itemTag = new CompoundTag();
				itemTag.putByte("Slot", (byte) slot);
				itemStack.toTag(itemTag);
				listTag.add(itemTag);
			}
		}

		return listTag;
	}
}
