package me.i509.fabric.bulkyshulkies.client.registry;

import me.i509.fabric.bulkyshulkies.registry.ShulkerRegistries;

public final class ClientShulkerRegistries {
	public static void init() {
	}

	static {
		ShulkerRegistries.init(); // Prevent any weird conditions
		ShulkerScreenFactories.init();
	}

	private ClientShulkerRegistries() {
	}
}
