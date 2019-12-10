package me.i509.fabric.bulkyshulkies.client.block.entity.renderer;

import net.minecraft.client.render.block.entity.BlockEntityRenderDispatcher;

import me.i509.fabric.bulkyshulkies.block.material.platinum.PlatinumShulkerBoxBE;
import me.i509.fabric.bulkyshulkies.client.TextureKeys;

public class PlatinumShulkerBoxBERenderer extends Abstract1x1ShulkerBERenderer<PlatinumShulkerBoxBE> {
	public PlatinumShulkerBoxBERenderer(BlockEntityRenderDispatcher ber) {
		super(ber, TextureKeys.PLATINUM);
	}
}
