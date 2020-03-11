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

package me.i509.fabric.bulkyshulkies.extension;

import static net.kyrptonaught.quickshulker.api.QuickOpenableRegistry.register;

import net.kyrptonaught.quickshulker.api.Util;
import net.kyrptonaught.quickshulker.api.ItemStackInventory;
import net.kyrptonaught.quickshulker.api.RegisterQuickShulker;

import net.minecraft.block.Block;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.slot.ShulkerBoxSlot;
import net.minecraft.screen.slot.Slot;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.PacketByteBuf;

import net.fabricmc.fabric.api.container.ContainerProviderRegistry;

import me.i509.fabric.bulkyshulkies.BulkyShulkies;
import me.i509.fabric.bulkyshulkies.api.block.base.AbstractShulkerBoxBlock;
import me.i509.fabric.bulkyshulkies.api.player.EnderSlabBridge;
import me.i509.fabric.bulkyshulkies.block.cursed.slab.CursedSlabShulkerBox;
import me.i509.fabric.bulkyshulkies.block.cursed.stair.StairShulkerBoxBlock;
import me.i509.fabric.bulkyshulkies.block.ender.EnderSlabBoxBlock;
import me.i509.fabric.bulkyshulkies.block.material.copper.CopperShulkerBoxBlock;
import me.i509.fabric.bulkyshulkies.block.material.diamond.DiamondShulkerBoxBlock;
import me.i509.fabric.bulkyshulkies.block.material.gold.GoldShulkerBoxBlock;
import me.i509.fabric.bulkyshulkies.block.material.iron.IronShulkerBoxBlock;
import me.i509.fabric.bulkyshulkies.block.material.obsidian.ObsidianShulkerBoxBlock;
import me.i509.fabric.bulkyshulkies.block.material.platinum.PlatinumShulkerBoxBlock;
import me.i509.fabric.bulkyshulkies.block.material.silver.SilverShulkerBoxBlock;
import me.i509.fabric.bulkyshulkies.block.missing.MissingTexBoxBlock;
import me.i509.fabric.bulkyshulkies.config.section.quickshulker.QuickShulkerSection;
import me.i509.fabric.bulkyshulkies.screen.GenericScreenHandler11x7;
import me.i509.fabric.bulkyshulkies.screen.GenericScreenHandler13x7;
import me.i509.fabric.bulkyshulkies.screen.GenericScreenHandler9x7;
import me.i509.fabric.bulkyshulkies.screen.ScrollableScreenHandler;
import me.i509.fabric.bulkyshulkies.inventory.EnderSlabInventory;
import me.i509.fabric.bulkyshulkies.registry.ShulkerTexts;

public class QuickShulkerHook implements RegisterQuickShulker {
	public static final Identifier QS$SHULKER_SCROLLABLE_CONTAINER = BulkyShulkies.id("qs_shulker_scrollable_container");
	public static final Identifier QS$SHULKER_9x7_CONTAINER = BulkyShulkies.id("qs_shulker_container_9x7");
	public static final Identifier QS$SHULKER_11x7_CONTAINER = BulkyShulkies.id("qs_shulker_container_11x7");
	public static final Identifier QS$SHULKER_13x7_CONTAINER = BulkyShulkies.id("qs_shulker_container_13x7");
	public static final Identifier QS$ENDER_SLAB = BulkyShulkies.id("qs_ender_slab_container");

	@Override
	public void registerProviders() {
		ContainerProviderRegistry.INSTANCE.registerFactory(QS$SHULKER_SCROLLABLE_CONTAINER, QuickShulkerHook::createScrollable);
		ContainerProviderRegistry.INSTANCE.registerFactory(QS$SHULKER_9x7_CONTAINER, QuickShulkerHook::create9x7);
		ContainerProviderRegistry.INSTANCE.registerFactory(QS$SHULKER_11x7_CONTAINER, QuickShulkerHook::create11x7);
		ContainerProviderRegistry.INSTANCE.registerFactory(QS$SHULKER_13x7_CONTAINER, QuickShulkerHook::create13x7);
		ContainerProviderRegistry.INSTANCE.registerFactory(QS$ENDER_SLAB, QuickShulkerHook::createEnderSlab);

		QuickShulkerSection config = BulkyShulkies.getInstance().getConfig().getExtensions().getQuickShulker();

		if (config.canOpenCopper()) {
			register(QuickShulkerHook::openCopperBox, CopperShulkerBoxBlock.class);
		}

		if (config.canOpenIron()) {
			register(QuickShulkerHook::openIronBox, IronShulkerBoxBlock.class);
		}

		if (config.canOpenSilver()) {
			register(QuickShulkerHook::openSilverBox, SilverShulkerBoxBlock.class);
		}

		if (config.canOpenGold()) {
			register(QuickShulkerHook::openGoldBox, GoldShulkerBoxBlock.class);
		}

		if (config.canOpenDiamond()) {
			register(QuickShulkerHook::openDiamondBox, DiamondShulkerBoxBlock.class);
		}

		if (config.canOpenObsidian()) {
			register(QuickShulkerHook::openObsidianBox, ObsidianShulkerBoxBlock.class);
		}

		if (config.canOpenPlatinum()) {
			register(QuickShulkerHook::openPlatinumBox, PlatinumShulkerBoxBlock.class);
		}

		if (config.canOpenSlab()) {
			register(QuickShulkerHook::openSlabBox, CursedSlabShulkerBox.class);
		}

		if (config.canOpenEnderSlab()) {
			register(QuickShulkerHook::openEnderSlab, EnderSlabBoxBlock.class);
		}

		if (config.canOpenMissingTex()) {
			register(QuickShulkerHook::openMissingTex, MissingTexBoxBlock.class);
		}

		if (config.canOpenStair()) {
			register(QuickShulkerHook::openStair, StairShulkerBoxBlock.class);
		}
	}

	public static GenericScreenHandler13x7 create13x7(int syncId, Identifier identifier, PlayerEntity player, PacketByteBuf buf) {
		int invSlot = buf.readInt();
		Text name = buf.readText();
		ItemStack stack = player.inventory.getInvStack(invSlot);
		return new GenericScreenHandler13x7(syncId, ShulkerBoxSlot::new, player.inventory, new ItemStackInventory(stack, getSlotCount(stack)), name);
	}

	public static GenericScreenHandler11x7 create11x7(int syncId, Identifier identifier, PlayerEntity player, PacketByteBuf buf) {
		int invSlot = buf.readInt();
		Text name = buf.readText();
		ItemStack stack = player.inventory.getInvStack(invSlot);
		return new GenericScreenHandler11x7(syncId, ShulkerBoxSlot::new, player.inventory, new ItemStackInventory(stack, getSlotCount(stack)), name);
	}

	public static GenericScreenHandler9x7 create9x7(int syncId, Identifier identifier, PlayerEntity player, PacketByteBuf buf) {
		int invSlot = buf.readInt();
		Text name = buf.readText();
		ItemStack stack = player.inventory.getInvStack(invSlot);
		return new GenericScreenHandler9x7(syncId, ShulkerBoxSlot::new, player.inventory, new ItemStackInventory(stack, getSlotCount(stack)), name);
	}

	public static ScrollableScreenHandler createScrollable(int syncId, Identifier identifier, PlayerEntity player, PacketByteBuf buf) {
		int invSlot = buf.readInt();
		Text name = buf.readText();
		ItemStack stack = player.inventory.getInvStack(invSlot);
		return new ScrollableScreenHandler(syncId, ShulkerBoxSlot::new, player.inventory, new ItemStackInventory(stack, getSlotCount(stack)), name);
	}

	public static ScrollableScreenHandler createEnderSlab(int syncId, Identifier identifier, PlayerEntity player, PacketByteBuf buf) {
		Text name = buf.readText();
		EnderSlabInventory slab = ((EnderSlabBridge) player).bridge$getEnderSlabInventory();
		return new ScrollableScreenHandler(syncId, Slot::new, player.inventory, slab, name);
	}

	public static int getSlotCount(ItemStack stack) {
		if (stack.getItem() instanceof BlockItem) {
			Block block = ((BlockItem) stack.getItem()).getBlock();

			if (block instanceof AbstractShulkerBoxBlock) {
				return ((AbstractShulkerBoxBlock) block).getSlotCount();
			}
		}

		return 0;
	}

	public static void openCopperBox(PlayerEntity player, ItemStack stack) {
		ContainerProviderRegistry.INSTANCE.openContainer(QS$SHULKER_SCROLLABLE_CONTAINER, player, writer -> {
			writer.writeInt(Util.getSlotWithStack(player.inventory, stack));
			writer.writeText(ShulkerTexts.COPPER_CONTAINER);
		});
	}

	public static void openIronBox(PlayerEntity player, ItemStack stack) {
		ContainerProviderRegistry.INSTANCE.openContainer(QS$SHULKER_SCROLLABLE_CONTAINER, player, writer -> {
			writer.writeInt(Util.getSlotWithStack(player.inventory, stack));
			writer.writeText(ShulkerTexts.IRON_CONTAINER);
		});
	}

	public static void openSilverBox(PlayerEntity player, ItemStack stack) {
		ContainerProviderRegistry.INSTANCE.openContainer(QS$SHULKER_SCROLLABLE_CONTAINER, player, writer -> {
			writer.writeInt(Util.getSlotWithStack(player.inventory, stack));
			writer.writeText(ShulkerTexts.SILVER_CONTAINER);
		});
	}

	public static void openGoldBox(PlayerEntity player, ItemStack stack) {
		ContainerProviderRegistry.INSTANCE.openContainer(QS$SHULKER_9x7_CONTAINER, player, writer -> {
			writer.writeInt(Util.getSlotWithStack(player.inventory, stack));
			writer.writeText(ShulkerTexts.GOLD_CONTAINER);
		});
	}

	public static void openDiamondBox(PlayerEntity player, ItemStack stack) {
		ContainerProviderRegistry.INSTANCE.openContainer(QS$SHULKER_11x7_CONTAINER, player, writer -> {
			writer.writeInt(Util.getSlotWithStack(player.inventory, stack));
			writer.writeText(ShulkerTexts.DIAMOND_CONTAINER);
		});
	}

	public static void openObsidianBox(PlayerEntity player, ItemStack stack) {
		ContainerProviderRegistry.INSTANCE.openContainer(QS$SHULKER_13x7_CONTAINER, player, writer -> {
			writer.writeInt(Util.getSlotWithStack(player.inventory, stack));
			writer.writeText(ShulkerTexts.OBSIDIAN_CONTAINER);
		});
	}

	public static void openPlatinumBox(PlayerEntity player, ItemStack stack) {
		ContainerProviderRegistry.INSTANCE.openContainer(QS$SHULKER_13x7_CONTAINER, player, writer -> {
			writer.writeInt(Util.getSlotWithStack(player.inventory, stack));
			writer.writeText(ShulkerTexts.PLATINUM_CONTAINER);
		});
	}

	public static void openSlabBox(PlayerEntity player, ItemStack stack) {
		ContainerProviderRegistry.INSTANCE.openContainer(QS$SHULKER_SCROLLABLE_CONTAINER, player, writer -> {
			writer.writeInt(Util.getSlotWithStack(player.inventory, stack));
			writer.writeText(ShulkerTexts.CURSED_SLAB_CONTAINER);
		});
	}

	public static void openEnderSlab(PlayerEntity player, ItemStack stack) {
		ContainerProviderRegistry.INSTANCE.openContainer(QS$ENDER_SLAB, player, writer -> writer.writeText(ShulkerTexts.ENDER_SLAB));
	}

	public static void openMissingTex(PlayerEntity player, ItemStack stack) {
		ContainerProviderRegistry.INSTANCE.openContainer(QS$SHULKER_SCROLLABLE_CONTAINER, player, writer -> {
			writer.writeInt(Util.getSlotWithStack(player.inventory, stack));
			writer.writeText(ShulkerTexts.MISSING_CONTAINER);
		});
	}

	private static void openStair(PlayerEntity player, ItemStack stack) {
		ContainerProviderRegistry.INSTANCE.openContainer(QS$SHULKER_SCROLLABLE_CONTAINER, player, writer -> {
			writer.writeInt(Util.getSlotWithStack(player.inventory, stack));
			writer.writeText(ShulkerTexts.CURSED_STAIR_CONTAINER);
		});
	}
}
