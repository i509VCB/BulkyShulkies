package me.i509.fabric.bulkyshulkies.block.material.diamond;

import org.jetbrains.annotations.Nullable;

import net.minecraft.util.DyeColor;

import me.i509.fabric.bulkyshulkies.api.block.Abstract1X1ShulkerBoxBE;
import me.i509.fabric.bulkyshulkies.block.ShulkerBoxConstants;
import me.i509.fabric.bulkyshulkies.registry.ShulkerBlockEntities;

public class DiamondShulkerBoxBE extends Abstract1X1ShulkerBoxBE {
	public DiamondShulkerBoxBE(@Nullable DyeColor color) {
		super(ShulkerBlockEntities.DIAMOND_SHULKER_BOX, ShulkerBoxConstants.DIAMOND_SLOT_COUNT, color);
	}

	public DiamondShulkerBoxBE() {
		this(null);
	}
}
