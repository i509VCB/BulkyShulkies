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

import me.i509.fabric.bulkyshulkies.BulkyShulkiesMod;
import net.minecraft.client.render.block.entity.BlockEntityRenderDispatcher;
import net.minecraft.client.render.entity.model.ShulkerEntityModel;
import net.minecraft.client.util.SpriteIdentifier;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

import me.i509.fabric.bulkyshulkies.abstraction.DefaultReturnHashMap;
import me.i509.fabric.bulkyshulkies.block.material.iron.IronShulkerBoxBE;

@Environment(EnvType.CLIENT)
public class IronShulkerBoxBlockEntityRenderer extends AbstractMaterialBasedShulkerBlockEntityRenderer<IronShulkerBoxBE> {
	public IronShulkerBoxBlockEntityRenderer(BlockEntityRenderDispatcher blockEntityRenderDispatcher) {
		super(new ShulkerEntityModel<>(), blockEntityRenderDispatcher);
	}

	public static final Identifier shulkerTextureAtlas = new Identifier("textures/atlas/shulker_boxes.png");
	public static final SpriteIdentifier UNCOLORED_IDENTIFIER = new SpriteIdentifier(shulkerTextureAtlas, BulkyShulkiesMod.id("textures/be/shulker/iron/shulker"));
	public static final DefaultReturnHashMap<DyeColor, SpriteIdentifier> COLOR_TO_SPRITE_IDENTIFIER = Util.create(new DefaultReturnHashMap<>(UNCOLORED_IDENTIFIER), map -> {
		Arrays.stream(DyeColor.values()).forEach(color -> {
			map.put(color, new SpriteIdentifier(shulkerTextureAtlas, BulkyShulkiesMod.id("textures/be/shulker/iron/shulker_" + color.getName())));
		});
	});

	@Override
	public SpriteIdentifier getUncoloredSprite() {
		return UNCOLORED_IDENTIFIER;
	}

	@Override
	public SpriteIdentifier getColoredSprite(DyeColor color) {
		return COLOR_TO_SPRITE_IDENTIFIER.get(color);
	}
}
