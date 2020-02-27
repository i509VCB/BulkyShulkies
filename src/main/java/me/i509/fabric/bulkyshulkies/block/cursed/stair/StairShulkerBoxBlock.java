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

import java.util.Map;

import org.jetbrains.annotations.Nullable;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Waterloggable;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.enums.BlockHalf;
import net.minecraft.block.enums.StairShape;
import net.minecraft.entity.EntityContext;
import net.minecraft.entity.mob.ShulkerLidCollisions;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.text.Text;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Util;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.IWorld;

import net.fabricmc.fabric.api.container.ContainerProviderRegistry;

import me.i509.fabric.bulkyshulkies.abstraction.DefaultReturnHashMap;
import me.i509.fabric.bulkyshulkies.api.block.HorizontalFacingShulkerBoxBlock;
import me.i509.fabric.bulkyshulkies.block.ShulkerBoxConstants;
import me.i509.fabric.bulkyshulkies.block.cursed.slab.CursedSlabShulkerBox;
import me.i509.fabric.bulkyshulkies.container.ContainerKeys;
import me.i509.fabric.bulkyshulkies.mixin.StairsBlockAccessor;

public class StairShulkerBoxBlock extends HorizontalFacingShulkerBoxBlock implements Waterloggable {
	public static final EnumProperty<StairShape> SHAPE = Properties.STAIR_SHAPE;
	public static final EnumProperty<BlockHalf> HALF = Properties.BLOCK_HALF;
	public static final BooleanProperty WATERLOGGED = Properties.WATERLOGGED;

	protected static final VoxelShape BOTTOM_SHAPE = CursedSlabShulkerBox.getShape(Direction.UP);
	protected static final VoxelShape TOP_SHAPE = CursedSlabShulkerBox.getShape(Direction.DOWN);

	protected static final Map<Integer, StairShapeHolder> INT_TO_SHAPE_HOLDER = new DefaultReturnHashMap<>(new StairShapeHolder(0));

	// Time for absolute madness with states

	public static VoxelShape getShape(double animationProgress, Direction facing, StairShape shape, BlockHalf half) {
		int progress = (int) (animationProgress * 10);
		return INT_TO_SHAPE_HOLDER.computeIfAbsent(progress, (p -> new StairShapeHolder(p, shape, facing, half))).getShape();
	}

	public StairShulkerBoxBlock(Settings settings, @Nullable DyeColor color) {
		super(settings, ShulkerBoxConstants.STAIR_SLOT_COUNT, color);
		this.setDefaultState(this.getStateManager().getDefaultState().with(SHAPE, StairShape.STRAIGHT).with(HALF, BlockHalf.BOTTOM).with(FACING, Direction.NORTH).with(WATERLOGGED, false));
	}

	@Override
	public VoxelShape getOutlineShape(BlockState state, BlockView view, BlockPos pos, EntityContext ePos) {
		BlockEntity blockEntity = view.getBlockEntity(pos);

		if (blockEntity instanceof StairShulkerBoxBE) {
			StairShape shape = state.get(StairShulkerBoxBlock.SHAPE);
			Direction facing = state.get(StairShulkerBoxBlock.FACING);
			BlockHalf half = state.get(StairShulkerBoxBlock.HALF);
			float f = ((StairShulkerBoxBE) blockEntity).getAnimationProgress(1.0F);
			return StairShulkerBoxBlock.getShape(f, facing, shape, half);
		}

		return StairShulkerBoxBlock.getShape(0.0D, Direction.NORTH, StairShape.STRAIGHT, BlockHalf.BOTTOM);
	}

	@Override
	protected void openContainer(BlockPos pos, PlayerEntity playerEntity, Text displayName) {
		ContainerProviderRegistry.INSTANCE.openContainer(ContainerKeys.SHULKER_SCROLLABLE_CONTAINER, playerEntity, (packetByteBuf -> {
			packetByteBuf.writeBlockPos(pos);
			packetByteBuf.writeText(displayName);
		}));
	}

	@Override
	public ItemStack getItemStack(@Nullable DyeColor color) {
		return null;
	}

	@Override
	public Box getOpenBox(Direction facing) {
		return ShulkerLidCollisions.getLidCollisionBox(new BlockPos(0, 1, 0), facing);
	}

	@Override
	public BlockEntity createBlockEntity(BlockView view) {
		return new StairShulkerBoxBE(this.color); // TODO temp
	}

	@Override
	public FluidState getFluidState(BlockState state) {
		return state.get(WATERLOGGED) ? Fluids.WATER.getStill(false) : super.getFluidState(state);
	}

	@Override
	protected void appendProperties(StateManager.Builder<Block, BlockState> stateManagerBuilder) {
		super.appendProperties(stateManagerBuilder);
		stateManagerBuilder.add(SHAPE);
		stateManagerBuilder.add(HALF);
		stateManagerBuilder.add(WATERLOGGED);
	}

	@Override
	public boolean hasSidedTransparency(BlockState state) {
		return true;
	}

	@Override
	public BlockState rotate(BlockState state, BlockRotation rotation) {
		return state.with(FACING, rotation.rotate(state.get(FACING)));
	}

	@Override
	public BlockState mirror(BlockState state, BlockMirror mirror) {
		Direction direction = state.get(FACING);
		StairShape stairShape = state.get(SHAPE);
		switch (mirror) {
		case LEFT_RIGHT:
			if (direction.getAxis() == Direction.Axis.Z) {
				switch (stairShape) {
				case INNER_LEFT:
					return state.rotate(BlockRotation.CLOCKWISE_180).with(SHAPE, StairShape.INNER_RIGHT);
				case INNER_RIGHT:
					return state.rotate(BlockRotation.CLOCKWISE_180).with(SHAPE, StairShape.INNER_LEFT);
				case OUTER_LEFT:
					return state.rotate(BlockRotation.CLOCKWISE_180).with(SHAPE, StairShape.OUTER_RIGHT);
				case OUTER_RIGHT:
					return state.rotate(BlockRotation.CLOCKWISE_180).with(SHAPE, StairShape.OUTER_LEFT);
				default:
					return state.rotate(BlockRotation.CLOCKWISE_180);
				}
			}

			break;
		case FRONT_BACK:
			if (direction.getAxis() == Direction.Axis.X) {
				switch (stairShape) {
				case INNER_LEFT:
					return state.rotate(BlockRotation.CLOCKWISE_180).with(SHAPE, StairShape.INNER_LEFT);
				case INNER_RIGHT:
					return state.rotate(BlockRotation.CLOCKWISE_180).with(SHAPE, StairShape.INNER_RIGHT);
				case OUTER_LEFT:
					return state.rotate(BlockRotation.CLOCKWISE_180).with(SHAPE, StairShape.OUTER_RIGHT);
				case OUTER_RIGHT:
					return state.rotate(BlockRotation.CLOCKWISE_180).with(SHAPE, StairShape.OUTER_LEFT);
				case STRAIGHT:
					return state.rotate(BlockRotation.CLOCKWISE_180);
				}
			}
		}

		return super.mirror(state, mirror);
	}

	@Override
	public BlockState getPlacementState(ItemPlacementContext ctx) {
		Direction direction = ctx.getSide();
		BlockPos blockPos = ctx.getBlockPos();
		FluidState fluidState = ctx.getWorld().getFluidState(blockPos);
		BlockState blockState = this.getDefaultState().with(FACING, ctx.getPlayerFacing()).with(HALF, direction != Direction.DOWN && (direction == Direction.UP || ctx.getHitPos().y - blockPos.getY() <= 0.5D) ? BlockHalf.BOTTOM : BlockHalf.TOP).with(WATERLOGGED, fluidState.getFluid() == Fluids.WATER);
		return blockState.with(SHAPE, StairsBlockAccessor.method_10675(blockState, ctx.getWorld(), blockPos));
	}

	@Override
	public BlockState getStateForNeighborUpdate(BlockState state, Direction facing, BlockState neighborState, IWorld world, BlockPos pos, BlockPos neighborPos) {
		if (state.get(WATERLOGGED)) {
			world.getFluidTickScheduler().schedule(pos, Fluids.WATER, Fluids.WATER.getTickRate(world));
		}

		return facing.getAxis().isHorizontal() ? state.with(SHAPE, StairsBlockAccessor.method_10675(state, world, pos)) : super.getStateForNeighborUpdate(state, facing, neighborState, world, pos, neighborPos);
	}

	public static class StairShapeHolder {
		private static final VoxelShape TOP_STRAIGHT_EAST_SHAPE = Block.createCuboidShape(0.0D, 0.0D, 0.0D, 8.0D, 16.0D, 16.0D);
		private static final VoxelShape TOP_STRAIGHT_WEST_SHAPE = Block.createCuboidShape(8.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D);
		private static final VoxelShape TOP_STRAIGHT_SOUTH_SHAPE = Block.createCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 8.0D);
		private static final VoxelShape TOP_STRAIGHT_NORTH_SHAPE = Block.createCuboidShape(0.0D, 0.0D, 8.0D, 16.0D, 16.0D, 16.0D);
		private static final Map<Direction, VoxelShape> TOP_STRAIGHT_SHAPES = Util.make(new DefaultReturnHashMap<>(TOP_STRAIGHT_NORTH_SHAPE), (map) -> {
			map.put(Direction.SOUTH, TOP_STRAIGHT_SOUTH_SHAPE);
			map.put(Direction.NORTH, TOP_STRAIGHT_NORTH_SHAPE);
			map.put(Direction.EAST, TOP_STRAIGHT_EAST_SHAPE);
			map.put(Direction.WEST, TOP_STRAIGHT_WEST_SHAPE);
		});

		private static final VoxelShape BOTTOM_STRAIGHT_EAST_SHAPE = Block.createCuboidShape(0.0D, 0.0D, 0.0D, 8.0D, 8.0D, 16.0D);
		private static final VoxelShape BOTTOM_STRAIGHT_WEST_SHAPE = Block.createCuboidShape(8.0D, 0.0D, 0.0D, 16.0D, 8.0D, 16.0D);
		private static final VoxelShape BOTTOM_STRAIGHT_SOUTH_SHAPE = Block.createCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 8.0D, 8.0D);
		private static final VoxelShape BOTTOM_STRAIGHT_NORTH_SHAPE = Block.createCuboidShape(0.0D, 0.0D, 8.0D, 16.0D, 8.0D, 16.0D);
		private static final Map<Direction, VoxelShape> BOTTOM_STRAIGHT_SHAPES = Util.make(new DefaultReturnHashMap<>(BOTTOM_STRAIGHT_NORTH_SHAPE), (map) -> {
			map.put(Direction.SOUTH, BOTTOM_STRAIGHT_SOUTH_SHAPE);
			map.put(Direction.NORTH, BOTTOM_STRAIGHT_NORTH_SHAPE);
			map.put(Direction.EAST, BOTTOM_STRAIGHT_EAST_SHAPE);
			map.put(Direction.WEST, BOTTOM_STRAIGHT_WEST_SHAPE);
		});

		private final VoxelShape calculatedShape;

		public StairShapeHolder(int progress, StairShape shape, Direction direction, BlockHalf half) {
			boolean isBottom = half.equals(BlockHalf.BOTTOM);
			VoxelShape baseShape = isBottom ? StairShulkerBoxBlock.BOTTOM_SHAPE : StairShulkerBoxBlock.TOP_SHAPE;

			Box baseBox = baseShape.getBoundingBox();
			baseBox.stretch(0.0D, isBottom ? (0.5 * progress / 10) : (-0.5 * progress / 10), 0.0D);
			VoxelShape base = VoxelShapes.cuboid(baseBox);

			VoxelShape part = VoxelShapes.fullCube();
			switch (shape) {
			case STRAIGHT:
				part = isBottom ? TOP_STRAIGHT_SHAPES.get(direction) : BOTTOM_STRAIGHT_SHAPES.get(direction);
				break;
			case INNER_LEFT:
				break;
			case INNER_RIGHT:
				break;
			case OUTER_LEFT:
				break;
			case OUTER_RIGHT:
				break;
			default:
				break;
			}

			part.offset(0.0D, isBottom ? 1 : -1 * (progress / 10.0D) * 0.5D, 0.0D);

			this.calculatedShape = VoxelShapes.union(base, part);
		}

		private StairShapeHolder(int progress) {
			this(progress, StairShape.STRAIGHT, Direction.NORTH, BlockHalf.BOTTOM);
		}

		public VoxelShape getShape() {
			return this.calculatedShape;
		}
	}
}
