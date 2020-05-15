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

package me.i509.fabric.bulkyshulkies.block.cursed.stair;

import org.checkerframework.checker.nullness.qual.Nullable;

import net.minecraft.block.BlockState;
import net.minecraft.block.enums.BlockHalf;
import net.minecraft.text.Text;
import net.minecraft.util.DyeColor;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;

import me.i509.fabric.bulkyshulkies.api.block.colored.ColoredHorizontalFacingShulkerBoxBlock;
import me.i509.fabric.bulkyshulkies.api.block.entity.colored.ColoredHorizontalFacingShulkerBoxBlockEntity;
import me.i509.fabric.bulkyshulkies.block.ShulkerBoxConstants;
import me.i509.fabric.bulkyshulkies.registry.ShulkerTexts;
import me.i509.fabric.bulkyshulkies.registry.ShulkerBlockEntities;

public class StairShulkerBoxBlockEntity extends ColoredHorizontalFacingShulkerBoxBlockEntity {
	public StairShulkerBoxBlockEntity(@Nullable DyeColor color) {
		super(ShulkerBlockEntities.STAIR, ShulkerBoxConstants.STAIR_SLOT_COUNT, color);
	}

	public StairShulkerBoxBlockEntity() {
		this(null);
	}

	@Override
	protected Text getDefaultName() {
		return ShulkerTexts.CURSED_STAIR_CONTAINER;
	}

	@Override
	public VoxelShape getBoundingBox(BlockState state) {
		VoxelShape unmodified = StairShulkerBoxBlock.getBaseStairShape(state);

		if (this.animationProgress == 0.0D) {
			return unmodified;
		}

		VoxelShape offset = unmodified.offset(0.0D, state.get(StairShulkerBoxBlock.HALF).equals(BlockHalf.BOTTOM) ? animationProgress : -1 * animationProgress, 0.0D);

		return VoxelShapes.cuboid(offset.getBoundingBox());
	}

	@Override
	public Box getLidCollisionBox(BlockState state) {
		Direction opposite = state.get(ColoredHorizontalFacingShulkerBoxBlock.FACING).getOpposite();
		return this.getBoundingBox(state).getBoundingBox().shrink(opposite.getOffsetX(), opposite.getOffsetY(), opposite.getOffsetZ());
	}

	// TODO: Modify pushEntities
}
