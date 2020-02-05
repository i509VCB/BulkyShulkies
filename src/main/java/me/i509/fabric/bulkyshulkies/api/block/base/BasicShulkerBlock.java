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
import net.minecraft.entity.EntityContext;
import net.minecraft.item.ItemStack;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.DyeColor;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;

/**
 * This represents all the base methods to a shulker box.
 *
 * <p>It is recommended to use {@link AbstractShulkerBoxBlock} instead, which will handle most of the boilerplate from container based storage blocks.
 */
public interface BasicShulkerBlock {
	DirectionProperty FACING = Properties.FACING;

	/**
	 * Gets the VoxelShape representing the outline and hitbox shape. This shape should adjust with the lid of the shulker box.
	 *
	 * @param blockState    The blockState.
	 * @param blockView     The blockView.
	 * @param blockPos      The blockPos.
	 * @param entityContext The entityContext.
	 * @return A VoxelShape which is the collision bounds and outline shape for the shulker box.
	 */
	VoxelShape getOutlineShape(BlockState blockState, BlockView blockView, BlockPos blockPos, EntityContext entityContext);

	/**
	 * Gets the ItemStack which represents this shulker box.
	 *
	 * @param color The DyeColor the box should be in, may be null.
	 * @return An itemstack which represents a shulker box in the color, or uncolored if color is null.
	 */
	ItemStack getItemStack(@Nullable DyeColor color);

	/**
	 * Checks weather an entity can suffocate in this block.
	 * If your shulker box is an odd shape or is less than a block tall, this should be false or handled with the shape in the context.
	 *
	 * @param blockState The blockState.
	 * @param blockView  The blockView.
	 * @param blockPos   The blockPos.
	 * @return true if an entity can suffocate, otherwise false.
	 */
	boolean canSuffocate(BlockState blockState, BlockView blockView, BlockPos blockPos);

	/**
	 * Get's the dimensions of the Box when it is fully opened.
	 *
	 * @param facing The direction the blockState is facing.
	 * @return A Box which contains the maximum dimensions of the box.
	 */
	Box getOpenBox(Direction facing);

	/**
	 * Get's the DyeColor the shulker box is.
	 *
	 * @return A DyeColor that is box is, or null if the box is not colored.
	 */
	@Nullable
	DyeColor getColor();
}
