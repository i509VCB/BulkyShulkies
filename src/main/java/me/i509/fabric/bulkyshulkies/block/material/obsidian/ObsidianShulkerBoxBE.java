package me.i509.fabric.bulkyshulkies.block.material.obsidian;

import org.jetbrains.annotations.Nullable;

import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.DyeColor;

import me.i509.fabric.bulkyshulkies.api.block.Abstract1X1ShulkerBoxBE;
import me.i509.fabric.bulkyshulkies.block.ShulkerBoxConstants;
import me.i509.fabric.bulkyshulkies.registry.ShulkerBlockEntities;

public class ObsidianShulkerBoxBE extends Abstract1X1ShulkerBoxBE {
	public ObsidianShulkerBoxBE(@Nullable DyeColor color) {
		super(ShulkerBlockEntities.OBSIDIAN_SHULKER_BOX, ShulkerBoxConstants.OBSIDIAN_SLOT_COUNT, color);
	}

	public ObsidianShulkerBoxBE() {
		this(null);
	}

	@Override
	protected Text getContainerName() {
		return new TranslatableText("container.obsidianShulkerBox");
	}
}
