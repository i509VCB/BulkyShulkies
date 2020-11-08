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

import net.kyrptonaught.quickshulker.api.ItemStackInventory;
import net.kyrptonaught.quickshulker.api.RegisterQuickShulker;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.SimpleNamedScreenHandlerFactory;
import net.minecraft.screen.slot.ShulkerBoxSlot;
import net.minecraft.screen.slot.Slot;

import me.i509.fabric.bulkyshulkies.BulkyShulkies;
import me.i509.fabric.bulkyshulkies.inventory.PlayerEntityExtensions;
import me.i509.fabric.bulkyshulkies.block.old.ShulkerBoxConstants;
import me.i509.fabric.bulkyshulkies.block.old.cursed.slab.ColoredSlabShulkerBoxBlock;
import me.i509.fabric.bulkyshulkies.block.old.cursed.stair.StairShulkerBoxBlock;
import me.i509.fabric.bulkyshulkies.block.old.ender.EnderSlabBlock;
import me.i509.fabric.bulkyshulkies.block.old.CopperShulkerBoxBlock;
import me.i509.fabric.bulkyshulkies.block.old.DiamondShulkerBoxBlock;
import me.i509.fabric.bulkyshulkies.block.old.GoldShulkerBoxBlock;
import me.i509.fabric.bulkyshulkies.block.old.IronShulkerBoxBlock;
import me.i509.fabric.bulkyshulkies.block.old.ObsidianShulkerBoxBlock;
import me.i509.fabric.bulkyshulkies.block.old.PlatinumShulkerBoxBlock;
import me.i509.fabric.bulkyshulkies.block.old.SilverShulkerBoxBlock;
import me.i509.fabric.bulkyshulkies.block.old.MissingTexBoxBlock;
import me.i509.fabric.bulkyshulkies.registry.ShulkerScreenHandlers;
import me.i509.fabric.bulkyshulkies.config.section.quickshulker.QuickShulkerSection;
import me.i509.fabric.bulkyshulkies.screen.GenericCustomSlotContainerScreenHandler;
import me.i509.fabric.bulkyshulkies.screen.GenericScreenHandler11x7;
import me.i509.fabric.bulkyshulkies.screen.GenericScreenHandler13x7;
import me.i509.fabric.bulkyshulkies.screen.GenericScreenHandler9x7;

public class QuickShulkerHook implements RegisterQuickShulker {
	@Override
	public void registerProviders() {
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
			register(QuickShulkerHook::openSlabBox, ColoredSlabShulkerBoxBlock.class);
		}

		if (config.canOpenEnderSlab()) {
			register(QuickShulkerHook::openEnderSlab, EnderSlabBlock.class);
		}

		if (config.canOpenMissingTex()) {
			register(QuickShulkerHook::openMissingTex, MissingTexBoxBlock.class);
		}

		if (config.canOpenStair()) {
			register(QuickShulkerHook::openStair, StairShulkerBoxBlock.class);
		}
	}

	public static void openCopperBox(PlayerEntity user, ItemStack stack) {
		user.openHandledScreen(new SimpleNamedScreenHandlerFactory((syncId, inventory, player) -> {
			return GenericCustomSlotContainerScreenHandler.createGeneric9x4(ShulkerScreenHandlers.SHULKER_9x4_SCREEN_HANDLER, syncId, inventory, new ItemStackInventory(stack, ShulkerBoxConstants.COPPER_SLOT_COUNT), ShulkerBoxSlot::new);
		}, stack.getName()));
	}

	public static void openIronBox(PlayerEntity user, ItemStack stack) {
		user.openHandledScreen(new SimpleNamedScreenHandlerFactory((syncId, inventory, player) -> {
			return GenericCustomSlotContainerScreenHandler.createGeneric9x5(ShulkerScreenHandlers.SHULKER_9x5_SCREEN_HANDLER, syncId, inventory, new ItemStackInventory(stack, ShulkerBoxConstants.IRON_SLOT_COUNT), ShulkerBoxSlot::new);
		}, stack.getName()));
	}

	public static void openSilverBox(PlayerEntity user, ItemStack stack) {
		user.openHandledScreen(new SimpleNamedScreenHandlerFactory((syncId, inventory, player) -> {
			return GenericCustomSlotContainerScreenHandler.createGeneric9x6(ShulkerScreenHandlers.SHULKER_9x6_SCREEN_HANDLER, syncId, inventory, new ItemStackInventory(stack, ShulkerBoxConstants.SILVER_SLOT_COUNT), ShulkerBoxSlot::new);
		}, stack.getName()));
	}

	public static void openGoldBox(PlayerEntity user, ItemStack stack) {
		user.openHandledScreen(new SimpleNamedScreenHandlerFactory((syncId, inventory, player) -> {
			return new GenericScreenHandler9x7(ShulkerScreenHandlers.SHULKER_9x7_SCREEN_HANDLER, syncId, inventory, new ItemStackInventory(stack, ShulkerBoxConstants.GOLD_SLOT_COUNT), ShulkerBoxSlot::new);
		}, stack.getName()));
	}

	public static void openDiamondBox(PlayerEntity user, ItemStack stack) {
		user.openHandledScreen(new SimpleNamedScreenHandlerFactory((syncId, inventory, player) -> {
			return new GenericScreenHandler11x7(ShulkerScreenHandlers.SHULKER_11x7_SCREEN_HANDLER, syncId, inventory, new ItemStackInventory(stack, ShulkerBoxConstants.DIAMOND_SLOT_COUNT), ShulkerBoxSlot::new);
		}, stack.getName()));
	}

	public static void openObsidianBox(PlayerEntity user, ItemStack stack) {
		user.openHandledScreen(new SimpleNamedScreenHandlerFactory((syncId, inventory, player) -> {
			return new GenericScreenHandler13x7(ShulkerScreenHandlers.SHULKER_13x7_SCREEN_HANDLER, syncId, inventory, new ItemStackInventory(stack, ShulkerBoxConstants.OBSIDIAN_SLOT_COUNT), ShulkerBoxSlot::new);
		}, stack.getName()));
	}

	public static void openPlatinumBox(PlayerEntity user, ItemStack stack) {
		user.openHandledScreen(new SimpleNamedScreenHandlerFactory((syncId, inventory, player) -> {
			return new GenericScreenHandler13x7(ShulkerScreenHandlers.SHULKER_13x7_SCREEN_HANDLER, syncId, inventory, new ItemStackInventory(stack, ShulkerBoxConstants.PLATINUM_SLOT_COUNT), ShulkerBoxSlot::new);
		}, stack.getName()));
	}

	public static void openSlabBox(PlayerEntity user, ItemStack stack) {
		user.openHandledScreen(new SimpleNamedScreenHandlerFactory((syncId, inventory, player) -> {
			return GenericCustomSlotContainerScreenHandler.createGeneric9x2(ShulkerScreenHandlers.SHULKER_9x2_SCREEN_HANDLER, syncId, inventory, new ItemStackInventory(stack, ShulkerBoxConstants.SLAB_SLOT_COUNT), ShulkerBoxSlot::new);
		}, stack.getName()));
	}

	public static void openEnderSlab(PlayerEntity user, ItemStack stack) {
		user.openHandledScreen(new SimpleNamedScreenHandlerFactory((syncId, inventory, player) -> {
			return GenericCustomSlotContainerScreenHandler.createGeneric9x2(ShulkerScreenHandlers.ENDER_SLAB, syncId, inventory, ((PlayerEntityExtensions) player).getEnderSlabInventory(), Slot::new);
		}, stack.getName()));
	}

	public static void openMissingTex(PlayerEntity user, ItemStack stack) {
		user.openHandledScreen(new SimpleNamedScreenHandlerFactory((syncId, inventory, player) -> {
			return GenericCustomSlotContainerScreenHandler.createGeneric9x3(ShulkerScreenHandlers.SHULKER_9x3_SCREEN_HANDLER, syncId, inventory, new ItemStackInventory(stack, ShulkerBoxConstants.MISSING_TEX), ShulkerBoxSlot::new);
		}, stack.getName()));
	}

	private static void openStair(PlayerEntity user, ItemStack stack) {
		user.openHandledScreen(new SimpleNamedScreenHandlerFactory((syncId, inventory, player) -> {
			return GenericCustomSlotContainerScreenHandler.createGeneric9x3(ShulkerScreenHandlers.SHULKER_9x3_SCREEN_HANDLER, syncId, inventory, new ItemStackInventory(stack, ShulkerBoxConstants.STAIR_SLOT_COUNT), ShulkerBoxSlot::new);
		}, stack.getName()));
	}
}
