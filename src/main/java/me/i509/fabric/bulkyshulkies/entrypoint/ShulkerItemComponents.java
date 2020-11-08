package me.i509.fabric.bulkyshulkies.entrypoint;

import java.util.Map;

import dev.onyxstudios.cca.api.v3.component.ComponentKey;
import dev.onyxstudios.cca.api.v3.item.ItemComponentFactory;
import dev.onyxstudios.cca.api.v3.item.ItemComponentFactoryRegistry;
import dev.onyxstudios.cca.api.v3.item.ItemComponentInitializer;
import me.i509.fabric.bulkyshulkies.BulkyShulkies;
import me.i509.fabric.bulkyshulkies.api.ShulkerBoxType;

import net.fabricmc.fabric.api.event.registry.RegistryEntryAddedCallback;

public final class ShulkerItemComponents implements ItemComponentInitializer {
	@Override
	public void registerItemComponentFactories(ItemComponentFactoryRegistry registry) {
		RegistryEntryAddedCallback.event(BulkyShulkies.SHULKER_BOX_TYPE).register((rawId, id, type) -> {
			this.registerItemStackComponents(registry, type);
		});

		for (ShulkerBoxType type : BulkyShulkies.SHULKER_BOX_TYPE) {
			this.registerItemStackComponents(registry, type);
		}
	}

	private void registerItemStackComponents(ItemComponentFactoryRegistry registry, ShulkerBoxType type) {
		for (Map.Entry<ComponentKey<?>, ItemComponentFactory<?>> entry : type.getItemStackComponents().entrySet()) {
			//noinspection unchecked,rawtypes
			registry.registerFor(type.getId(), (ComponentKey) entry.getKey(), entry.getValue());
		}
	}
}
