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

package me.i509.fabric.bulkyshulkies.client.extension;

import net.kyrptonaught.quickshulker.api.RegisterQuickShulkerClient;

import net.fabricmc.fabric.api.client.screen.ScreenProviderRegistry;

import me.i509.fabric.bulkyshulkies.client.screen.Generic11x7Screen;
import me.i509.fabric.bulkyshulkies.client.screen.Generic13x7Screen;
import me.i509.fabric.bulkyshulkies.client.screen.Generic9x7Screen;
import me.i509.fabric.bulkyshulkies.client.screen.ScrollableScreen;
import me.i509.fabric.bulkyshulkies.extension.QuickShulkerHook;

public class QuickShulkerHookClient implements RegisterQuickShulkerClient {
	@Override
	public void registerClient() {
		ScreenProviderRegistry.INSTANCE.registerFactory(QuickShulkerHook.QS$SHULKER_9x7_CONTAINER, Generic9x7Screen::createScreen);
		ScreenProviderRegistry.INSTANCE.registerFactory(QuickShulkerHook.QS$SHULKER_11x7_CONTAINER, Generic11x7Screen::createScreen);
		ScreenProviderRegistry.INSTANCE.registerFactory(QuickShulkerHook.QS$SHULKER_13x7_CONTAINER, Generic13x7Screen::createScreen);
		ScreenProviderRegistry.INSTANCE.registerFactory(QuickShulkerHook.QS$SHULKER_SCROLLABLE_CONTAINER, ScrollableScreen::createScreen);
		ScreenProviderRegistry.INSTANCE.registerFactory(QuickShulkerHook.QS$ENDER_SLAB, ScrollableScreen::createScreen);
	}
}
