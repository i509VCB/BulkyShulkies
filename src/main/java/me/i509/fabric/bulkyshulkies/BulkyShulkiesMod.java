/*
 * MIT License
 *
 * Copyright (c) 2019 i509VCB
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

package me.i509.fabric.bulkyshulkies;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.container.ContainerProviderRegistry;

import me.i509.fabric.bulkyshulkies.container.ContainerKeys;
import me.i509.fabric.bulkyshulkies.extension.ShulkerHooks;
import me.i509.fabric.bulkyshulkies.recipe.BulkyRecipeSerializers;
import me.i509.fabric.bulkyshulkies.registry.ShulkerBlockEntities;
import me.i509.fabric.bulkyshulkies.registry.ShulkerBlocks;
import me.i509.fabric.bulkyshulkies.registry.ShulkerContainers;
import me.i509.fabric.bulkyshulkies.registry.ShulkerDispenserBehaviors;
import me.i509.fabric.bulkyshulkies.registry.ShulkerItemGroups;
import me.i509.fabric.bulkyshulkies.registry.ShulkerItems;

public class BulkyShulkiesMod implements ModInitializer {
	public static final String MODID = "bulkyshulkies";

	@Override
	public void onInitialize() {
		BulkyShulkies.getInstance();
		BulkyRecipeSerializers.ABSTRACT_SHULKER_COLORING.getClass(); // Register the colorizer recipe type
		BulkyDataTrackers.SHULKER_ANIMATION_STAGE.getClass(); // Load the DataTrackers
		ShulkerBlocks.init();
		ShulkerDispenserBehaviors.init();
		ShulkerBlockEntities.init();
		ShulkerItems.init();
		ShulkerItemGroups.init();
		ShulkerHooks.init();

		ContainerProviderRegistry.INSTANCE.registerFactory(ContainerKeys.ENDER_SLAB, ShulkerContainers::createEnderSlab);
		ContainerProviderRegistry.INSTANCE.registerFactory(ContainerKeys.SHULKER_SCROLLABLE_CONTAINER, (ShulkerContainers::createScrollable));
		ContainerProviderRegistry.INSTANCE.registerFactory(ContainerKeys.SHULKER_9x7_CONTAINER, ShulkerContainers::create9x7);
		ContainerProviderRegistry.INSTANCE.registerFactory(ContainerKeys.SHULKER_11x7_CONTAINER, ShulkerContainers::create11x7);
		ContainerProviderRegistry.INSTANCE.registerFactory(ContainerKeys.SHULKER_13x7_CONTAINER, ShulkerContainers::create13x7);
	}
}
