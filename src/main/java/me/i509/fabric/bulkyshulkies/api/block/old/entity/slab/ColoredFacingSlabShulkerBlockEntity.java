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

package me.i509.fabric.bulkyshulkies.api.block.old.entity.slab;

import org.checkerframework.checker.nullness.qual.Nullable;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.DyeColor;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;

import me.i509.fabric.bulkyshulkies.api.block.old.FacingShulkerBoxBlock;
import me.i509.fabric.bulkyshulkies.api.block.old.entity.colored.ColoredFacing1X1ShulkerBoxBlockEntity;
import me.i509.fabric.bulkyshulkies.api.block.old.slab.AbstractShulkerSlabBlock;

// TODO Change push speed as not to make it as jaggedly hard when being moved.
public abstract class ColoredFacingSlabShulkerBlockEntity extends ColoredFacing1X1ShulkerBoxBlockEntity {
	protected ColoredFacingSlabShulkerBlockEntity(BlockEntityType<? extends ColoredFacingSlabShulkerBlockEntity> blockEntityType, int slots, @Nullable DyeColor color) {
		super(blockEntityType, slots, color);
	}

	@Override
	public VoxelShape getBoundingBox(BlockState state) {
		Direction direction = state.get(FacingShulkerBoxBlock.FACING);
		float f = this.getAnimationProgress(1.0F);
		return VoxelShapes.cuboid(AbstractShulkerSlabBlock.getShape(direction)
			.getBoundingBox()
			.stretch(0.25F * f * direction.getOffsetX(), 0.25F * f * direction.getOffsetY(), 0.25F * f * direction.getOffsetZ()));
	}

	@Override
	public Box getLidCollisionBox(BlockState blockState) {
		Direction direction = blockState.get(FacingShulkerBoxBlock.FACING).getOpposite();
		return this.getBoundingBox(blockState).getBoundingBox().shrink(direction.getOffsetX(), direction.getOffsetY(), direction.getOffsetZ());
	}
}
