/*
 * MIT License
 *
 * Copyright (c) 2019 i509VCB
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

package me.i509.fabric.cursedshulkerboxes.api.block.slab;

import java.util.Map;

import org.jetbrains.annotations.Nullable;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.EntityContext;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Util;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;

import me.i509.fabric.cursedshulkerboxes.abstraction.DefaultReturnHashMap;
import me.i509.fabric.cursedshulkerboxes.api.block.base.AbstractShulkerBoxBlock;

public abstract class AbstractCursedShulkerSlabBlock extends AbstractShulkerBoxBlock {
	protected static final VoxelShape BOTTOM_SHAPE = Block.createCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 8.0D, 16.0D);
	protected static final VoxelShape TOP_SHAPE = Block.createCuboidShape(0.0D, 8.0D, 0.0D, 16.0D, 16.0D, 16.0D);
	protected static final VoxelShape EAST_SHAPE = Block.createCuboidShape(0.0D, 0.0D, 0.0D, 8.0D, 16.0D, 16.0D);
	protected static final VoxelShape WEST_SHAPE = Block.createCuboidShape(8.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D);
	protected static final VoxelShape SOUTH_SHAPE = Block.createCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 8.0D);
	protected static final VoxelShape NORTH_SHAPE = Block.createCuboidShape(0.0D, 0.0D, 8.0D, 16.0D, 16.0D, 16.0D);

	private static final Map<Direction, VoxelShape> SHAPES = Util.create(new DefaultReturnHashMap<>(BOTTOM_SHAPE), map -> {
		map.put(Direction.DOWN, TOP_SHAPE);
		map.put(Direction.UP, BOTTOM_SHAPE);
		map.put(Direction.WEST, WEST_SHAPE);
		map.put(Direction.EAST, EAST_SHAPE);
		map.put(Direction.NORTH, NORTH_SHAPE);
		map.put(Direction.SOUTH, SOUTH_SHAPE);
	});

	protected AbstractCursedShulkerSlabBlock(Settings settings, int slotCount, @Nullable DyeColor color) {
		super(settings, slotCount, color);
		this.setDefaultState(this.getStateManager().getDefaultState().with(FACING, Direction.UP));
	}

	public static VoxelShape getShape(Direction facing) {
		return SHAPES.get(facing);
	}

	@Override
	public VoxelShape getCollisionShape(BlockState blockState, BlockView blockView, BlockPos blockPos, EntityContext entityContext) {
		return getOutlineShape(blockState, blockView, blockPos, entityContext);
	}

	@Override
	public VoxelShape getOutlineShape(BlockState blockState, BlockView blockView, BlockPos blockPos, EntityContext entityContext) {
		BlockEntity blockEntity = blockView.getBlockEntity(blockPos);
		return blockEntity instanceof AbstractCursedShulkerSlabBlockEntity
				? VoxelShapes.cuboid(((AbstractCursedShulkerSlabBlockEntity) blockEntity).getBoundingBox(blockState))
				: AbstractCursedShulkerSlabBlock.getShape(blockState.get(AbstractCursedShulkerSlabBlock.FACING));
	}

	@Override
	public Box getOpenBox(Direction facing) { // This is the bit above the block where collisions are checked for to verify the box can actually be opened.
		return getShape(facing).getBoundingBox()
				.stretch(0.25F * facing.getOffsetX(), 0.25F * facing.getOffsetY(), 0.25F * facing.getOffsetZ())
				.shrink(facing.getOffsetX(), facing.getOffsetY(), facing.getOffsetZ());
	}

	@Override
	public boolean canSuffocate(BlockState blockState, BlockView blockView, BlockPos blockPos) {
		return false; // This is required for sub 1 block tall boxes, otherwise special math is needed or collisions break.
	}
}
