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

package me.i509.fabric.bulkyshulkies.block.ender;

import org.checkerframework.checker.nullness.qual.Nullable;

import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemConvertible;
import net.minecraft.text.Text;
import net.minecraft.util.DyeColor;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;

import net.fabricmc.fabric.api.container.ContainerProviderRegistry;

import me.i509.fabric.bulkyshulkies.api.block.slab.AbstractShulkerSlabBlock;
import me.i509.fabric.bulkyshulkies.screen.ScreenHandlerKeys;
import me.i509.fabric.bulkyshulkies.registry.ShulkerBlocks;

public class EnderSlabBlock extends AbstractShulkerSlabBlock {
	public EnderSlabBlock(Settings settings) {
		super(settings);
	}

	@Override
	protected void openScreen(BlockPos pos, PlayerEntity playerEntity, Text title) {
		ContainerProviderRegistry.INSTANCE.openContainer(ScreenHandlerKeys.ENDER_SLAB, playerEntity, (packetByteBuf -> {
			packetByteBuf.writeBlockPos(pos);
			packetByteBuf.writeText(title);
		}));
	}

	@Override
	public BlockEntity createBlockEntity(BlockView blockView) {
		return new EnderSlabBoxBlockEntity();
	}

	@Override
	public ItemConvertible getItem(@Nullable DyeColor color) {
		return ShulkerBlocks.ENDER_SLAB_BOX;
	}
}
