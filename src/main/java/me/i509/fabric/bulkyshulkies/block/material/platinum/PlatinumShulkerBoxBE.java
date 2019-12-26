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

package me.i509.fabric.bulkyshulkies.block.material.platinum;

import java.util.List;

import org.jetbrains.annotations.Nullable;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.ItemEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.predicate.entity.EntityPredicates;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.DyeColor;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Direction;

import me.i509.fabric.bulkyshulkies.BulkyShulkies;
import me.i509.fabric.bulkyshulkies.api.block.Abstract1X1ShulkerBoxBE;
import me.i509.fabric.bulkyshulkies.api.block.base.AbstractShulkerBoxBlock;
import me.i509.fabric.bulkyshulkies.block.ShulkerBoxConstants;
import me.i509.fabric.bulkyshulkies.api.event.MagnetismCollectionCallback;
import me.i509.fabric.bulkyshulkies.registry.ShulkerBlockEntities;

public class PlatinumShulkerBoxBE extends Abstract1X1ShulkerBoxBE {
	private int lastMagnetTick = 0;

	public PlatinumShulkerBoxBE(@Nullable DyeColor color) {
		super(ShulkerBlockEntities.PLATINUM_SHULKER_BOX, ShulkerBoxConstants.PLATINUM_SLOT_COUNT, color);
	}

	public PlatinumShulkerBoxBE() {
		this(null);
	}

	public void tick() {
		super.tick();

		if (BulkyShulkies.getInstance().getConfig().shouldPlatinumUseMagnetism()) {
			tickMagnets();
		}
	}

	private void tickMagnets() {
		if (this.hasWorld() && !this.getWorld().isClient) {
			if (lastMagnetTick >= BulkyShulkies.getInstance().getConfig().getMagnetismTickDelay()) {
				this.lastMagnetTick = 0;
				int range = BulkyShulkies.getInstance().getConfig().getPlatinumMagnetMaxRange();
				Box box = new Box(this.getPos()).stretch(range, range, range);
				List<ItemEntity> entities = this.getWorld().getEntities(EntityType.ITEM, box, EntityPredicates.VALID_ENTITY);

				MagnetismCollectionCallback.EVENT.invoker().onMagnetismTick(entities, this.getWorld(), this.getPos(), this);
				// Attempt to insert the items into the box

				for (ItemEntity itemEntity : entities) {
					this.attemptInsertion(itemEntity, this);
				}
			}

			this.lastMagnetTick++;
		}
	}

	private void attemptInsertion(ItemEntity itemEntity, PlatinumShulkerBoxBE shulkerBoxBE) {
		SidedInventory inventory = AbstractShulkerBoxBlock.getInventoryStatically(this.getWorld(), this.getPos());
		ItemStack stack = itemEntity.getStack();

		if (!inventory.canInsertInvStack(0, stack, Direction.UP)) { // We do this to make sure people don't try to do recursive shulker boxes.
			return;
		}

		ItemStack result = this.add(stack, inventory);

		itemEntity.setStack(result);
	}

	public ItemStack add(ItemStack itemStack, Inventory inventory) {
		ItemStack itemStack2 = itemStack.copy();
		this.addToExistingSlot(itemStack2, inventory);

		if (itemStack2.isEmpty()) {
			return ItemStack.EMPTY;
		} else {
			this.addToNewSlot(itemStack2, inventory);
			return itemStack2.isEmpty() ? ItemStack.EMPTY : itemStack2;
		}
	}

	private void addToNewSlot(ItemStack stack, Inventory inventory) {
		for (int i = 0; i < inventory.getInvSize(); ++i) {
			ItemStack itemStack = this.getInvStack(i);

			if (itemStack.isEmpty()) {
				this.setInvStack(i, stack.copy());
				stack.setCount(0);
				return;
			}
		}
	}

	private void addToExistingSlot(ItemStack stack, Inventory inventory) {
		for (int i = 0; i < inventory.getInvSize(); ++i) {
			ItemStack itemStack = this.getInvStack(i);

			if (ItemStack.areItemsEqualIgnoreDamage(itemStack, stack)) {
				this.transfer(stack, itemStack, inventory);

				if (stack.isEmpty()) {
					return;
				}
			}
		}
	}

	private void transfer(ItemStack source, ItemStack target, Inventory inventory) {
		int i = Math.min(this.getInvMaxStackAmount(), target.getMaxCount());
		int j = Math.min(source.getCount(), i - target.getCount());

		if (j > 0) {
			target.increment(j);
			source.decrement(j);
			inventory.markDirty();
		}
	}

	@Override
	protected Text getContainerName() {
		return new TranslatableText("container.platinumShulkerBox");
	}
}
