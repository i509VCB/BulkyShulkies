package me.i509.fabric.bulkyshulkies.block.material.netherite;

import me.i509.fabric.bulkyshulkies.api.block.Facing1X1ShulkerBoxBE;
import me.i509.fabric.bulkyshulkies.block.ShulkerBoxConstants;
import me.i509.fabric.bulkyshulkies.registry.ShulkerBlockEntities;
import me.i509.fabric.bulkyshulkies.registry.ShulkerTexts;
import net.minecraft.text.Text;
import net.minecraft.util.DyeColor;
import org.jetbrains.annotations.Nullable;

public class NetheriteShulkerBoxBE extends Facing1X1ShulkerBoxBE {
	public NetheriteShulkerBoxBE(@Nullable DyeColor color) {
		super(ShulkerBlockEntities.NETHERITE_SHULKER_BOX, ShulkerBoxConstants.NETHERITE_SLOT_COUNT, color);
	}

	public NetheriteShulkerBoxBE() {
		this(null);
	}

	@Override
	protected Text getContainerName() {
		return ShulkerTexts.NETHERITE_CONTAINER;
	}
}
