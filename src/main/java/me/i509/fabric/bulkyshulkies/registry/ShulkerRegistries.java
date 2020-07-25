package me.i509.fabric.bulkyshulkies.registry;

import me.i509.fabric.bulkyshulkies.BulkyDataTrackerHandlers;
import me.i509.fabric.bulkyshulkies.event.ShulkerEvents;
import me.i509.fabric.bulkyshulkies.extension.ShulkerHooks;
import me.i509.fabric.bulkyshulkies.recipe.BulkyRecipeSerializers;

public final class ShulkerRegistries {
	public static void init() {
		BulkyRecipeSerializers.ABSTRACT_SHULKER_COLORING.getClass(); // Register the colorizer recipe type
		BulkyDataTrackerHandlers.SHULKER_ANIMATION_STAGE.getClass(); // Load the DataTrackers
		ShulkerBlocks.init();
		ShulkerDispenserBehaviors.init();
		ShulkerBlockEntities.init();
		ShulkerEvents.init();
		ShulkerItems.init();
		ShulkerItemGroups.init();
		ShulkerHooks.init();
	}

	private ShulkerRegistries() {
		throw new AssertionError("You should not be instantiating this");
	}
}
