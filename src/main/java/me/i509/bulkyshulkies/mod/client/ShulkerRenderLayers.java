/*
 * MIT License
 *
 * Copyright (c) 2019, 2020 i509VCB
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

package me.i509.bulkyshulkies.mod.client;

import java.util.Arrays;
import java.util.function.Consumer;

import me.i509.bulkyshulkies.mod.BulkyShulkiesImpl;
import me.i509.bulkyshulkies.mod.Uninstantiable;
import org.checkerframework.checker.nullness.qual.Nullable;

import net.minecraft.client.texture.SpriteAtlasTexture;
import net.minecraft.client.util.SpriteIdentifier;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Identifier;

import net.fabricmc.fabric.api.event.client.ClientSpriteRegistryCallback;

public final class ShulkerRenderLayers {
	public static final Identifier SHULKER_BOXES_ATLAS_TEXTURE = BulkyShulkiesImpl.id("textures/atlas/shulker_boxes.png");

	public static void makeAtlases(Consumer<SpriteIdentifier> consumer, String key) {
		consumer.accept(new SpriteIdentifier(SHULKER_BOXES_ATLAS_TEXTURE, BulkyShulkiesImpl.id(makePath(key, null))));
		Arrays.stream(DyeColor.values()).forEach(c -> consumer.accept(new SpriteIdentifier(SHULKER_BOXES_ATLAS_TEXTURE, BulkyShulkiesImpl.id(makePath(key, c)))));
	}

	public static void registerSprites(SpriteAtlasTexture atlas, ClientSpriteRegistryCallback.Registry registry, String key) {
		registry.register(BulkyShulkiesImpl.id(makePath(key, null)));
		Arrays.stream(DyeColor.values()).forEach(c -> registry.register(BulkyShulkiesImpl.id(makePath(key, c))));
	}

	public static void makeAtlas(Consumer<SpriteIdentifier> consumer, String key) {
		consumer.accept(new SpriteIdentifier(SHULKER_BOXES_ATLAS_TEXTURE, BulkyShulkiesImpl.id(makePath(key, null))));
	}

	public static void registerSprite(SpriteAtlasTexture atlas, ClientSpriteRegistryCallback.Registry registry, String key) {
		registry.register(BulkyShulkiesImpl.id(makePath(key, null)));
	}

	private static String makePath(String key, @Nullable DyeColor color) {
		if (color != null) {
			return "be/shulker/" + key + "/shulker_" + color.getName();
		}

		return "be/shulker/" + key + "/shulker";
	}

	private ShulkerRenderLayers() {
		Uninstantiable.whyDoIHearBossMusic(ShulkerRenderLayers.class);
	}
}
