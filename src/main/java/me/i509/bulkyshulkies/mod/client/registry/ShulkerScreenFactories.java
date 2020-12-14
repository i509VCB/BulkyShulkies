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

package me.i509.bulkyshulkies.mod.client.registry;

import net.fabricmc.fabric.api.client.screenhandler.v1.ScreenRegistry;

import me.i509.bulkyshulkies.api.registry.ShulkerScreenHandlers;
import me.i509.bulkyshulkies.mod.Uninstantiable;
import me.i509.bulkyshulkies.mod.client.screen.Generic11x7Screen;
import me.i509.bulkyshulkies.mod.client.screen.Generic13x7Screen;
import me.i509.bulkyshulkies.mod.client.screen.Generic9x7Screen;
import me.i509.bulkyshulkies.mod.client.screen.GenericCustomSlotContainerScreen;

// Intentionally package-private
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
		Uninstantiable.whyDoIHearBossMusic(ShulkerScreenFactories.class);
	}
}
