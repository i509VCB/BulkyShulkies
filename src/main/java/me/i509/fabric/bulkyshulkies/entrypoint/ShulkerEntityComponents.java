package me.i509.fabric.bulkyshulkies.entrypoint;

import dev.onyxstudios.cca.api.v3.entity.EntityComponentFactoryRegistry;
import dev.onyxstudios.cca.api.v3.entity.EntityComponentInitializer;
import me.i509.fabric.bulkyshulkies.inventory.EnderSlabInventoryImpl;
import me.i509.fabric.bulkyshulkies.registry.ShulkerComponents;
import nerdhub.cardinal.components.api.util.RespawnCopyStrategy;

public final class ShulkerEntityComponents implements EntityComponentInitializer {
	@Override
	public void registerEntityComponentFactories(EntityComponentFactoryRegistry registry) {
		registry.registerForPlayers(ShulkerComponents.ENDER_SLAB_INVENTORY, player -> new EnderSlabInventoryImpl(), RespawnCopyStrategy.ALWAYS_COPY);
	}
}
