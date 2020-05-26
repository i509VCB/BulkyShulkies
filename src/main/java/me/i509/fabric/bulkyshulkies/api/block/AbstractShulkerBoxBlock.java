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

package me.i509.fabric.bulkyshulkies.api.block;

import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import me.i509.fabric.bulkyshulkies.api.block.base.BasicShulkerBlock;
import me.i509.fabric.bulkyshulkies.api.block.base.InventoryShulkerBoxBlock;
import me.i509.fabric.bulkyshulkies.api.block.entity.inventory.AbstractShulkerBoxBlockEntity;

/**
 * Represents an abstract implementation of a shulker box with no strict physical size or inventory size.
 */
public abstract class AbstractShulkerBoxBlock extends BlockWithEntity implements BasicShulkerBlock {
	protected AbstractShulkerBoxBlock(Settings settings) {
		super(settings);
	}

	@Override
	public BlockRenderType getRenderType(BlockState blockState) {
		return BlockRenderType.ENTITYBLOCK_ANIMATED;
	}

	@Override
	public void onStateReplaced(BlockState blockState, World world, BlockPos blockPos, BlockState otherBlockState, boolean notify) {
		if (blockState.getBlock() != otherBlockState.getBlock()) {
			BlockEntity blockEntity = world.getBlockEntity(blockPos);

			if (blockEntity instanceof AbstractShulkerBoxBlockEntity) {
				world.updateComparators(blockPos, blockState.getBlock());
			}

			super.onStateReplaced(blockState, world, blockPos, otherBlockState, notify);
		}
	}

	@Override
	public PistonBehavior getPistonBehavior(BlockState blockState) {
		return PistonBehavior.DESTROY;
	}

	@Override
	public boolean hasComparatorOutput(BlockState blockState) {
		return this instanceof InventoryShulkerBoxBlock;
	}

	@Override
	public int getComparatorOutput(BlockState blockState, World world, BlockPos blockPos) {
		if (world.getBlockEntity(blockPos) instanceof Inventory) {
			return ScreenHandler.calculateComparatorOutput((Inventory) world.getBlockEntity(blockPos));
		}

		return super.getComparatorOutput(blockState, world, blockPos);
	}

	/**
	 * Migrate to ScreenHandler based system in the future.
	 */
	@Deprecated
	protected abstract void openScreen(BlockPos pos, PlayerEntity playerEntity, Text title);
}
