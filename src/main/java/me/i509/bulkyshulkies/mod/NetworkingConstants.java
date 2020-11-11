package me.i509.bulkyshulkies.mod;

import net.minecraft.util.Identifier;

public final class NetworkingConstants {
	/*
	 * Versioning constants
	 */
	public static final int NETWORKING_VERSION = 1;

	/*
	 * Channels
	 */
	public static final Identifier DECLARE_VERSION = BulkyShulkies.id("packet/declare_version");
	public static final Identifier OPEN_HELMET = BulkyShulkies.id("packet/action/open_helmet_inv");
	public static final Identifier SYNC_CONFIG = BulkyShulkies.id("packet/config/sync");

	private NetworkingConstants() {
	}
}
