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

package me.i509.fabric.bulkyshulkies.registry;

import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.screen.slot.ShulkerBoxSlot;
import net.minecraft.screen.slot.Slot;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import me.i509.fabric.bulkyshulkies.api.block.base.AbstractShulkerBoxBlock;
import me.i509.fabric.bulkyshulkies.api.player.EnderSlabBridge;
import me.i509.fabric.bulkyshulkies.block.ender.EnderSlabBoxBE;
import me.i509.fabric.bulkyshulkies.screen.GenericScreenHandler9x7;
import me.i509.fabric.bulkyshulkies.screen.GenericScreenHandler11x7;
import me.i509.fabric.bulkyshulkies.screen.GenericScreenHandler13x7;
import me.i509.fabric.bulkyshulkies.screen.ScrollableScreenHandler;
import me.i509.fabric.bulkyshulkies.inventory.EnderSlabInventory;

public class ShulkerScreenHandlers {
	private ShulkerScreenHandlers() {
		// NO-OP
	}

	public static void init() {
		// NO-OP
	}

	public static GenericScreenHandler13x7 create13x7(int syncId, Identifier identifier, PlayerEntity player, PacketByteBuf buf) {
		BlockPos pos = buf.readBlockPos();
		Text name = buf.readText();
		World world = player.getEntityWorld();
		return new GenericScreenHandler13x7(syncId, ShulkerBoxSlot::new, player.inventory, AbstractShulkerBoxBlock.getInventoryStatically(world, pos), name);
	}

	public static GenericScreenHandler11x7 create11x7(int syncId, Identifier identifier, PlayerEntity player, PacketByteBuf buf) {
		BlockPos pos = buf.readBlockPos();
		Text name = buf.readText();
		World world = player.getEntityWorld();
		return new GenericScreenHandler11x7(syncId, ShulkerBoxSlot::new, player.inventory, AbstractShulkerBoxBlock.getInventoryStatically(world, pos), name);
	}

	public static GenericScreenHandler9x7 create9x7(int syncId, Identifier identifier, PlayerEntity player, PacketByteBuf buf) {
		BlockPos pos = buf.readBlockPos();
		Text name = buf.readText();
		World world = player.getEntityWorld();
		return new GenericScreenHandler9x7(syncId, ShulkerBoxSlot::new, player.inventory, AbstractShulkerBoxBlock.getInventoryStatically(world, pos), name);
	}

	public static ScrollableScreenHandler createScrollable(int syncId, Identifier identifier, PlayerEntity player, PacketByteBuf buf) {
		BlockPos pos = buf.readBlockPos();
		Text name = buf.readText();
		World world = player.getEntityWorld();
		return new ScrollableScreenHandler(syncId, ShulkerBoxSlot::new, player.inventory, AbstractShulkerBoxBlock.getInventoryStatically(world, pos), name);
	}

	public static ScrollableScreenHandler createEnderSlab(int syncId, Identifier identifier, PlayerEntity player, PacketByteBuf buf) {
		BlockPos pos = buf.readBlockPos();
		Text name = buf.readText();
		World world = player.getEntityWorld();

		EnderSlabInventory slab = ((EnderSlabBridge) player).bridge$getEnderSlabInventory();
		BlockEntity blockEntity = world.getBlockEntity(pos);
		slab.setCurrentBlockEntity(blockEntity instanceof EnderSlabBoxBE ? (EnderSlabBoxBE) blockEntity : null);

		return new ScrollableScreenHandler(syncId, Slot::new, player.inventory, slab, name);
	}
}
