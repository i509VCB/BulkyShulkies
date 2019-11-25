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

package me.i509.fabric.cursedshulkerboxes.client.block.entity.renderer;

import java.util.Arrays;

import net.minecraft.class_4722;
import net.minecraft.class_4730;
import net.minecraft.client.render.block.entity.BlockEntityRenderDispatcher;
import net.minecraft.client.render.entity.model.ShulkerEntityModel;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

import me.i509.fabric.cursedshulkerboxes.CursedShulkerBoxMod;
import me.i509.fabric.cursedshulkerboxes.abstraction.DefaultReturnHashMap;
import me.i509.fabric.cursedshulkerboxes.block.material.iron.IronShulkerBoxBlockEntity;

@Environment(EnvType.CLIENT)
public class IronShulkerBoxBlockEntityRenderer extends AbstractMaterialBasedShulkerBlockEntityRenderer<IronShulkerBoxBlockEntity> {
	public IronShulkerBoxBlockEntityRenderer(BlockEntityRenderDispatcher blockEntityRenderDispatcher) {
		super(new ShulkerEntityModel<>(), blockEntityRenderDispatcher);
	}

	public static final Identifier shulkerTextureAtlas = CursedShulkerBoxMod.id("textures/atlas/shulker_boxes.png");
	private static final class_4730 uncoloredWrappedSprite = new class_4730(shulkerTextureAtlas, CursedShulkerBoxMod.id("textures/blockentity/shulker/iron/shulker"));
	private static final DefaultReturnHashMap<DyeColor, class_4730> wrappedSprites = Util.create(new DefaultReturnHashMap<>(uncoloredWrappedSprite), map -> {
		Arrays.stream(DyeColor.values()).forEach(color -> {
			map.put(color, new class_4730(shulkerTextureAtlas, CursedShulkerBoxMod.id("textures/blockentity/shulker/iron/shulker_" + color.getName())));
		});
	});

	@Override
	public Identifier getShulkerTextureAtlas() {
		//return shulkerTextureAtlas;
		return class_4722.field_21704;
	}

	@Override
	public class_4730 getWrappedSprite(DyeColor color) {
		return wrappedSprites.get(color);
	}
}
