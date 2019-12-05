package me.i509.fabric.bulkyshulkies.api.player;

import me.i509.fabric.bulkyshulkies.inventory.EnderSlabInventory;
import net.minecraft.entity.player.PlayerEntity;

public interface EnderSlabAccess {
	EnderSlabInventory getEnderSlabInventory();

	static EnderSlabAccess access(PlayerEntity playerEntity) {
		return (EnderSlabAccess) playerEntity;
	}
}
