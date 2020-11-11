package me.i509.fabric.bulkyshulkies.entrypoint;

import java.util.Map;

import dev.onyxstudios.cca.api.v3.block.BlockComponentFactoryRegistry;
import dev.onyxstudios.cca.api.v3.block.BlockComponentInitializer;
import dev.onyxstudios.cca.api.v3.component.ComponentFactory;
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
		for (Map.Entry<ComponentKey<?>, ComponentFactory<? extends ShulkerBoxBlockEntity, ?>> entry : type.getBlockEntityComponents().entrySet()) {
			// TODO: Test me
			//noinspection unchecked,rawtypes
			registry.beginRegistration(BlockEntity.class, entry.getKey())
					.filter(klass -> klass.isAssignableFrom(ShulkerBoxBlockEntity.class))
					.end((ComponentFactory) entry.getValue());
		}
	}
}
