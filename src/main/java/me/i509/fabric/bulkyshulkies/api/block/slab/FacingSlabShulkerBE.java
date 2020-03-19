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

package me.i509.fabric.bulkyshulkies.api.block.slab;

import java.util.List;

import org.jetbrains.annotations.Nullable;

import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.entity.Entity;
import net.minecraft.entity.MovementType;
import net.minecraft.util.math.Vec3d;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DyeColor;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;

import me.i509.fabric.bulkyshulkies.api.block.FacingShulkerBoxBlock;
import me.i509.fabric.bulkyshulkies.api.block.base.AbstractShulkerBoxBE;
import me.i509.fabric.bulkyshulkies.api.block.base.BasicShulkerBlock;

// TODO Change push speed as not to make it as jaggedly hard when being moved.
public abstract class FacingSlabShulkerBE extends AbstractShulkerBoxBE {
	protected FacingSlabShulkerBE(BlockEntityType<?> blockEntityType, int maxAvailableSlot, @Nullable DyeColor color) {
		super(blockEntityType, maxAvailableSlot, color);
		this.inventory = DefaultedList.ofSize(this.AVAILABLE_SLOTS.length, ItemStack.EMPTY);
	}

	@Override
	public VoxelShape getBoundingBox(BlockState blockState) {
		Direction direction = blockState.get(FacingShulkerBoxBlock.FACING);
		float f = this.getAnimationProgress(1.0F);
		return VoxelShapes.cuboid(AbstractCursedShulkerSlabBlock.getShape(direction)
			.getBoundingBox()
			.stretch(0.25F * f * direction.getOffsetX(), 0.25F * f * direction.getOffsetY(), 0.25F * f * direction.getOffsetZ()));
	}

	@Override
	public Box getLidCollisionBox(BlockState blockState) {
		Direction direction = blockState.get(FacingShulkerBoxBlock.FACING).getOpposite();
		return this.getBoundingBox(blockState).getBoundingBox().shrink(direction.getOffsetX(), direction.getOffsetY(), direction.getOffsetZ());
	}

	/**
	 * We slow down the pushes.
	 */
	@Override
	protected void pushEntities() {
		BlockState blockState = this.world.getBlockState(this.getPos());

		if (blockState.getBlock() instanceof BasicShulkerBlock) {
			Direction direction = blockState.get(FacingShulkerBoxBlock.FACING);
			Box box = this.getLidCollisionBox(blockState).offset(this.pos);
			List<Entity> list = this.world.getEntities(null, box);

			if (!list.isEmpty()) {
				for (int i = 0; i < list.size(); ++i) {
					Entity entity = list.get(i);

					if (entity.getPistonBehavior() != PistonBehavior.IGNORE) {
						double d = 0.0D;
						double e = 0.0D;
						double f = 0.0D;
						Box box2 = entity.getBoundingBox();
						switch (direction.getAxis()) {
						case X:
							if (direction.getDirection() == Direction.AxisDirection.POSITIVE) {
								d = box.x2 - box2.x1;
							} else {
								d = box2.x2 - box.x1;
							}

							d += 0.005D;
							break;
						case Y:
							if (direction.getDirection() == Direction.AxisDirection.POSITIVE) {
								e = box.y2 - box2.y1;
							} else {
								e = box2.y2 - box.y1;
							}

							e += 0.005D;
							break;
						case Z:
							if (direction.getDirection() == Direction.AxisDirection.POSITIVE) {
								f = box.z2 - box2.z1;
							} else {
								f = box2.z2 - box.z1;
							}

							f += 0.005D;
						}

						entity.move(MovementType.SHULKER_BOX, new Vec3d(d * (double) direction.getOffsetX(), e * (double) direction.getOffsetY(), f * (double) direction.getOffsetZ()));
					}
				}
			}
		}
	}
}
