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

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.block.Waterloggable;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.enums.BlockHalf;
import net.minecraft.block.enums.StairShape;
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
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.IWorld;

import net.fabricmc.fabric.api.container.ContainerProviderRegistry;

import me.i509.fabric.bulkyshulkies.api.block.colored.ColoredHorizontalFacingShulkerBoxBlock;
import me.i509.fabric.bulkyshulkies.block.ShulkerBoxConstants;
import me.i509.fabric.bulkyshulkies.screen.ScreenHandlerKeys;
import me.i509.fabric.bulkyshulkies.mixin.accessor.StairsBlockAccessor;
import me.i509.fabric.bulkyshulkies.registry.ShulkerBlocks;

public class StairShulkerBoxBlock extends ColoredHorizontalFacingShulkerBoxBlock implements Waterloggable {
	public static final EnumProperty<StairShape> SHAPE = Properties.STAIR_SHAPE;
	public static final EnumProperty<BlockHalf> HALF = Properties.BLOCK_HALF;
	public static final BooleanProperty WATERLOGGED = Properties.WATERLOGGED;

	public static VoxelShape getShape(double animationProgress, BlockState state, BlockView blockView, BlockPos pos) {
		VoxelShape unmodified = StairShulkerBoxBlock.getBaseStairShape(state);

		if (animationProgress == 0.0D) {
			return unmodified;
		}

		VoxelShape offset = unmodified.offset(0.0D, state.get(StairShulkerBoxBlock.HALF).equals(BlockHalf.BOTTOM) ? 0.5 * animationProgress : -0.5 * animationProgress, 0.0D);
		VoxelShape base = state.get(StairShulkerBoxBlock.HALF) == BlockHalf.BOTTOM
				? Block.createCuboidShape(
					0.0D, 0.0D, 0.0D,
					16.0D, (8.0D * animationProgress), 16.0D
				)
				: Block.createCuboidShape(
					0.0D, 16.0D - (8.0D * animationProgress), 0.0D,
					16.0D, 16.0D, 16.0D
				);
		return VoxelShapes.union(offset, base);
		// TODO fix shape issue with upside down stairs (Lower tenary operator)
	}

	public static VoxelShape getBaseStairShape(BlockState state) {
		return (state.get(HALF) == BlockHalf.TOP ? StairsBlockAccessor.getTopShapes()
				: StairsBlockAccessor.getBottomShapes())[StairsBlockAccessor.getShapeIndices()[getShapeIndexIndex(state)]];
	}

	public StairShulkerBoxBlock(Settings settings, @Nullable DyeColor color) {
		super(settings, ShulkerBoxConstants.STAIR_SLOT_COUNT, color);
		this.setDefaultState(this.getStateManager().getDefaultState()
				.with(SHAPE, StairShape.STRAIGHT)
				.with(HALF, BlockHalf.BOTTOM)
				.with(FACING, Direction.NORTH)
				.with(WATERLOGGED, false)
		);
	}

	@Override
	public VoxelShape getOutlineShape(BlockState state, BlockView view, BlockPos pos, ShapeContext shapeContext) {
		BlockEntity blockEntity = view.getBlockEntity(pos);

		if (blockEntity instanceof StairShulkerBoxBlockEntity) {
			float f = ((StairShulkerBoxBlockEntity) blockEntity).getAnimationProgress(1.0F);
			return StairShulkerBoxBlock.getShape(f, state, view, pos);
		}

		return StairShulkerBoxBlock.getShape(0.0D, state, view, pos);
	}

	@Override
	protected void openScreen(BlockPos pos, PlayerEntity playerEntity, Text title) {
		ContainerProviderRegistry.INSTANCE.openContainer(ScreenHandlerKeys.SHULKER_SCROLLABLE_CONTAINER, playerEntity, (packetByteBuf -> {
			packetByteBuf.writeBlockPos(pos);
			packetByteBuf.writeText(title);
		}));
	}

	@Override
	public ItemStack getItemStack(@Nullable DyeColor color) {
		if (color == null) {
			return new ItemStack(ShulkerBlocks.STAIR_SHULKER_BOX);
		}

		switch (color) {
		case WHITE:
			return new ItemStack(ShulkerBlocks.WHITE_STAIR_SHULKER_BOX);
		case ORANGE:
			return new ItemStack(ShulkerBlocks.ORANGE_STAIR_SHULKER_BOX);
		case MAGENTA:
			return new ItemStack(ShulkerBlocks.MAGENTA_STAIR_SHULKER_BOX);
		case LIGHT_BLUE:
			return new ItemStack(ShulkerBlocks.LIGHT_BLUE_STAIR_SHULKER_BOX);
		case YELLOW:
			return new ItemStack(ShulkerBlocks.YELLOW_STAIR_SHULKER_BOX);
		case LIME:
			return new ItemStack(ShulkerBlocks.LIME_STAIR_SHULKER_BOX);
		case PINK:
			return new ItemStack(ShulkerBlocks.PINK_STAIR_SHULKER_BOX);
		case GRAY:
			return new ItemStack(ShulkerBlocks.GRAY_STAIR_SHULKER_BOX);
		case LIGHT_GRAY:
			return new ItemStack(ShulkerBlocks.LIGHT_GRAY_STAIR_SHULKER_BOX);
		case CYAN:
			return new ItemStack(ShulkerBlocks.CYAN_STAIR_SHULKER_BOX);
		case PURPLE:
		default:
			return new ItemStack(ShulkerBlocks.PURPLE_STAIR_SHULKER_BOX);
		case BLUE:
			return new ItemStack(ShulkerBlocks.BLUE_STAIR_SHULKER_BOX);
		case BROWN:
			return new ItemStack(ShulkerBlocks.BROWN_STAIR_SHULKER_BOX);
		case GREEN:
			return new ItemStack(ShulkerBlocks.GREEN_STAIR_SHULKER_BOX);
		case RED:
			return new ItemStack(ShulkerBlocks.RED_STAIR_SHULKER_BOX);
		case BLACK:
			return new ItemStack(ShulkerBlocks.BLACK_STAIR_SHULKER_BOX);
		}
	}

	@Override
	public Box getLidCollisionBox(Direction facing) {
		return ShulkerLidCollisions.getLidCollisionBox(new BlockPos(0, 1, 0), facing);
	}

	@Override
	public BlockEntity createBlockEntity(BlockView view) {
		return new StairShulkerBoxBlockEntity(this.color);
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
		return blockState.with(SHAPE, StairsBlockAccessor.getStairShape(blockState, ctx.getWorld(), blockPos));
	}

	@Override
	public BlockState getStateForNeighborUpdate(BlockState state, Direction facing, BlockState neighborState, IWorld world, BlockPos pos, BlockPos neighborPos) {
		if (state.get(WATERLOGGED)) {
			world.getFluidTickScheduler().schedule(pos, Fluids.WATER, Fluids.WATER.getTickRate(world));
		}

		return facing.getAxis().isHorizontal() ? state.with(SHAPE, StairsBlockAccessor.getStairShape(state, world, pos)) : super.getStateForNeighborUpdate(state, facing, neighborState, world, pos, neighborPos);
	}

	private static int getShapeIndexIndex(BlockState state) {
		return state.get(SHAPE).ordinal() * 4 + state.get(FACING).getHorizontal();
	}
}
