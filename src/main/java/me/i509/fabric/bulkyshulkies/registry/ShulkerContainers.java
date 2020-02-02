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
import net.minecraft.container.ShulkerBoxSlot;
import net.minecraft.container.Slot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.PacketByteBuf;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import me.i509.fabric.bulkyshulkies.api.block.base.AbstractShulkerBoxBlock;
import me.i509.fabric.bulkyshulkies.api.player.EnderSlabBridge;
import me.i509.fabric.bulkyshulkies.block.ender.EnderSlabBoxBE;
import me.i509.fabric.bulkyshulkies.container.GenericContainer9x7;
import me.i509.fabric.bulkyshulkies.container.GenericContainer11x7;
import me.i509.fabric.bulkyshulkies.container.GenericContainer13x7;
import me.i509.fabric.bulkyshulkies.container.ScrollableContainer;
import me.i509.fabric.bulkyshulkies.inventory.EnderSlabInventory;

public class ShulkerContainers {
	private ShulkerContainers() {
		// NO-OP
	}

	public static void init() {
		// NO-OP
	}

	public static GenericContainer13x7 create13x7(int syncId, Identifier identifier, PlayerEntity player, PacketByteBuf buf) {
		BlockPos pos = buf.readBlockPos();
		Text name = buf.readText();
		World world = player.getEntityWorld();
		return new GenericContainer13x7(syncId, ShulkerBoxSlot::new, player.inventory, AbstractShulkerBoxBlock.getInventoryStatically(world, pos), name);
	}

	public static GenericContainer11x7 create11x7(int syncId, Identifier identifier, PlayerEntity player, PacketByteBuf buf) {
		BlockPos pos = buf.readBlockPos();
		Text name = buf.readText();
		World world = player.getEntityWorld();
		return new GenericContainer11x7(syncId, ShulkerBoxSlot::new, player.inventory, AbstractShulkerBoxBlock.getInventoryStatically(world, pos), name);
	}

	public static GenericContainer9x7 create9x7(int syncId, Identifier identifier, PlayerEntity player, PacketByteBuf buf) {
		BlockPos pos = buf.readBlockPos();
		Text name = buf.readText();
		World world = player.getEntityWorld();
		return new GenericContainer9x7(syncId, ShulkerBoxSlot::new, player.inventory, AbstractShulkerBoxBlock.getInventoryStatically(world, pos), name);
	}

	public static ScrollableContainer createScrollable(int syncId, Identifier identifier, PlayerEntity player, PacketByteBuf buf) {
		BlockPos pos = buf.readBlockPos();
		Text name = buf.readText();
		World world = player.getEntityWorld();
		return new ScrollableContainer(syncId, ShulkerBoxSlot::new, player.inventory, AbstractShulkerBoxBlock.getInventoryStatically(world, pos), name);
	}

	public static ScrollableContainer createEnderSlab(int syncId, Identifier identifier, PlayerEntity player, PacketByteBuf buf) {
		BlockPos pos = buf.readBlockPos();
		Text name = buf.readText();
		World world = player.getEntityWorld();

		EnderSlabInventory slab = ((EnderSlabBridge) player).bridge$getEnderSlabInventory();
		BlockEntity blockEntity = world.getBlockEntity(pos);
		slab.setCurrentBlockEntity(blockEntity instanceof EnderSlabBoxBE ? (EnderSlabBoxBE) blockEntity : null);

		return new ScrollableContainer(syncId, Slot::new, player.inventory, slab, name);
	}
}
