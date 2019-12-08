package me.i509.fabric.bulkyshulkies.block.ender;

import org.jetbrains.annotations.Nullable;

import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.DyeColor;

import me.i509.fabric.bulkyshulkies.api.block.slab.AbstractCursedShulkerSlabBE;

public class EnderSlabBoxBE extends AbstractCursedShulkerSlabBE {
	public EnderSlabBoxBE(BlockEntityType<?> blockEntityType, int maxAvailableSlot, @Nullable DyeColor color) {
		super(blockEntityType, maxAvailableSlot, color);
	}
}
