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

package me.i509.fabric.bulkyshulkies.block.material.platinum;

import java.util.List;

import org.checkerframework.checker.nullness.qual.Nullable;

import net.minecraft.block.entity.HopperBlockEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ItemEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.predicate.entity.EntityPredicates;
import net.minecraft.text.Text;
import net.minecraft.util.DyeColor;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;

import me.i509.fabric.bulkyshulkies.BulkyShulkies;
import me.i509.fabric.bulkyshulkies.api.block.entity.colored.ColoredFacing1X1ShulkerBoxBlockEntity;
import me.i509.fabric.bulkyshulkies.api.event.MagnetismCollectionCallback;
import me.i509.fabric.bulkyshulkies.block.ShulkerBoxConstants;
import me.i509.fabric.bulkyshulkies.registry.ShulkerBlockEntities;
import me.i509.fabric.bulkyshulkies.registry.ShulkerTexts;

public class PlatinumShulkerBoxBlockEntity extends ColoredFacing1X1ShulkerBoxBlockEntity {
	private int lastMagnetTick = 0;

	public PlatinumShulkerBoxBlockEntity(@Nullable DyeColor color) {
		super(ShulkerBlockEntities.PLATINUM_SHULKER_BOX, ShulkerBoxConstants.PLATINUM_SLOT_COUNT, color);
	}

	public PlatinumShulkerBoxBlockEntity() {
		this(null);
	}

	public void tick() {
		super.tick();

		if (BulkyShulkies.getInstance().getConfig().shouldPlatinumUseMagnetism()) {
			this.tickMagnets();
		}
	}

	private void tickMagnets() {
		if (this.hasWorld() && !this.getWorld().isClient()) {
			if (lastMagnetTick >= BulkyShulkies.getInstance().getConfig().getMagnetismTickDelay()) {
				this.lastMagnetTick = 0;
				int range = BulkyShulkies.getInstance().getConfig().getPlatinumMagnetMaxRange();

				final Direction facing = this.getWorld().getBlockState(this.getPos()).get(PlatinumShulkerBoxBlock.FACING);

				Box box = new Box(0, 0, 0, range, range, range)
								.offset(this.getPos())
								.offset(PlatinumShulkerBoxBlockEntity.getDirectionalBoxOffset(facing));

				List<ItemEntity> entities = this.getWorld().getEntitiesByType(EntityType.ITEM, box, EntityPredicates.VALID_ENTITY);

				MagnetismCollectionCallback.EVENT.invoker().onMagnetismTick(entities, this.getWorld(), this.getPos(), this);
				// Attempt to insert the items into the box

				for (ItemEntity itemEntity : entities) {
					this.attemptInsertion(itemEntity);
				}
			}

			this.lastMagnetTick++;
		}
	}

	private void attemptInsertion(ItemEntity itemEntity) {
		Inventory inventory = HopperBlockEntity.getInventoryAt(this.getWorld(), this.getPos());
		ItemStack stack = itemEntity.getStack();

		if (inventory instanceof SidedInventory) {
			if (!((SidedInventory) inventory).canInsert(0, stack, Direction.UP)) { // We do this to make sure people don't try to do recursive shulker boxes.
				return;
			}
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
		for (int i = 0; i < inventory.size(); ++i) {
			ItemStack itemStack = this.getStack(i);

			if (itemStack.isEmpty()) {
				this.setStack(i, stack.copy());
				stack.setCount(0);
				return;
			}
		}
	}

	private void addToExistingSlot(ItemStack stack, Inventory inventory) {
		for (int i = 0; i < inventory.size(); ++i) {
			ItemStack itemStack = this.getStack(i);

			if (ItemStack.areItemsEqualIgnoreDamage(itemStack, stack)) {
				this.transfer(stack, itemStack, inventory);

				if (stack.isEmpty()) {
					return;
				}
			}
		}
	}

	private void transfer(ItemStack source, ItemStack target, Inventory inventory) {
		int i = Math.min(this.getMaxCountPerStack(), target.getMaxCount());
		int j = Math.min(source.getCount(), i - target.getCount());

		if (j > 0) {
			target.increment(j);
			source.decrement(j);
			inventory.markDirty();
		}
	}

	@Override
	protected Text getDefaultName() {
		return ShulkerTexts.PLATINUM_CONTAINER;
	}

	public static Vec3d getDirectionalBoxOffset(Direction direction) {
		final int size = BulkyShulkies.getInstance().getConfig().getPlatinumMagnetMaxRange() - 1;

		switch (direction) {
		case NORTH:
			return new Vec3d(-size / 2.0D, -size / 2.0D, -size);
		case SOUTH:
			return new Vec3d(-size / 2.0D, -size / 2.0D, 0);
		case WEST:
			return new Vec3d(-size, -size / 2.0D, -size / 2.0D);
		case EAST:
			return new Vec3d(0, -size / 2.0D, -size / 2.0D);
		case DOWN:
			return new Vec3d(-size / 2.0D, -size, -size / 2.0D);
		case UP:
		default:
			return new Vec3d(-size / 2.0D, 0, -size / 2.0D);
		}
	}
}
