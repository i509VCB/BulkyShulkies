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

import net.minecraft.block.entity.ShulkerBoxBlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.BasicInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.util.collection.DefaultedList;

import me.i509.fabric.bulkyshulkies.api.item.ShulkerHelmetStage;

public class ShulkerHelmetInventory extends BasicInventory {
	private final int invSize;
	private ItemStack stack;

	public ShulkerHelmetInventory(ItemStack stack, int invSize) {
		super(getStacks(stack, invSize).toArray(new ItemStack[invSize]));
		this.stack = stack;
		this.invSize = invSize;
	}

	private static DefaultedList<ItemStack> getStacks(ItemStack usedStack, int SIZE) {
		CompoundTag compoundTag = usedStack.getSubTag("BlockEntityTag");
		DefaultedList<ItemStack> itemStacks = DefaultedList.ofSize(SIZE, ItemStack.EMPTY);

		if (compoundTag != null && compoundTag.contains("Items", 9)) {
			Inventories.fromTag(compoundTag, itemStacks);
		}

		return itemStacks;
	}

	@Override
	public void readTags(ListTag listTag) {
		int j;

		for (j = 0; j < this.getInvSize(); ++j) {
			this.setInvStack(j, ItemStack.EMPTY);
		}

		for (j = 0; j < listTag.size(); ++j) {
			CompoundTag compoundTag = listTag.getCompound(j);
			int k = compoundTag.getByte("Slot") & 255;

			if (k >= 0 && k < this.getInvSize()) {
				this.setInvStack(k, ItemStack.fromTag(compoundTag));
			}
		}
	}

	@Override
	public ListTag getTags() {
		ListTag listTag = new ListTag();

		for (int i = 0; i < this.getInvSize(); ++i) {
			ItemStack itemStack = this.getInvStack(i);

			if (!itemStack.isEmpty()) {
				CompoundTag compoundTag = new CompoundTag();
				compoundTag.putByte("Slot", (byte) i);
				itemStack.toTag(compoundTag);
				listTag.add(compoundTag);
			}
		}

		return listTag;
	}

	@Override
	public void markDirty() {
		super.markDirty();
		CompoundTag compoundTag = this.stack.getSubTag("BlockEntityTag");

		if (this.isInvEmpty()) {
			this.stack.removeSubTag("BlockEntityTag");
		} else {
			if (compoundTag == null) {
				compoundTag = this.stack.getOrCreateSubTag("BlockEntityTag");
			}

			DefaultedList<ItemStack> itemStacks = DefaultedList.ofSize(this.getInvSize(), ItemStack.EMPTY);

			for (int i = 0; i < this.getInvSize(); ++i) {
				itemStacks.set(i, this.getInvStack(i));
			}

			Inventories.toTag(compoundTag, itemStacks);
		}
	}

	@Override
	public void onInvClose(PlayerEntity playerEntity) {
		this.markDirty();
		ShulkerHelmetStage.bulkyshulkies$getStageFromEntity(playerEntity).bulkyshulkies$setStage(ShulkerBoxBlockEntity.AnimationStage.CLOSING);
	}
}
