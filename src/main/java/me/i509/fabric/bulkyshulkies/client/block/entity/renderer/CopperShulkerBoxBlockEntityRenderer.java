/*
 * MIT License
 *
 * Copyright (c) 2019 i509VCB
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package me.i509.fabric.bulkyshulkies.client.block.entity.renderer;

import java.util.Arrays;

import me.i509.fabric.bulkyshulkies.client.ShulkerRenderLayers;
import net.minecraft.client.render.block.entity.BlockEntityRenderDispatcher;
import net.minecraft.client.render.entity.model.ShulkerEntityModel;
import net.minecraft.client.util.SpriteIdentifier;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

import me.i509.fabric.bulkyshulkies.BulkyShulkiesMod;
import me.i509.fabric.bulkyshulkies.abstraction.DefaultReturnHashMap;
import me.i509.fabric.bulkyshulkies.block.material.copper.CopperShulkerBoxBE;

@Environment(EnvType.CLIENT)
public class CopperShulkerBoxBlockEntityRenderer extends AbstractMaterialBasedShulkerBlockEntityRenderer<CopperShulkerBoxBE> {
	public CopperShulkerBoxBlockEntityRenderer(BlockEntityRenderDispatcher blockEntityRenderDispatcher) {
		super(new ShulkerEntityModel<>(), blockEntityRenderDispatcher);
	}

	public static final Identifier SHULKER_TEXTURE_ATLAS = BulkyShulkiesMod.id("textures/atlas/copper_shulkerboxes.png");
	public static final SpriteIdentifier UNCOLORED_IDENTIFIER = new SpriteIdentifier(SHULKER_TEXTURE_ATLAS, BulkyShulkiesMod.id("textures/be/shulker/copper/shulker"));
	public static final DefaultReturnHashMap<DyeColor, SpriteIdentifier> COLOR_TO_SPRITE_IDENTIFIER = Util.create(new DefaultReturnHashMap<>(UNCOLORED_IDENTIFIER), map -> {
		Arrays.stream(DyeColor.values()).forEach(color -> {
			map.put(color, new SpriteIdentifier(SHULKER_TEXTURE_ATLAS, BulkyShulkiesMod.id("textures/be/shulker/copper/shulker_" + color.getName())));
		});
	});

	@Override
	public SpriteIdentifier getUncoloredSprite() {
		return ShulkerRenderLayers.Copper.UNCOLORED;
	}

	@Override
	public SpriteIdentifier getColoredSprite(DyeColor color) {
		switch (color) {
		case WHITE:
			return ShulkerRenderLayers.Copper.WHITE;
		case ORANGE:
			return ShulkerRenderLayers.Copper.ORANGE;
		case MAGENTA:
			return ShulkerRenderLayers.Copper.MAGENTA;
		case LIGHT_BLUE:
			return ShulkerRenderLayers.Copper.LIGHT_BLUE;
		case YELLOW:
			return ShulkerRenderLayers.Copper.YELLOW;
		case LIME:
			return ShulkerRenderLayers.Copper.LIME;
		case PINK:
			return ShulkerRenderLayers.Copper.PINK;
		case GRAY:
			return ShulkerRenderLayers.Copper.GRAY;
		case LIGHT_GRAY:
			return ShulkerRenderLayers.Copper.LIGHT_GRAY;
		case CYAN:
			return ShulkerRenderLayers.Copper.CYAN;
		case PURPLE:
			return ShulkerRenderLayers.Copper.PURPLE;
		case BLUE:
			return ShulkerRenderLayers.Copper.BLUE;
		case BROWN:
			return ShulkerRenderLayers.Copper.BROWN;
		case GREEN:
			return ShulkerRenderLayers.Copper.GREEN;
		case RED:
			return ShulkerRenderLayers.Copper.RED;
		case BLACK:
			return ShulkerRenderLayers.Copper.BLACK;
		}

		return ShulkerRenderLayers.Copper.UNCOLORED;
	}
}
