package me.i509.fabric.bulkyshulkies.api.block.entity;

import me.i509.fabric.bulkyshulkies.api.ShulkerBoxType;

import net.minecraft.block.entity.BlockEntity;
import net.minecraft.nbt.CompoundTag;

public interface ShulkerBoxBlockEntity {
	ShulkerBoxType getShulkerBoxType();

	BlockEntity toBlockEntity();
}
