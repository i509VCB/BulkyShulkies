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

package me.i509.fabric.bulkyshulkies.event;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.CauldronBlock;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.stat.Stats;
import net.minecraft.util.ActionResult;

import net.fabricmc.fabric.api.event.player.UseBlockCallback;

import me.i509.fabric.bulkyshulkies.api.block.old.colored.ColoredShulkerBoxBlock;

public final class ShulkerEvents {
	public static void init() {
		// NO-OP
	}

	static {
		UseBlockCallback.EVENT.register((player, world, hand, hitResult) -> {
			if (world.isClient()) {
				return ActionResult.PASS;
			}

			final BlockState blockState = world.getBlockState(hitResult.getBlockPos());

			// Test if we are using a cauldron
			if (blockState.getBlock() instanceof CauldronBlock) { // TODO: Add a hook method to verify we have a cauldron for custom cauldrons
				final ItemStack handStack = player.getStackInHand(hand);

				if (handStack.getItem() instanceof BlockItem) {
					final Block block = ((BlockItem) handStack.getItem()).getBlock();

					if (block instanceof ColoredShulkerBoxBlock) {
						// Test Cauldron water level
						// TODO: Get level from custom cauldron?
						final int level = blockState.get(CauldronBlock.LEVEL);

						if (level == 0) { // Pass on if we have no water
							return ActionResult.PASS;
						}

						final ItemStack newStack = ((ColoredShulkerBoxBlock) block).getItemStack(null); // Get the uncolored item

						if (handStack.hasTag()) {
							newStack.setTag(handStack.getTag().copy());
						}

						// Copied logic from cauldron
						player.setStackInHand(hand, newStack);
						// TODO: Custom level decrement?
						((CauldronBlock) blockState.getBlock()).setLevel(world, hitResult.getBlockPos(), blockState, level - 1);
						player.incrementStat(Stats.CLEAN_SHULKER_BOX);

						return ActionResult.SUCCESS;
					}
				}
			}

			return ActionResult.PASS;
		});
	}
}
