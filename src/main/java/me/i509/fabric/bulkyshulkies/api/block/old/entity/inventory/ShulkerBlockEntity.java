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

package me.i509.fabric.bulkyshulkies.api.block.old.entity.inventory;

import java.util.List;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.ShulkerBoxBlockEntity;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.entity.Entity;
import net.minecraft.entity.MovementType;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.text.Text;
import net.minecraft.util.Tickable;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.shape.VoxelShape;

import me.i509.fabric.bulkyshulkies.api.block.old.base.OldShulkerBox;

/**
 * Represents a shulker box block entity and exposes some information about the blockentity's state.
 *
 * <p>It is recommended to use {@link AbstractInventoryShulkerBoxBlockEntity} instead, which will handle most of the boilerplate from container based storage blocks.
 */
public interface ShulkerBlockEntity extends Tickable {
	/**
	 * Gets the bounding box of the BlockEntity. This will redirect the call to after getting the facing direction from the state {@link ShulkerBlockEntity#getBoundingBox(BlockState)}.
	 *
	 * @param state The blockState of the BlockEntity.
	 * @return The bounding box of the block entity.
	 */
	VoxelShape getBoundingBox(BlockState state);

	Box getLidCollisionBox(BlockState state);

	/**
	 * Gets the current animation stage of the shulker box.
	 */
	ShulkerBoxBlockEntity.AnimationStage getAnimationStage();

	/**
	 * Gets the current progress of the animation.
	 *
	 * @return The current progress.
	 */
	float getAnimationProgress(float delta);

	void updateNeighborStates();

	void updateAnimation();

	DirectionProperty getDirectionProperty();

	Text getDisplayName();

	BlockEntity toBlockEntity();

	@Override
	default void tick() {
		this.updateAnimation();

		if (this.getAnimationStage() == ShulkerBoxBlockEntity.AnimationStage.OPENING || this.getAnimationStage() == ShulkerBoxBlockEntity.AnimationStage.CLOSING) {
			this.pushEntities(this.toBlockEntity(), this.getDirectionProperty());
		}
	}

	default void pushEntities(BlockEntity blockEntity, DirectionProperty directionProperty) {
		//noinspection ConstantConditions - there will be a world
		BlockState blockState = blockEntity.getWorld().getBlockState(blockEntity.getPos());

		if (blockState.getBlock() instanceof OldShulkerBox) {
			Direction direction = blockState.get(directionProperty);
			Box box = this.getLidCollisionBox(blockState).offset(blockEntity.getPos());
			List<Entity> list = blockEntity.getWorld().getOtherEntities(null, box);

			if (!list.isEmpty()) {
				for (Entity entity : list) {
					if (entity.getPistonBehavior() != PistonBehavior.IGNORE) {
						double x = 0.0D;
						double y = 0.0D;
						double z = 0.0D;
						Box entityBox = entity.getBoundingBox();
						switch (direction.getAxis()) {
						case X:
							if (direction.getDirection() == Direction.AxisDirection.POSITIVE) {
								x = box.maxX - entityBox.minX;
							} else {
								x = entityBox.maxX - box.minX;
							}

							x += 0.01D;
							break;
						case Y:
							if (direction.getDirection() == Direction.AxisDirection.POSITIVE) {
								y = box.maxY - entityBox.minY;
							} else {
								y = entityBox.maxY - box.minY;
							}

							y += 0.01D;
							break;
						case Z:
							if (direction.getDirection() == Direction.AxisDirection.POSITIVE) {
								z = box.maxZ - entityBox.minZ;
							} else {
								z = entityBox.maxZ - box.minZ;
							}

							z += 0.01D;
						}

						entity.move(MovementType.SHULKER_BOX, new Vec3d(x * (double) direction.getOffsetX(), y * (double) direction.getOffsetY(), z * (double) direction.getOffsetZ()));
					}
				}
			}
		}
	}
}
