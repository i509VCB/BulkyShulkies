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

package me.i509.fabric.cursedshulkerboxes.api.block.multi;

import me.i509.fabric.cursedshulkerboxes.api.block.base.AbstractCursedShulkerBoxBlock;
import me.i509.fabric.cursedshulkerboxes.api.block.base.AbstractCursedShulkerBoxBlockEntity;
import me.i509.fabric.cursedshulkerboxes.api.block.base.BaseShulkerBlockEntity;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.ShulkerBoxBlockEntity;
import net.minecraft.block.enums.DoubleBlockHalf;
import net.minecraft.entity.EntityContext;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.state.StateFactory;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.DyeColor;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.ViewableWorld;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public abstract class AbstractCursedShulkerBoxMultiBlock extends AbstractCursedShulkerBoxBlock implements MultiShulkerBoxBlock {
    /**
     * TODO:
     *
     * Make it so it will only access the bottom blockentity to open the inventory, can still open from top but actual inventory will be located in bottom.
     *
     * Do isObstructionFree check from the bottom block only, first verify the bottom block is actually our type or things like debug world will crash.
     *
     * Cannot place on a wall in midair yet.
     *
     * When block is placed sideways or vertically, verify an entity isn't in the way before going letting the place occur.
     *
     * Fix issue where placing the box will overwrite the block above it.
     *
     * Breaking either side where an item is present will not break the other side.
     *
     * When opening from bottom, also expand the upper halve's box shape.
     *
     * Drop the shulker box when broken.
     *
     * Item Texture should be the tall box (custom item renderer needed)
     *
     * Piston pushing is broken, when one half is pushed and destroyed, make sure the other half is removed.
     */
    public static final EnumProperty<DoubleBlockHalf> HALF = Properties.DOUBLE_BLOCK_HALF;

    protected AbstractCursedShulkerBoxMultiBlock(Settings settings, int slotCount, @Nullable DyeColor color) {
        super(settings, slotCount, color);
        this.setDefaultState(getStateFactory().getDefaultState().with(FACING, Direction.UP).with(HALF, DoubleBlockHalf.LOWER));
    }

    @Override
    public VoxelShape getOutlineShape(BlockState blockState, BlockView blockView, BlockPos blockPos, EntityContext entityContext) {
        if(blockState.get(HALF) == DoubleBlockHalf.LOWER) {
            return VoxelShapes.fullCube();
        }

        BlockEntity blockEntity = blockView.getBlockEntity(blockPos);
        return blockEntity instanceof BaseShulkerBlockEntity ? VoxelShapes.cuboid(((BaseShulkerBlockEntity)blockEntity).getBoundingBox(blockState)) : VoxelShapes.fullCube();
    }

    @Override
    public boolean isBottom(BlockState state) {
        return state.get(HALF) == DoubleBlockHalf.LOWER;
    }

    public boolean canPlaceAt(BlockState blockState, ViewableWorld viewableWorld, BlockPos blockPos) { // TODO for bug relating to overwriting block above when it shouldn't place at all
        BlockPos down = blockPos.down();
        BlockState belowState = viewableWorld.getBlockState(down);

        return blockState.get(HALF) == DoubleBlockHalf.LOWER ? belowState.isSideSolidFullSquare(viewableWorld, down, Direction.UP) : belowState.getBlock() == this;
    }

    public boolean isObstructionFree(BaseShulkerBlockEntity blockEntity, Direction facing, World world, BlockPos blockPos) {
        if (blockEntity.getAnimationStage() == ShulkerBoxBlockEntity.AnimationStage.CLOSED) {
            Box openBox = getOpenBox(facing, world, blockPos);
            if(world.getBlockState(blockPos).get(HALF) == DoubleBlockHalf.LOWER) {
                return world.doesNotCollide(openBox.offset(blockPos.offset(facing, 2)));
            }

            return world.doesNotCollide(openBox.offset(blockPos.offset(facing)));

        } else {
            return true;
        }
    }

    public final Box getOpenBox(Direction facing) {
        return VoxelShapes.fullCube().getBoundingBox();
    }

    protected abstract Box getOpenBox(Direction facing, World world, BlockPos blockPos);

    @Override
    public BlockState getPlacementState(ItemPlacementContext placementContext) {
        return this.getDefaultState().with(FACING, placementContext.getSide()).with(HALF, DoubleBlockHalf.LOWER);
    }

    public void onPlaced(World world, BlockPos blockPos, BlockState blockState, @Nullable LivingEntity livingEntity, ItemStack itemStack) {
        world.setBlockState(blockPos.offset(blockState.get(FACING)), blockState.with(HALF, DoubleBlockHalf.UPPER), 3); // Directionality of place checks here.
        if (itemStack.hasCustomName()) {
            BlockEntity blockEntity = world.getBlockEntity(blockPos.up());
            if (blockEntity instanceof AbstractCursedShulkerBoxBlockEntity) {
                ((AbstractCursedShulkerBoxBlockEntity)blockEntity).setCustomName(itemStack.getName());
            }
        }

        super.onPlaced(world, blockPos, blockState, livingEntity, itemStack);
    }

    public void onBreak(World world, BlockPos blockPos, BlockState blockState, PlayerEntity playerEntity) {
        DoubleBlockHalf brokeBlockHalf = blockState.get(HALF);
        Direction facing = blockState.get(FACING);

        BlockPos otherHalf = brokeBlockHalf == DoubleBlockHalf.LOWER ? blockPos.offset(facing) : blockPos.offset(facing.getOpposite());
        BlockState otherBlockState = world.getBlockState(otherHalf);

        if (otherBlockState.getBlock() == this && otherBlockState.get(HALF) != brokeBlockHalf) {
            world.setBlockState(otherHalf, Blocks.AIR.getDefaultState(), 35);
            world.playLevelEvent(playerEntity, 2001, otherHalf, Block.getRawIdFromState(otherBlockState));
            ItemStack mainHandStack = playerEntity.getMainHandStack();

            if (!world.isClient && !playerEntity.isCreative()) {
                Block.dropStacks(blockState, world, blockPos, null, playerEntity, mainHandStack);
                Block.dropStacks(otherBlockState, world, otherHalf, null, playerEntity, mainHandStack);
            }
        }

        super.onBreak(world, blockPos, blockState, playerEntity);
    }

    protected void appendProperties(StateFactory.Builder<Block, BlockState> stateFactory) {
        super.appendProperties(stateFactory);
        stateFactory.add(HALF);
    }
}
