package me.i509.fabric.bulkyshulkies.client.block.entity.renderer;

import net.minecraft.client.render.block.entity.BlockEntityRenderDispatcher;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

import me.i509.fabric.bulkyshulkies.ShulkerBoxKeys;
import me.i509.fabric.bulkyshulkies.block.material.obsidian.ObsidianShulkerBoxBE;

@Environment(EnvType.CLIENT)
public class ObsidianShulkerBoxBERenderer extends Abstract1x1ShulkerBERenderer<ObsidianShulkerBoxBE> {
	public ObsidianShulkerBoxBERenderer(BlockEntityRenderDispatcher ber) {
		super(ber, ShulkerBoxKeys.OBSIDIAN);
	}
}
