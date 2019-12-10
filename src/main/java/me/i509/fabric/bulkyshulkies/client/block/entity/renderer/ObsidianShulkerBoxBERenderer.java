package me.i509.fabric.bulkyshulkies.client.block.entity.renderer;

import net.minecraft.client.render.block.entity.BlockEntityRenderDispatcher;

import me.i509.fabric.bulkyshulkies.block.material.obsidian.ObsidianShulkerBoxBE;
import me.i509.fabric.bulkyshulkies.client.TextureKeys;

public class ObsidianShulkerBoxBERenderer extends Abstract1x1ShulkerBERenderer<ObsidianShulkerBoxBE> {
	public ObsidianShulkerBoxBERenderer(BlockEntityRenderDispatcher ber) {
		super(ber, TextureKeys.OBSIDIAN);
	}
}
