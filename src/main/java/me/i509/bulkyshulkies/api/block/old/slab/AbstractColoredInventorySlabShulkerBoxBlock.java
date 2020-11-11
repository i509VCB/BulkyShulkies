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

package me.i509.bulkyshulkies.api.block.old.slab;

import org.checkerframework.checker.nullness.qual.Nullable;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.ShulkerBoxBlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.stat.Stats;
import net.minecraft.state.StateManager;
import net.minecraft.util.ActionResult;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

import me.i509.bulkyshulkies.api.block.old.colored.AbstractColoredInventoryShulkerBoxBlock;
import me.i509.bulkyshulkies.api.block.old.entity.inventory.AbstractInventoryShulkerBoxBlockEntity;
import me.i509.bulkyshulkies.api.block.old.entity.inventory.ShulkerBlockEntity;

public abstract class AbstractColoredInventorySlabShulkerBoxBlock extends AbstractColoredInventoryShulkerBoxBlock {
	protected AbstractColoredInventorySlabShulkerBoxBlock(Settings settings, int slots, @Nullable DyeColor color) {
		super(settings, slots, color);
		this.setDefaultState(this.getStateManager().getDefaultState().with(FACING, Direction.NORTH));
	}

	@Override
	public VoxelShape getCollisionShape(BlockState blockState, BlockView blockView, BlockPos blockPos, ShapeContext shapeContext) {
		return getOutlineShape(blockState, blockView, blockPos, shapeContext);
	}

	@Override
	public VoxelShape getOutlineShape(BlockState blockState, BlockView blockView, BlockPos blockPos, ShapeContext shapeContext) {
		BlockEntity blockEntity = blockView.getBlockEntity(blockPos);
		return blockEntity instanceof ShulkerBlockEntity
						? ((ShulkerBlockEntity) blockEntity).getBoundingBox(blockState)
						: AbstractShulkerSlabBlock.getShape(blockState.get(FACING));
	}

	@Override
	public Box getLidCollisionBox(Direction facing) { // This is the bit above the block where collisions are checked for to verify the box can actually be opened.
		return getShape(facing).getBoundingBox()
						.stretch(0.25F * facing.getOffsetX(), 0.25F * facing.getOffsetY(), 0.25F * facing.getOffsetZ())
						.shrink(facing.getOffsetX(), facing.getOffsetY(), facing.getOffsetZ());
	}

	@Override
	public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity playerEntity, Hand hand, BlockHitResult hitResult) {
		if (world.isClient) {
			return ActionResult.SUCCESS;
		} else if (playerEntity.isSpectator()) {
			return ActionResult.SUCCESS;
		} else {
			BlockEntity blockEntity = world.getBlockEntity(pos);

			if (blockEntity instanceof AbstractInventoryShulkerBoxBlockEntity) {
				Direction facing = state.get(FACING);
				ShulkerBlockEntity cursedBlockEntity = (ShulkerBlockEntity) blockEntity;
				boolean shouldOpen;

				if (cursedBlockEntity.getAnimationStage() == ShulkerBoxBlockEntity.AnimationStage.CLOSED) {
					Box openBox = getLidCollisionBox(facing);
					shouldOpen = world.isSpaceEmpty(openBox.offset(pos.offset(facing)));
				} else {
					shouldOpen = true;
				}

				if (shouldOpen) {
					// TODO: Abstract this logic for opening screens
					playerEntity.openHandledScreen((AbstractInventoryShulkerBoxBlockEntity) blockEntity);
					playerEntity.incrementStat(Stats.OPEN_SHULKER_BOX);
				}

				return ActionResult.SUCCESS;
			} else {
				return ActionResult.PASS;
			}
		}
	}

	@Override
	public BlockState getPlacementState(ItemPlacementContext placementContext) {
		return this.getDefaultState().with(FACING, placementContext.getSide());
	}

	@Override
	protected void appendProperties(StateManager.Builder<Block, BlockState> stateManagerBuilder) {
		stateManagerBuilder.add(FACING);
	}

	@Override
	public BlockState rotate(BlockState blockState, BlockRotation blockRotation) {
		return blockState.with(FACING, blockRotation.rotate(blockState.get(FACING)));
	}

	@Override
	public BlockState mirror(BlockState blockState, BlockMirror blockMirror) {
		return blockState.rotate(blockMirror.getRotation(blockState.get(FACING)));
	}

	public static VoxelShape getShape(Direction facing) {
		return AbstractShulkerSlabBlock.getShape(facing);
	}
}
