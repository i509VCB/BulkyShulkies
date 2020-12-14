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

import me.i509.bulkyshulkies.api.registry.ShulkerBoxTypes;
import me.i509.bulkyshulkies.api.registry.ShulkerItems;
import me.i509.bulkyshulkies.api.registry.ShulkerScreenHandlers;
import me.i509.bulkyshulkies.mod.BulkyDataTrackerHandlers;
import me.i509.bulkyshulkies.mod.Uninstantiable;
import me.i509.bulkyshulkies.mod.event.ShulkerEvents;
import me.i509.bulkyshulkies.mod.recipe.BulkyRecipeSerializers;

public final class ShulkerRegistries {
	public static void init() {
		BulkyRecipeSerializers.ABSTRACT_SHULKER_COLORING.getClass(); // Register the colorizer recipe type
		BulkyDataTrackerHandlers.SHULKER_ANIMATION_STAGE.getClass(); // Load the DataTrackers
		ShulkerBlockSettings.init();
		ShulkerBoxTypes.init();
		ShulkerDispenserBehaviors.init();
		ShulkerEvents.init();
		ShulkerItems.init();
		ShulkerItemGroups.init();
		ShulkerScreenHandlers.init();
	}

	private ShulkerRegistries() {
		Uninstantiable.whyDoIHearBossMusic(ShulkerRegistries.class);
	}
}
