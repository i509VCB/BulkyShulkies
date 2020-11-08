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

package me.i509.fabric.bulkyshulkies.api.block.old.entity.colored;

import org.checkerframework.checker.nullness.qual.Nullable;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.state.property.Properties;
import net.minecraft.util.DyeColor;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;

import me.i509.fabric.bulkyshulkies.api.block.old.entity.inventory.AbstractColoredInventoryShulkerBoxBlockEntity;

public abstract class ColoredFacing1X1ShulkerBoxBlockEntity extends AbstractColoredInventoryShulkerBoxBlockEntity {
	protected ColoredFacing1X1ShulkerBoxBlockEntity(BlockEntityType<? extends ColoredFacing1X1ShulkerBoxBlockEntity> blockEntityType, int slots, @Nullable DyeColor color) {
		super(blockEntityType, Properties.FACING, slots, color);
	}

	@Override
	public VoxelShape getBoundingBox(BlockState state) {
		Direction direction = state.get(this.getDirectionProperty());
		float f = this.getAnimationProgress(1.0F);

		if (f == 0.0F) {
			return VoxelShapes.fullCube();
		}

		return VoxelShapes.cuboid(VoxelShapes.fullCube().getBoundingBox().stretch(f * 0.5F * direction.getOffsetX(), f * 0.5F * direction.getOffsetY(), f * 0.5F * direction.getOffsetZ()));
	}

	@Override
	public Box getLidCollisionBox(BlockState state) {
		Direction opposite = state.get(this.getDirectionProperty()).getOpposite();
		return this.getBoundingBox(state).getBoundingBox().shrink(opposite.getOffsetX(), opposite.getOffsetY(), opposite.getOffsetZ());
	}
}
