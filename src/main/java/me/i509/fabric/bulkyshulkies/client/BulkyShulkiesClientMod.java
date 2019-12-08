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

package me.i509.fabric.bulkyshulkies.client;

import java.util.function.Consumer;

import org.lwjgl.glfw.GLFW;

import net.minecraft.client.texture.SpriteAtlasTexture;
import net.minecraft.client.util.InputUtil;
import net.minecraft.client.util.SpriteIdentifier;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.keybinding.FabricKeyBinding;
import net.fabricmc.fabric.api.client.keybinding.KeyBindingRegistry;
import net.fabricmc.fabric.api.client.rendereregistry.v1.BlockEntityRendererRegistry;
import net.fabricmc.fabric.api.client.screen.ScreenProviderRegistry;
import net.fabricmc.fabric.api.event.client.ClientSpriteRegistryCallback;

import me.i509.fabric.bulkyshulkies.BulkyShulkies;
import me.i509.fabric.bulkyshulkies.BulkyShulkiesMod;
import me.i509.fabric.bulkyshulkies.client.block.entity.renderer.CopperShulkerBoxBlockEntityRenderer;
import me.i509.fabric.bulkyshulkies.client.block.entity.renderer.IronShulkerBoxBlockEntityRenderer;
import me.i509.fabric.bulkyshulkies.client.screen.Generic11x7Screen;
import me.i509.fabric.bulkyshulkies.client.screen.Generic13x7Screen;
import me.i509.fabric.bulkyshulkies.client.screen.ScrollableScreen;
import me.i509.fabric.bulkyshulkies.container.ContainerKeys;
import me.i509.fabric.bulkyshulkies.registry.ShulkerBlockEntities;

@Environment(EnvType.CLIENT)
public class BulkyShulkiesClientMod implements ClientModInitializer {
	public static final FabricKeyBinding OPEN_SHULKER_HELMET = FabricKeyBinding.Builder.create(BulkyShulkies.id("open_helmet"), InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_V, BulkyShulkiesMod.MODID).build();

	@Override
	public void onInitializeClient() {
		KeyBindingRegistry.INSTANCE.addCategory(BulkyShulkiesMod.MODID);
		KeyBindingRegistry.INSTANCE.register(OPEN_SHULKER_HELMET);
		// TODO: ScreenProviderRegistry.INSTANCE.registerFactory(ContainerKeys.SHULKER_9x7_CONTAINER, /*Add Method reference to 9x7 screen when created.*/);
		ScreenProviderRegistry.INSTANCE.registerFactory(ContainerKeys.SHULKER_11x7_CONTAINER, Generic11x7Screen::createScreen);
		ScreenProviderRegistry.INSTANCE.registerFactory(ContainerKeys.SHULKER_13x7_CONTAINER, Generic13x7Screen::createScreen);
		ScreenProviderRegistry.INSTANCE.registerFactory(ContainerKeys.SHULKER_SCROLLABLE_CONTAINER, ScrollableScreen::createScreen);
		BlockEntityRendererRegistry.INSTANCE.register(ShulkerBlockEntities.COPPER_SHULKER_BOX, CopperShulkerBoxBlockEntityRenderer::new);
		BlockEntityRendererRegistry.INSTANCE.register(ShulkerBlockEntities.IRON_SHULKER_BOX, IronShulkerBoxBlockEntityRenderer::new);
		ClientSpriteRegistryCallback.event(ShulkerRenderLayers.SHULKER_BOXES_ATLAS_TEXTURE).register(BulkyShulkiesClientMod::registerSprites);
	}

	private static void registerSprites(SpriteAtlasTexture atlas, ClientSpriteRegistryCallback.Registry registry) {
		ShulkerRenderLayers.registerSprites(atlas, registry, TextureKeys.COPPER);
		ShulkerRenderLayers.registerSprites(atlas, registry, TextureKeys.IRON);
		ShulkerRenderLayers.registerSprites(atlas, registry, TextureKeys.SILVER);
		ShulkerRenderLayers.registerSprites(atlas, registry, TextureKeys.GOLD);
		ShulkerRenderLayers.registerSprites(atlas, registry, TextureKeys.DIAMOND);
		ShulkerRenderLayers.registerSprites(atlas, registry, TextureKeys.OBSIDIAN);
		ShulkerRenderLayers.registerSprites(atlas, registry, TextureKeys.CLEAR);
		ShulkerRenderLayers.registerSprites(atlas, registry, TextureKeys.PLATINUM);
	}

	public static void makeAtlases(Consumer<SpriteIdentifier> consumer) {
		ShulkerRenderLayers.makeAtlases(consumer, TextureKeys.COPPER);
		ShulkerRenderLayers.makeAtlases(consumer, TextureKeys.IRON);
		ShulkerRenderLayers.makeAtlases(consumer, TextureKeys.SILVER);
		ShulkerRenderLayers.makeAtlases(consumer, TextureKeys.GOLD);
		ShulkerRenderLayers.makeAtlases(consumer, TextureKeys.DIAMOND);
		ShulkerRenderLayers.makeAtlases(consumer, TextureKeys.OBSIDIAN);
		ShulkerRenderLayers.makeAtlases(consumer, TextureKeys.CLEAR);
		ShulkerRenderLayers.makeAtlases(consumer, TextureKeys.PLATINUM);
	}
}
