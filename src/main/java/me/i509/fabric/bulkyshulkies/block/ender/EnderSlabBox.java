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

package me.i509.fabric.bulkyshulkies.block.ender;

import org.jetbrains.annotations.Nullable;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.stat.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Direction;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

import me.i509.fabric.bulkyshulkies.api.block.base.AbstractShulkerBoxBE;
import me.i509.fabric.bulkyshulkies.api.block.base.BaseShulkerBlockEntity;
import me.i509.fabric.bulkyshulkies.block.cursed.slab.CursedSlabShulkerBox;
import me.i509.fabric.bulkyshulkies.block.cursed.slab.CursedSlabShulkerBoxBE;
import me.i509.fabric.bulkyshulkies.registry.ShulkerBlocks;

public class EnderSlabBox extends CursedSlabShulkerBox {
	public EnderSlabBox(Settings settings, @Nullable DyeColor color) {
		super(settings, color);
	}

	// TODO: Instead of reading the BE for data, we instead refer to an ender like chest.
	@Override
	public ActionResult onUse(BlockState blockState, World world, BlockPos blockPos, PlayerEntity player, Hand hand, BlockHitResult blockHitResult) {
		if (world.isClient) {
			return ActionResult.SUCCESS;
		} else if (player.isSpectator()) {
			return ActionResult.SUCCESS;
		} else {
			BlockEntity blockEntity = world.getBlockEntity(blockPos);

			if (blockEntity instanceof AbstractShulkerBoxBE) {
				Direction facing = blockState.get(FACING);
				AbstractShulkerBoxBE cursedBlockEntity = (AbstractShulkerBoxBE) blockEntity;
				boolean shouldOpen;

				if (cursedBlockEntity.getAnimationStage() == BaseShulkerBlockEntity.AnimationStatus.CLOSED) {
					Box openBox = getOpenBox(facing);
					shouldOpen = world.doesNotCollide(openBox.offset(blockPos.offset(facing)));
				} else {
					shouldOpen = true;
				}

				if (shouldOpen) {
					if (cursedBlockEntity.checkUnlocked(player)) {
						cursedBlockEntity.checkLootInteraction(player);
						openContainer(blockPos, player, cursedBlockEntity.getDisplayName());
						player.incrementStat(Stats.OPEN_SHULKER_BOX);
					}
				}

				return ActionResult.SUCCESS;
			} else {
				return ActionResult.PASS;
			}
		}
	}

	@Override
	public BlockEntity createBlockEntity(BlockView blockView) {
		return new CursedSlabShulkerBoxBE(this.getColor());
	}

	@Override
	public ItemStack getItemStack(@Nullable DyeColor color) {
		if (color == null) {
			return new ItemStack(ShulkerBlocks.SLAB_SHULKER_BOX);
		}

		// TODO: Drop the correct ender slab box
		switch (color) {
		case WHITE:
			return new ItemStack(ShulkerBlocks.WHITE_SLAB_SHULKER_BOX);
		case ORANGE:
			return new ItemStack(ShulkerBlocks.ORANGE_SLAB_SHULKER_BOX);
		case MAGENTA:
			return new ItemStack(ShulkerBlocks.MAGENTA_SLAB_SHULKER_BOX);
		case LIGHT_BLUE:
			return new ItemStack(ShulkerBlocks.LIGHT_BLUE_SLAB_SHULKER_BOX);
		case YELLOW:
			return new ItemStack(ShulkerBlocks.YELLOW_SLAB_SHULKER_BOX);
		case LIME:
			return new ItemStack(ShulkerBlocks.LIME_SLAB_SHULKER_BOX);
		case PINK:
			return new ItemStack(ShulkerBlocks.PINK_SLAB_SHULKER_BOX);
		case GRAY:
			return new ItemStack(ShulkerBlocks.GRAY_SLAB_SHULKER_BOX);
		case LIGHT_GRAY:
			return new ItemStack(ShulkerBlocks.LIGHT_GRAY_SLAB_SHULKER_BOX);
		case CYAN:
			return new ItemStack(ShulkerBlocks.CYAN_SLAB_SHULKER_BOX);
		case PURPLE:
		default:
			return new ItemStack(ShulkerBlocks.PURPLE_SLAB_SHULKER_BOX);
		case BLUE:
			return new ItemStack(ShulkerBlocks.BLUE_SLAB_SHULKER_BOX);
		case BROWN:
			return new ItemStack(ShulkerBlocks.BROWN_SLAB_SHULKER_BOX);
		case GREEN:
			return new ItemStack(ShulkerBlocks.GREEN_SLAB_SHULKER_BOX);
		case RED:
			return new ItemStack(ShulkerBlocks.RED_SLAB_SHULKER_BOX);
		case BLACK:
			return new ItemStack(ShulkerBlocks.BLACK_SLAB_SHULKER_BOX);
		}
	}
}
