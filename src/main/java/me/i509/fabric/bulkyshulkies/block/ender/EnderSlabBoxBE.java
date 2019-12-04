package me.i509.fabric.bulkyshulkies.block.ender;

import me.i509.fabric.bulkyshulkies.api.block.slab.AbstractCursedShulkerSlabBlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.DyeColor;
import org.jetbrains.annotations.Nullable;

public class EnderSlabBoxBE extends AbstractCursedShulkerSlabBlockEntity {
	public EnderSlabBoxBE(BlockEntityType<?> blockEntityType, int maxAvailableSlot, @Nullable DyeColor color) {
		super(blockEntityType, maxAvailableSlot, color);
	}
}
