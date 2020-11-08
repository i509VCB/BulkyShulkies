package me.i509.fabric.bulkyshulkies.registry;

import dev.onyxstudios.cca.api.v3.component.ComponentKey;
import dev.onyxstudios.cca.api.v3.component.ComponentRegistryV3;
import dev.onyxstudios.cca.api.v3.component.ComponentV3;
import me.i509.fabric.bulkyshulkies.BulkyShulkies;
import me.i509.fabric.bulkyshulkies.api.component.EnderSlabInventory;
import me.i509.fabric.bulkyshulkies.api.component.MagnetismCooldownComponent;
import me.i509.fabric.bulkyshulkies.api.component.ShulkerBoxColorComponent;

public final class ShulkerComponents {
	public static final ComponentKey<EnderSlabInventory> ENDER_SLAB_INVENTORY = register("ender_slab_inventory", EnderSlabInventory.class);
	public static final ComponentKey<ShulkerBoxColorComponent> SHULKER_BOX_COLOR = register("shulker_box_color", ShulkerBoxColorComponent.class);
	public static final ComponentKey<MagnetismCooldownComponent> MAGNETISM_COOLDOWN = register("magnetism_cooldown", MagnetismCooldownComponent.class);

	private static <C extends ComponentV3> ComponentKey<C> register(String name, Class<C> type) {
		return ComponentRegistryV3.INSTANCE.getOrCreate(BulkyShulkies.id(name), type);
	}

	private ShulkerComponents() {
	}
}
