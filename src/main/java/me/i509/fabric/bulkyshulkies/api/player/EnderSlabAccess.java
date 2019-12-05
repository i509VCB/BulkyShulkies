package me.i509.fabric.bulkyshulkies.api.player;

import net.minecraft.entity.player.PlayerEntity;

import me.i509.fabric.bulkyshulkies.inventory.EnderSlabInventory;

public interface EnderSlabAccess {
	EnderSlabInventory getEnderSlabInventory();

	static EnderSlabAccess access(PlayerEntity playerEntity) {
		return (EnderSlabAccess) playerEntity;
	}
}
