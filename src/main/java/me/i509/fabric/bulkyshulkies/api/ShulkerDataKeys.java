package me.i509.fabric.bulkyshulkies.api;

import me.i509.fabric.bulkyshulkies.api.block.ShulkerBoxColor;

/**
 * A catalog of all builtin data keys.
 */
public final class ShulkerDataKeys {
	public static final DataKey<ShulkerBoxColor> COLOR = DataKey.create("shulker_box_color", ShulkerBoxColor.class, ShulkerBoxColor.values(), ShulkerBoxColor::parse);

	private ShulkerDataKeys() {
	}
}
