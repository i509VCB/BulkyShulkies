/*
 * MIT License
 *
 * Copyright (c) 2019, 2020 i509VCB
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

package me.i509.bulkyshulkies.mod.item;

import me.i509.bulkyshulkies.api.ShulkerBoxType;
import me.i509.bulkyshulkies.api.block.ShulkerBox;
import me.i509.bulkyshulkies.api.block.ShulkerBoxColor;
import me.i509.bulkyshulkies.mod.registry.ShulkerComponents;
import org.jetbrains.annotations.Nullable;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.LeveledCauldronBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.stat.Stats;
import net.minecraft.util.ActionResult;

public class ShulkerBlockItem extends BlockItem {
	private final ShulkerBoxType type;

	public ShulkerBlockItem(ShulkerBoxType type, Settings settings) {
		super(type.getBlock(), settings);
		this.type = type;
	}

	public ShulkerBox getShulkerBoxBlock() {
		return this.type.getBlock();
	}

	@Override
	public ActionResult useOnBlock(ItemUsageContext context) {
		if (context.getWorld().isClient()) { // Fail fast on client world
			return super.useOnBlock(context);
		}

		@Nullable
		final PlayerEntity player = context.getPlayer();

		if (player != null) {
			final BlockState state = context.getWorld().getBlockState(context.getBlockPos());

			// Test if we are using a cauldron
			// TODO: custom cauldrons?
			if (state.isOf(Blocks.WATER_CAULDRON)) {
				final ItemStack handStack = player.getStackInHand(context.getHand());

				if (handStack.getItem() instanceof BlockItem) {
					final Block block = ((BlockItem) handStack.getItem()).getBlock();

					if (block instanceof ShulkerBox) {
						final ItemStack newStack = handStack.copy();

						return ShulkerComponents.SHULKER_BOX_COLOR.maybeGet(newStack).map(component -> {
							component.setColor(ShulkerBoxColor.NONE);
							player.setStackInHand(context.getHand(), newStack);

							// TODO: Custom level decrement?
							// Reduce water level of cauldron
							LeveledCauldronBlock.decrementFluidLevel(state, context.getWorld(), context.getBlockPos());
							player.incrementStat(Stats.CLEAN_SHULKER_BOX);

							return ActionResult.SUCCESS;
						}).orElse(super.useOnBlock(context));
					}
				}
			}
		}

		// Fallback
		return super.useOnBlock(context);
	}

	@Override
	public boolean hasStoredInventory() {
		return this.type.hasInventory();
	}
}
