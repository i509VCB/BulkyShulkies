package me.i509.fabric.bulkyshulkies.client.registry;

import me.i509.fabric.bulkyshulkies.client.screen.Generic11x7Screen;
import me.i509.fabric.bulkyshulkies.client.screen.Generic13x7Screen;
import me.i509.fabric.bulkyshulkies.client.screen.Generic9x7Screen;
import me.i509.fabric.bulkyshulkies.client.screen.GenericCustomSlotContainerScreen;
import me.i509.fabric.bulkyshulkies.registry.ShulkerScreenHandlers;

import net.fabricmc.fabric.api.client.screenhandler.v1.ScreenRegistry;

final class ShulkerScreenFactories {
	static void init() {
		ScreenRegistry.register(ShulkerScreenHandlers.SHULKER_9x1_SCREEN_HANDLER, GenericCustomSlotContainerScreen::new);
		ScreenRegistry.register(ShulkerScreenHandlers.SHULKER_9x2_SCREEN_HANDLER, GenericCustomSlotContainerScreen::new);
		ScreenRegistry.register(ShulkerScreenHandlers.SHULKER_9x3_SCREEN_HANDLER, GenericCustomSlotContainerScreen::new);
		ScreenRegistry.register(ShulkerScreenHandlers.SHULKER_9x4_SCREEN_HANDLER, GenericCustomSlotContainerScreen::new);
		ScreenRegistry.register(ShulkerScreenHandlers.SHULKER_9x5_SCREEN_HANDLER, GenericCustomSlotContainerScreen::new);
		ScreenRegistry.register(ShulkerScreenHandlers.SHULKER_9x6_SCREEN_HANDLER, GenericCustomSlotContainerScreen::new);
		ScreenRegistry.register(ShulkerScreenHandlers.SHULKER_9x7_SCREEN_HANDLER, Generic9x7Screen::new);
		ScreenRegistry.register(ShulkerScreenHandlers.SHULKER_11x7_SCREEN_HANDLER, Generic11x7Screen::new);
		ScreenRegistry.register(ShulkerScreenHandlers.SHULKER_13x7_SCREEN_HANDLER, Generic13x7Screen::new);
		ScreenRegistry.register(ShulkerScreenHandlers.ENDER_SLAB, GenericCustomSlotContainerScreen::new);
	}

	private ShulkerScreenFactories() {
	}
}
