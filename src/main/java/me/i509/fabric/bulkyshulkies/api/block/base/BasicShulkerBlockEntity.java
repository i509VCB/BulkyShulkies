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

package me.i509.fabric.bulkyshulkies.api.block.base;

import org.jetbrains.annotations.Nullable;

import net.minecraft.block.BlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DyeColor;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;

/**
 * Represents a shulker box block entity and exposes some information about the blockentity's state.
 *
 * <p>It is recommended to use {@link AbstractShulkerBoxBE} instead, which will handle most of the boilerplate from container based storage blocks.
 */
public interface BasicShulkerBlockEntity {
	/**
	 * Gets the bounding box of the BlockEntity. This will redirect the call to after getting the facing direction from the state {@link BasicShulkerBlockEntity#getBoundingBox(BlockState)}.
	 *
	 * @param state The blockState of the BlockEntity.
	 * @return The bounding box of the block entity.
	 */
	VoxelShape getBoundingBox(BlockState state);

	Box getLidCollisionBox(BlockState state);

	/**
	 * Gets the current animation stage of the shulker box.
	 *
	 * @return The current {@link net.minecraft.block.entity.ShulkerBoxBlockEntity.AnimationStage}
	 */
	AnimationStatus getAnimationStage();

	/**
	 * Gets the color of the shulker box.
	 *
	 * @return The color of the box, or null if the box is not colored.
	 */
	@Nullable
	DyeColor getColor();

	/**
	 * Gets the current progress of the animation.
	 *
	 * @return The current progress.
	 */
	float getAnimationProgress(float delta);

	int getProgress();

	/**
	 * Checks if an item can be inserted into an item slot.
	 *
	 * @param inventorySlot The inventory slot the item is being placed at.
	 * @param stack         The itemstack that may be added to inventory.
	 * @param direction     The facing direction of the BlockEntity.
	 * @return If true, the item will enter the inventory, otherwise the item will not enter the inventory.
	 */
	boolean canInsertInvStack(int inventorySlot, ItemStack stack, @Nullable Direction direction);

	enum AnimationStatus {
		OPENED,
		CLOSED,
		OPENING,
		CLOSING;
	}
}
