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

package me.i509.fabric.bulkyshulkies.block.material.gold;

import org.jetbrains.annotations.Nullable;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.DyeColor;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;

import net.fabricmc.fabric.api.container.ContainerProviderRegistry;

import me.i509.fabric.bulkyshulkies.api.block.Abstract1x1ShulkerBoxBlock;
import me.i509.fabric.bulkyshulkies.block.ShulkerBoxConstants;
import me.i509.fabric.bulkyshulkies.container.ContainerKeys;
import me.i509.fabric.bulkyshulkies.registry.ShulkerBlocks;

public class GoldShulkerBoxBlock extends Abstract1x1ShulkerBoxBlock {
	public GoldShulkerBoxBlock(Settings settings, @Nullable DyeColor color) {
		super(settings, ShulkerBoxConstants.GOLD_SLOT_COUNT, color);
	}

	@Override
	public BlockEntity createBlockEntity(BlockView blockView) {
		return new GoldShulkerBoxBE(this.getColor());
	}

	@Override
	protected void openContainer(BlockPos pos, PlayerEntity playerEntity, Text displayName) {
		ContainerProviderRegistry.INSTANCE.openContainer(ContainerKeys.SHULKER_9x7_CONTAINER, playerEntity, (packetByteBuf -> {
			packetByteBuf.writeBlockPos(pos);
			packetByteBuf.writeText(displayName);
		}));
	}

	@Override
	public ItemStack getItemStack(@Nullable DyeColor color) {
		if (color == null) {
			return new ItemStack(ShulkerBlocks.GOLD_SHULKER_BOX);
		}

		switch (color) {
		case WHITE:
			return new ItemStack(ShulkerBlocks.WHITE_GOLD_SHULKER_BOX);
		case ORANGE:
			return new ItemStack(ShulkerBlocks.ORANGE_GOLD_SHULKER_BOX);
		case MAGENTA:
			return new ItemStack(ShulkerBlocks.MAGENTA_GOLD_SHULKER_BOX);
		case LIGHT_BLUE:
			return new ItemStack(ShulkerBlocks.LIGHT_BLUE_GOLD_SHULKER_BOX);
		case YELLOW:
			return new ItemStack(ShulkerBlocks.YELLOW_GOLD_SHULKER_BOX);
		case LIME:
			return new ItemStack(ShulkerBlocks.LIME_GOLD_SHULKER_BOX);
		case PINK:
			return new ItemStack(ShulkerBlocks.PINK_GOLD_SHULKER_BOX);
		case GRAY:
			return new ItemStack(ShulkerBlocks.GRAY_GOLD_SHULKER_BOX);
		case LIGHT_GRAY:
			return new ItemStack(ShulkerBlocks.LIGHT_GRAY_GOLD_SHULKER_BOX);
		case CYAN:
			return new ItemStack(ShulkerBlocks.CYAN_GOLD_SHULKER_BOX);
		case PURPLE:
		default:
			return new ItemStack(ShulkerBlocks.PURPLE_GOLD_SHULKER_BOX);
		case BLUE:
			return new ItemStack(ShulkerBlocks.BLUE_GOLD_SHULKER_BOX);
		case BROWN:
			return new ItemStack(ShulkerBlocks.BROWN_GOLD_SHULKER_BOX);
		case GREEN:
			return new ItemStack(ShulkerBlocks.GREEN_GOLD_SHULKER_BOX);
		case RED:
			return new ItemStack(ShulkerBlocks.RED_GOLD_SHULKER_BOX);
		case BLACK:
			return new ItemStack(ShulkerBlocks.BLACK_GOLD_SHULKER_BOX);
		}
	}
}
