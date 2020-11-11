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

package me.i509.bulkyshulkies.api.block.old.base;

import org.checkerframework.checker.nullness.qual.Nullable;

import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.item.ItemConvertible;
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
 */
public interface OldShulkerBox {
	DirectionProperty FACING = Properties.FACING;

	/**
	 * Gets the VoxelShape representing the outline and hitbox shape. This shape should adjust with the lid of the shulker box.
	 *
	 * @param blockState    The blockState.
	 * @param blockView     The blockView.
	 * @param blockPos      The blockPos.
	 * @param shapeContext The entityContext.
	 * @return A VoxelShape which is the collision bounds and outline shape for the shulker box.
	 */
	VoxelShape getOutlineShape(BlockState blockState, BlockView blockView, BlockPos blockPos, ShapeContext shapeContext);

	/**
	 * Gets the ItemStack which represents this shulker box.
	 *
	 * @param color The DyeColor the box should be in, may be null.
	 * @return An itemstack which represents a shulker box in the color, or uncolored if color is null.
	 */
	default ItemStack getItemStack(@Nullable DyeColor color) {
		return new ItemStack(this.getItem(color));
	}

	ItemConvertible getItem(@Nullable DyeColor color);

	/**
	 * Get's the dimensions of the Box when it is fully opened.
	 *
	 * @param facing The direction the blockState is facing.
	 * @return A Box which contains the maximum dimensions of the box.
	 */
	Box getLidCollisionBox(Direction facing);

	/**
	 * Get's the dimensions of the Box when it is fully opened.
	 *
	 * @param state the block state
	 * @return A Box which contains the maximum dimensions of the box.
	 */
	default Box getLidCollisionBox(BlockState state) {
		return this.getLidCollisionBox(state.get(FACING));
	}
}
