/*
 * MIT License
 *
 * Copyright (c) 2019-2020 i509VCB
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

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

import io.netty.buffer.Unpooled;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.lwjgl.glfw.GLFW;

import net.minecraft.block.entity.BlockEntity;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.options.KeyBinding;
import net.minecraft.client.render.block.entity.BlockEntityRenderDispatcher;
import net.minecraft.client.texture.SpriteAtlasTexture;
import net.minecraft.client.util.InputUtil;
import net.minecraft.client.util.SpriteIdentifier;
import net.minecraft.item.ItemConvertible;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Util;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.rendereregistry.v1.BlockEntityRendererRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.BuiltinItemRendererRegistry;
import net.fabricmc.fabric.api.client.screenhandler.v1.ScreenRegistry;
import net.fabricmc.fabric.api.event.client.ClientSpriteRegistryCallback;
import net.fabricmc.fabric.api.event.client.ClientTickCallback;
import net.fabricmc.fabric.api.network.ClientSidePacketRegistry;

import me.i509.fabric.bulkyshulkies.BulkyShulkiesMod;
import me.i509.fabric.bulkyshulkies.ShulkerBoxKeys;
import me.i509.fabric.bulkyshulkies.api.block.AbstractShulkerBoxBlock;
import me.i509.fabric.bulkyshulkies.api.block.entity.inventory.ShulkerBlockEntity;
import me.i509.fabric.bulkyshulkies.block.cursed.slab.ColoredSlabShulkerBoxBlock;
import me.i509.fabric.bulkyshulkies.block.cursed.slab.CursedSlabShulkerBoxBlockEntity;
import me.i509.fabric.bulkyshulkies.block.ender.EnderSlabBlock;
import me.i509.fabric.bulkyshulkies.block.ender.EnderSlabBoxBlockEntity;
import me.i509.fabric.bulkyshulkies.block.material.copper.CopperShulkerBoxBlock;
import me.i509.fabric.bulkyshulkies.block.material.copper.CopperShulkerBoxBlockEntity;
import me.i509.fabric.bulkyshulkies.block.material.diamond.DiamondShulkerBoxBlock;
import me.i509.fabric.bulkyshulkies.block.material.diamond.DiamondShulkerBoxBlockEntity;
import me.i509.fabric.bulkyshulkies.block.material.gold.GoldShulkerBoxBlock;
import me.i509.fabric.bulkyshulkies.block.material.gold.GoldShulkerBoxBlockEntity;
import me.i509.fabric.bulkyshulkies.block.material.iron.IronShulkerBoxBlock;
import me.i509.fabric.bulkyshulkies.block.material.iron.IronShulkerBoxBlockEntity;
import me.i509.fabric.bulkyshulkies.block.material.netherite.NetheriteShulkerBoxBlock;
import me.i509.fabric.bulkyshulkies.block.material.netherite.NetheriteShulkerBoxBlockEntity;
import me.i509.fabric.bulkyshulkies.block.material.obsidian.ObsidianShulkerBoxBlock;
import me.i509.fabric.bulkyshulkies.block.material.obsidian.ObsidianShulkerBoxBlockEntity;
import me.i509.fabric.bulkyshulkies.block.material.platinum.PlatinumShulkerBoxBlock;
import me.i509.fabric.bulkyshulkies.block.material.platinum.PlatinumShulkerBoxBlockEntity;
import me.i509.fabric.bulkyshulkies.block.material.silver.SilverShulkerBoxBlock;
import me.i509.fabric.bulkyshulkies.block.material.silver.SilverShulkerBoxBlockEntity;
import me.i509.fabric.bulkyshulkies.block.missing.MissingTexBoxBlock;
import me.i509.fabric.bulkyshulkies.block.missing.MissingTextureShulkerBoxBlockEntity;
import me.i509.fabric.bulkyshulkies.client.block.entity.renderer.EnderSlabBlockEntityRenderer;
import me.i509.fabric.bulkyshulkies.client.block.entity.renderer.Facing1x1ShulkerBlockEntityRenderer;
import me.i509.fabric.bulkyshulkies.client.block.entity.renderer.FacingSlabShulkerBoxBlockEntityRenderer;
import me.i509.fabric.bulkyshulkies.client.block.entity.renderer.PlatinumShulkerBlockEntityRenderer;
import me.i509.fabric.bulkyshulkies.client.screen.Generic11x7Screen;
import me.i509.fabric.bulkyshulkies.client.screen.Generic13x7Screen;
import me.i509.fabric.bulkyshulkies.client.screen.Generic9x7Screen;
import me.i509.fabric.bulkyshulkies.client.screen.GenericCustomSlotContainerScreen;
import me.i509.fabric.bulkyshulkies.registry.ShulkerBlockEntities;
import me.i509.fabric.bulkyshulkies.registry.ShulkerBlocks;
import me.i509.fabric.bulkyshulkies.registry.ShulkerNetworking;
import me.i509.fabric.bulkyshulkies.registry.ShulkerScreenHandlers;

@Environment(EnvType.CLIENT)
public class BulkyShulkiesClientMod implements ClientModInitializer {
	public static final KeyBinding OPEN_SHULKER_HELMET = KeyBindingHelper.registerKeyBinding(new KeyBinding("bulkyshulkies.keybinding.open_helmet", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_V, BulkyShulkiesMod.MODID));
	/**
	 * Provides the BlockEntity to use for the ItemRenderer.
	 */
	private static final Map<DyeColor, Map<Class<? extends AbstractShulkerBoxBlock>, ? extends ShulkerBlockEntity>> DYE_COLOR_TO_RENDER_BE = Util.make(new HashMap<>(), map -> {
		map.put(null, Util.make(new HashMap<>(), uncolored -> {
			uncolored.put(CopperShulkerBoxBlock.class, new CopperShulkerBoxBlockEntity());
			uncolored.put(IronShulkerBoxBlock.class, new IronShulkerBoxBlockEntity());
			uncolored.put(SilverShulkerBoxBlock.class, new SilverShulkerBoxBlockEntity());
			uncolored.put(GoldShulkerBoxBlock.class, new GoldShulkerBoxBlockEntity());
			uncolored.put(DiamondShulkerBoxBlock.class, new DiamondShulkerBoxBlockEntity());
			uncolored.put(ObsidianShulkerBoxBlock.class, new ObsidianShulkerBoxBlockEntity());
			uncolored.put(PlatinumShulkerBoxBlock.class, new PlatinumShulkerBoxBlockEntity());
			uncolored.put(MissingTexBoxBlock.class, new MissingTextureShulkerBoxBlockEntity());
			uncolored.put(ColoredSlabShulkerBoxBlock.class, new CursedSlabShulkerBoxBlockEntity());
			uncolored.put(NetheriteShulkerBoxBlock.class, new NetheriteShulkerBoxBlockEntity());
			// Ender Slab, this has no coloring at all
			uncolored.put(EnderSlabBlock.class, new EnderSlabBoxBlockEntity());
		}));

		for (DyeColor dyeColor : DyeColor.values()) {
			map.put(dyeColor, Util.make(new HashMap<>(), colored -> {
				colored.put(CopperShulkerBoxBlock.class, new CopperShulkerBoxBlockEntity(dyeColor));
				colored.put(IronShulkerBoxBlock.class, new IronShulkerBoxBlockEntity(dyeColor));
				colored.put(SilverShulkerBoxBlock.class, new SilverShulkerBoxBlockEntity(dyeColor));
				colored.put(GoldShulkerBoxBlock.class, new GoldShulkerBoxBlockEntity(dyeColor));
				colored.put(DiamondShulkerBoxBlock.class, new DiamondShulkerBoxBlockEntity(dyeColor));
				colored.put(ObsidianShulkerBoxBlock.class, new ObsidianShulkerBoxBlockEntity(dyeColor));
				colored.put(PlatinumShulkerBoxBlock.class, new PlatinumShulkerBoxBlockEntity(dyeColor));
				colored.put(MissingTexBoxBlock.class, new MissingTextureShulkerBoxBlockEntity(dyeColor));
				colored.put(ColoredSlabShulkerBoxBlock.class, new CursedSlabShulkerBoxBlockEntity(dyeColor));
				colored.put(NetheriteShulkerBoxBlock.class, new NetheriteShulkerBoxBlockEntity(dyeColor));
			}));
		}
	});

	private static void registerRenderer(ItemConvertible item, @Nullable DyeColor color) {
		BuiltinItemRendererRegistry.INSTANCE.register(item.asItem(), (stack, matrices, vertexConsumers, light, overlay) -> {
			BlockEntity blockEntity = BulkyShulkiesClientMod.getRenderBlockEntity((AbstractShulkerBoxBlock) item, color);
			BlockEntityRenderDispatcher.INSTANCE.renderEntity(blockEntity, matrices, vertexConsumers, light, overlay);
		});
	}

	@Override
	public void onInitializeClient() {
		ScreenRegistry.register(ShulkerScreenHandlers.SHULKER_9x1_SCREEN_HANDLER, GenericCustomSlotContainerScreen::new);
		ScreenRegistry.register(ShulkerScreenHandlers.SHULKER_9x2_SCREEN_HANDLER, GenericCustomSlotContainerScreen::new);
		ScreenRegistry.register(ShulkerScreenHandlers.SHULKER_9x3_SCREEN_HANDLER, GenericCustomSlotContainerScreen::new);
		ScreenRegistry.register(ShulkerScreenHandlers.SHULKER_9x4_SCREEN_HANDLER, GenericCustomSlotContainerScreen::new);
		ScreenRegistry.register(ShulkerScreenHandlers.SHULKER_9x5_SCREEN_HANDLER, GenericCustomSlotContainerScreen::new);
		ScreenRegistry.register(ShulkerScreenHandlers.SHULKER_9x6_SCREEN_HANDLER, GenericCustomSlotContainerScreen::new);
		ScreenRegistry.register(ShulkerScreenHandlers.SHULKER_9x7_SCREEN_HANDLER, Generic9x7Screen::new);
		ScreenRegistry.register(ShulkerScreenHandlers.SHULKER_11x7_SCREEN_HANDLER, Generic11x7Screen::new);
		ScreenRegistry.register(ShulkerScreenHandlers.SHULKER_13x7_SCREEN_HANDLER, Generic13x7Screen::new);
		ScreenRegistry.register(ShulkerScreenHandlers.ENDER_SLAB, GenericCustomSlotContainerScreen::new);

		ShulkerBlocks.iterateColors(ShulkerBlocks.COPPER_SHULKER_BOX, BulkyShulkiesClientMod::registerRenderer);
		ShulkerBlocks.iterateColors(ShulkerBlocks.IRON_SHULKER_BOX, BulkyShulkiesClientMod::registerRenderer);
		ShulkerBlocks.iterateColors(ShulkerBlocks.SILVER_SHULKER_BOX, BulkyShulkiesClientMod::registerRenderer);
		ShulkerBlocks.iterateColors(ShulkerBlocks.GOLD_SHULKER_BOX, BulkyShulkiesClientMod::registerRenderer);
		ShulkerBlocks.iterateColors(ShulkerBlocks.DIAMOND_SHULKER_BOX, BulkyShulkiesClientMod::registerRenderer);
		ShulkerBlocks.iterateColors(ShulkerBlocks.OBSIDIAN_SHULKER_BOX, BulkyShulkiesClientMod::registerRenderer);
		ShulkerBlocks.iterateColors(ShulkerBlocks.PLATINUM_SHULKER_BOX, BulkyShulkiesClientMod::registerRenderer);
		ShulkerBlocks.iterateColors(ShulkerBlocks.NETHERITE_SHULKER_BOX, BulkyShulkiesClientMod::registerRenderer);
		ShulkerBlocks.iterateColors(ShulkerBlocks.MISSING_TEX_SHULKER_BOX, BulkyShulkiesClientMod::registerRenderer);
		ShulkerBlocks.iterateColors(ShulkerBlocks.SLAB_SHULKER_BOX, BulkyShulkiesClientMod::registerRenderer);
		ShulkerBlocks.iterateColors(ShulkerBlocks.STAIR_SHULKER_BOX, BulkyShulkiesClientMod::registerRenderer);
		BulkyShulkiesClientMod.registerRenderer(ShulkerBlocks.ENDER_SLAB_BOX, null);

		BlockEntityRendererRegistry.INSTANCE.register(ShulkerBlockEntities.COPPER_SHULKER_BOX,
						d -> new Facing1x1ShulkerBlockEntityRenderer<>(d, ShulkerBoxKeys.COPPER));
		BlockEntityRendererRegistry.INSTANCE.register(ShulkerBlockEntities.IRON_SHULKER_BOX,
						d -> new Facing1x1ShulkerBlockEntityRenderer<>(d, ShulkerBoxKeys.IRON));
		BlockEntityRendererRegistry.INSTANCE.register(ShulkerBlockEntities.SILVER_SHULKER_BOX,
						d -> new Facing1x1ShulkerBlockEntityRenderer<>(d, ShulkerBoxKeys.SILVER));
		BlockEntityRendererRegistry.INSTANCE.register(ShulkerBlockEntities.GOLD_SHULKER_BOX,
						d -> new Facing1x1ShulkerBlockEntityRenderer<>(d, ShulkerBoxKeys.GOLD));
		BlockEntityRendererRegistry.INSTANCE.register(ShulkerBlockEntities.DIAMOND_SHULKER_BOX,
						d -> new Facing1x1ShulkerBlockEntityRenderer<>(d, ShulkerBoxKeys.DIAMOND));
		BlockEntityRendererRegistry.INSTANCE.register(ShulkerBlockEntities.OBSIDIAN_SHULKER_BOX,
						d -> new Facing1x1ShulkerBlockEntityRenderer<>(d, ShulkerBoxKeys.OBSIDIAN));
		BlockEntityRendererRegistry.INSTANCE.register(ShulkerBlockEntities.PLATINUM_SHULKER_BOX, PlatinumShulkerBlockEntityRenderer::new);
		BlockEntityRendererRegistry.INSTANCE.register(ShulkerBlockEntities.MISSING_TEX,
						d -> new Facing1x1ShulkerBlockEntityRenderer<>(d, ShulkerBoxKeys.MISSING_TEX));
		BlockEntityRendererRegistry.INSTANCE.register(ShulkerBlockEntities.ENDER_SLAB,
						d -> new EnderSlabBlockEntityRenderer(d, ShulkerBoxKeys.ENDER_SLAB));
		BlockEntityRendererRegistry.INSTANCE.register(ShulkerBlockEntities.SLAB_SHULKER_BOX,
						d -> new FacingSlabShulkerBoxBlockEntityRenderer<>(d, ShulkerBoxKeys.SLAB));
		ClientSpriteRegistryCallback.event(ShulkerRenderLayers.SHULKER_BOXES_ATLAS_TEXTURE).register(BulkyShulkiesClientMod::registerSprites);

		ClientTickCallback.EVENT.register(BulkyShulkiesClientMod::tickHelmet);
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
		ShulkerRenderLayers.registerSprites(atlas, registry, ShulkerBoxKeys.SLAB);
		ShulkerRenderLayers.registerSprites(atlas, registry, ShulkerBoxKeys.MISSING_TEX);
		// ShulkerRenderLayers.registerSprites(atlas, registry, ShulkerBoxKeys.STAIR);
		ShulkerRenderLayers.registerSprite(atlas, registry, ShulkerBoxKeys.ENDER_SLAB);
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
		ShulkerRenderLayers.makeAtlases(consumer, ShulkerBoxKeys.SLAB);
		ShulkerRenderLayers.makeAtlases(consumer, ShulkerBoxKeys.MISSING_TEX);
		ShulkerRenderLayers.makeAtlas(consumer, ShulkerBoxKeys.ENDER_SLAB);
	}

	public static BlockEntity getRenderBlockEntity(AbstractShulkerBoxBlock block, @Nullable DyeColor color) {
		return (BlockEntity) DYE_COLOR_TO_RENDER_BE.get(color).get(block.getClass());
	}

	private static void tickHelmet(MinecraftClient client) {
		if (OPEN_SHULKER_HELMET.wasPressed()) {
			ClientSidePacketRegistry.INSTANCE.sendToServer(ShulkerNetworking.HELMET_OPEN, new PacketByteBuf(Unpooled.buffer()));
		}
	}
}
