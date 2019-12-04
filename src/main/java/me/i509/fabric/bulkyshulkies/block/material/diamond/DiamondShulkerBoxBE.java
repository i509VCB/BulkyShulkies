package me.i509.fabric.bulkyshulkies.block.material.diamond;

import me.i509.fabric.bulkyshulkies.api.block.material.AbstractMaterialBasedShulkerBoxBlockEntity;
import me.i509.fabric.bulkyshulkies.registry.ShulkerBlockEntities;
import net.minecraft.util.DyeColor;
import org.jetbrains.annotations.Nullable;

public class DiamondShulkerBoxBE extends AbstractMaterialBasedShulkerBoxBlockEntity {
	public DiamondShulkerBoxBE(@Nullable DyeColor color) {
		super(ShulkerBlockEntities.DIAMOND_SHULKER_BOX, 72, color);
	}

	public DiamondShulkerBoxBE() {
		this(null);
	}
}
