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

package me.i509.bulkyshulkies.mod.registry;

import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.screen.slot.ShulkerBoxSlot;
import net.minecraft.screen.slot.Slot;

import net.fabricmc.fabric.api.screenhandler.v1.ScreenHandlerRegistry;

import me.i509.bulkyshulkies.mod.Uninstantiable;
import me.i509.bulkyshulkies.mod.screen.GenericScreenHandler13x7;
import me.i509.bulkyshulkies.mod.screen.GenericScreenHandler9x7;
import me.i509.bulkyshulkies.mod.screen.GenericCustomSlotContainerScreenHandler;
import me.i509.bulkyshulkies.mod.screen.GenericScreenHandler11x7;
import me.i509.bulkyshulkies.mod.screen.ScreenHandlerKeys;

public final class ShulkerScreenHandlers {
	public static final ScreenHandlerType<GenericCustomSlotContainerScreenHandler> SHULKER_9x1_SCREEN_HANDLER = ScreenHandlerRegistry.registerSimple(ScreenHandlerKeys.SHULKER_9x1_CONTAINER, (syncId, playerInventory) -> {
		return GenericCustomSlotContainerScreenHandler.createGeneric9x1(ShulkerScreenHandlers.SHULKER_9x1_SCREEN_HANDLER, syncId, playerInventory, ShulkerBoxSlot::new);
	});
	public static final ScreenHandlerType<GenericCustomSlotContainerScreenHandler> SHULKER_9x2_SCREEN_HANDLER = ScreenHandlerRegistry.registerSimple(ScreenHandlerKeys.SHULKER_9x2_CONTAINER, (syncId, playerInventory) -> {
		return GenericCustomSlotContainerScreenHandler.createGeneric9x2(ShulkerScreenHandlers.SHULKER_9x2_SCREEN_HANDLER, syncId, playerInventory, ShulkerBoxSlot::new);
	});
	public static final ScreenHandlerType<GenericCustomSlotContainerScreenHandler> SHULKER_9x3_SCREEN_HANDLER = ScreenHandlerRegistry.registerSimple(ScreenHandlerKeys.SHULKER_9x3_CONTAINER, (syncId, playerInventory) -> {
		return GenericCustomSlotContainerScreenHandler.createGeneric9x3(ShulkerScreenHandlers.SHULKER_9x3_SCREEN_HANDLER, syncId, playerInventory, ShulkerBoxSlot::new);
	});
	public static final ScreenHandlerType<GenericCustomSlotContainerScreenHandler> SHULKER_9x4_SCREEN_HANDLER = ScreenHandlerRegistry.registerSimple(ScreenHandlerKeys.SHULKER_9x4_CONTAINER, (syncId, playerInventory) -> {
		return GenericCustomSlotContainerScreenHandler.createGeneric9x4(ShulkerScreenHandlers.SHULKER_9x4_SCREEN_HANDLER, syncId, playerInventory, ShulkerBoxSlot::new);
	});
	public static final ScreenHandlerType<GenericCustomSlotContainerScreenHandler> SHULKER_9x5_SCREEN_HANDLER = ScreenHandlerRegistry.registerSimple(ScreenHandlerKeys.SHULKER_9x5_CONTAINER, (syncId, playerInventory) -> {
		return GenericCustomSlotContainerScreenHandler.createGeneric9x5(ShulkerScreenHandlers.SHULKER_9x5_SCREEN_HANDLER, syncId, playerInventory, ShulkerBoxSlot::new);
	});
	public static final ScreenHandlerType<GenericCustomSlotContainerScreenHandler> SHULKER_9x6_SCREEN_HANDLER = ScreenHandlerRegistry.registerSimple(ScreenHandlerKeys.SHULKER_9x6_CONTAINER, (syncId, playerInventory) -> {
		return GenericCustomSlotContainerScreenHandler.createGeneric9x6(ShulkerScreenHandlers.SHULKER_9x6_SCREEN_HANDLER, syncId, playerInventory, ShulkerBoxSlot::new);
	});
	public static final ScreenHandlerType<GenericScreenHandler9x7> SHULKER_9x7_SCREEN_HANDLER = ScreenHandlerRegistry.registerSimple(ScreenHandlerKeys.SHULKER_9x7_CONTAINER, (syncId, playerInventory) -> {
		return new GenericScreenHandler9x7(ShulkerScreenHandlers.SHULKER_9x7_SCREEN_HANDLER, syncId, playerInventory, ShulkerBoxSlot::new);
	});
	public static final ScreenHandlerType<GenericScreenHandler11x7> SHULKER_11x7_SCREEN_HANDLER = ScreenHandlerRegistry.registerSimple(ScreenHandlerKeys.SHULKER_11x7_CONTAINER, (syncId, playerInventory) -> {
		return new GenericScreenHandler11x7(ShulkerScreenHandlers.SHULKER_11x7_SCREEN_HANDLER, syncId, playerInventory, ShulkerBoxSlot::new);
	});
	public static final ScreenHandlerType<GenericScreenHandler13x7> SHULKER_13x7_SCREEN_HANDLER = ScreenHandlerRegistry.registerSimple(ScreenHandlerKeys.SHULKER_13x7_CONTAINER, (syncId, playerInventory) -> {
		return new GenericScreenHandler13x7(ShulkerScreenHandlers.SHULKER_13x7_SCREEN_HANDLER, syncId, playerInventory, ShulkerBoxSlot::new);
	});
	public static final ScreenHandlerType<GenericCustomSlotContainerScreenHandler> ENDER_SLAB = ScreenHandlerRegistry.registerSimple(ScreenHandlerKeys.ENDER_SLAB, (syncId, playerInventory) -> {
		return GenericCustomSlotContainerScreenHandler.createGeneric9x2(ShulkerScreenHandlers.ENDER_SLAB, syncId, playerInventory, Slot::new);
	});

	private ShulkerScreenHandlers() {
		Uninstantiable.whyDoIHearBossMusic(ShulkerScreenHandlers.class);
	}

	static void init() {
	}
}
