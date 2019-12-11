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
import me.i509.fabric.bulkyshulkies.ShulkerBoxKeys;
import me.i509.fabric.bulkyshulkies.client.block.entity.renderer.CopperShulkerBoxBERenderer;
import me.i509.fabric.bulkyshulkies.client.block.entity.renderer.DiamondShulkerBoxBERenderer;
import me.i509.fabric.bulkyshulkies.client.block.entity.renderer.GoldShulkerBoxBERenderer;
import me.i509.fabric.bulkyshulkies.client.block.entity.renderer.IronShulkerBoxBERenderer;
import me.i509.fabric.bulkyshulkies.client.block.entity.renderer.ObsidianShulkerBoxBERenderer;
import me.i509.fabric.bulkyshulkies.client.block.entity.renderer.PlatinumShulkerBoxBERenderer;
import me.i509.fabric.bulkyshulkies.client.block.entity.renderer.SilverShulkerBoxBERenderer;
import me.i509.fabric.bulkyshulkies.client.screen.Generic9x7Screen;
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
		ScreenProviderRegistry.INSTANCE.registerFactory(ContainerKeys.SHULKER_9x7_CONTAINER, Generic9x7Screen::createScreen);
		ScreenProviderRegistry.INSTANCE.registerFactory(ContainerKeys.SHULKER_11x7_CONTAINER, Generic11x7Screen::createScreen);
		ScreenProviderRegistry.INSTANCE.registerFactory(ContainerKeys.SHULKER_13x7_CONTAINER, Generic13x7Screen::createScreen);
		ScreenProviderRegistry.INSTANCE.registerFactory(ContainerKeys.SHULKER_SCROLLABLE_CONTAINER, ScrollableScreen::createScreen);
		BlockEntityRendererRegistry.INSTANCE.register(ShulkerBlockEntities.COPPER_SHULKER_BOX, CopperShulkerBoxBERenderer::new);
		BlockEntityRendererRegistry.INSTANCE.register(ShulkerBlockEntities.IRON_SHULKER_BOX, IronShulkerBoxBERenderer::new);
		BlockEntityRendererRegistry.INSTANCE.register(ShulkerBlockEntities.SILVER_SHULKER_BOX, SilverShulkerBoxBERenderer::new);
		BlockEntityRendererRegistry.INSTANCE.register(ShulkerBlockEntities.GOLD_SHULKER_BOX, GoldShulkerBoxBERenderer::new);
		BlockEntityRendererRegistry.INSTANCE.register(ShulkerBlockEntities.DIAMOND_SHULKER_BOX, DiamondShulkerBoxBERenderer::new);
		BlockEntityRendererRegistry.INSTANCE.register(ShulkerBlockEntities.OBSIDIAN_SHULKER_BOX, ObsidianShulkerBoxBERenderer::new);
		BlockEntityRendererRegistry.INSTANCE.register(ShulkerBlockEntities.PLATINUM_SHULKER_BOX, PlatinumShulkerBoxBERenderer::new);
		ClientSpriteRegistryCallback.event(ShulkerRenderLayers.SHULKER_BOXES_ATLAS_TEXTURE).register(BulkyShulkiesClientMod::registerSprites);
	}

	private static void registerSprites(SpriteAtlasTexture atlas, ClientSpriteRegistryCallback.Registry registry) {
		ShulkerRenderLayers.registerSprites(atlas, registry, ShulkerBoxKeys.COPPER);
		ShulkerRenderLayers.registerSprites(atlas, registry, ShulkerBoxKeys.IRON);
		ShulkerRenderLayers.registerSprites(atlas, registry, ShulkerBoxKeys.SILVER);
		ShulkerRenderLayers.registerSprites(atlas, registry, ShulkerBoxKeys.GOLD);
		ShulkerRenderLayers.registerSprites(atlas, registry, ShulkerBoxKeys.DIAMOND);
		ShulkerRenderLayers.registerSprites(atlas, registry, ShulkerBoxKeys.OBSIDIAN);
		ShulkerRenderLayers.registerSprites(atlas, registry, ShulkerBoxKeys.CLEAR);
		ShulkerRenderLayers.registerSprites(atlas, registry, ShulkerBoxKeys.PLATINUM);
	}

	public static void makeAtlases(Consumer<SpriteIdentifier> consumer) {
		ShulkerRenderLayers.makeAtlases(consumer, ShulkerBoxKeys.COPPER);
		ShulkerRenderLayers.makeAtlases(consumer, ShulkerBoxKeys.IRON);
		ShulkerRenderLayers.makeAtlases(consumer, ShulkerBoxKeys.SILVER);
		ShulkerRenderLayers.makeAtlases(consumer, ShulkerBoxKeys.GOLD);
		ShulkerRenderLayers.makeAtlases(consumer, ShulkerBoxKeys.DIAMOND);
		ShulkerRenderLayers.makeAtlases(consumer, ShulkerBoxKeys.OBSIDIAN);
		ShulkerRenderLayers.makeAtlases(consumer, ShulkerBoxKeys.CLEAR);
		ShulkerRenderLayers.makeAtlases(consumer, ShulkerBoxKeys.PLATINUM);
	}
}
