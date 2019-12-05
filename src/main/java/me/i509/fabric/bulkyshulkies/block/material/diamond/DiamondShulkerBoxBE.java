package me.i509.fabric.bulkyshulkies.block.material.diamond;

import org.jetbrains.annotations.Nullable;

import net.minecraft.util.DyeColor;

import me.i509.fabric.bulkyshulkies.api.block.material.AbstractMaterialBasedShulkerBoxBlockEntity;
import me.i509.fabric.bulkyshulkies.registry.ShulkerBlockEntities;

public class DiamondShulkerBoxBE extends AbstractMaterialBasedShulkerBoxBlockEntity {
	public DiamondShulkerBoxBE(@Nullable DyeColor color) {
		super(ShulkerBlockEntities.DIAMOND_SHULKER_BOX, 72, color);
	}

	public DiamondShulkerBoxBE() {
		this(null);
	}
}
