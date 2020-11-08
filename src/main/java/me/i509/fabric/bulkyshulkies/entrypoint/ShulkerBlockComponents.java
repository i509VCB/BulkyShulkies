package me.i509.fabric.bulkyshulkies.entrypoint;

import java.util.Map;

import dev.onyxstudios.cca.api.v3.block.BlockComponentFactoryRegistry;
import dev.onyxstudios.cca.api.v3.block.BlockComponentInitializer;
import dev.onyxstudios.cca.api.v3.block.BlockComponentProvider;
import dev.onyxstudios.cca.api.v3.component.ComponentKey;
import me.i509.fabric.bulkyshulkies.BulkyShulkies;
import me.i509.fabric.bulkyshulkies.api.ShulkerBoxType;
import me.i509.fabric.bulkyshulkies.api.block.entity.ShulkerBoxBlockEntity;

import net.minecraft.block.entity.BlockEntity;

import net.fabricmc.fabric.api.event.registry.RegistryEntryAddedCallback;

public final class ShulkerBlockComponents implements BlockComponentInitializer {
	@Override
	public void registerBlockComponentFactories(BlockComponentFactoryRegistry registry) {
		RegistryEntryAddedCallback.event(BulkyShulkies.SHULKER_BOX_TYPE).register((rawId, id, type) -> {
			this.registerBlockEntityComponents(registry, type);
		});

		for (ShulkerBoxType type : BulkyShulkies.SHULKER_BOX_TYPE) {
			this.registerBlockEntityComponents(registry, type);
		}
	}

	private void registerBlockEntityComponents(BlockComponentFactoryRegistry registry, ShulkerBoxType type) {
		for (Map.Entry<ComponentKey<?>, BlockComponentProvider<?>> entry : type.getBlockEntityComponents().entrySet()) {
			registry.beginRegistration(BlockEntity.class, entry.getKey())
					.filter(ShulkerBoxBlockEntity.class::isAssignableFrom)
					.end(blockEntity -> {
						return null; // FIXME: Should not return null at all
					});
		}
	}
}
