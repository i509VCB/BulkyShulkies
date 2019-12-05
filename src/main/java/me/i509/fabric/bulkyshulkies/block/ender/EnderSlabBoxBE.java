package me.i509.fabric.bulkyshulkies.block.ender;

import org.jetbrains.annotations.Nullable;

import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.DyeColor;

import me.i509.fabric.bulkyshulkies.api.block.slab.AbstractCursedShulkerSlabBlockEntity;

public class EnderSlabBoxBE extends AbstractCursedShulkerSlabBlockEntity {
	public EnderSlabBoxBE(BlockEntityType<?> blockEntityType, int maxAvailableSlot, @Nullable DyeColor color) {
		super(blockEntityType, maxAvailableSlot, color);
	}
}
