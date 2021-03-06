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

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

import io.netty.buffer.Unpooled;
import org.jetbrains.annotations.Nullable;
import org.lwjgl.glfw.GLFW;

import net.minecraft.block.entity.BlockEntity;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.options.KeyBinding;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
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
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.rendereregistry.v1.BlockEntityRendererRegistry;
import net.fabricmc.fabric.api.client.rendereregistry.v1.LivingEntityFeatureRendererRegistrationCallback;
import net.fabricmc.fabric.api.client.rendering.v1.BuiltinItemRendererRegistry;
import net.fabricmc.fabric.api.event.client.ClientSpriteRegistryCallback;
import net.fabricmc.fabric.api.network.ClientSidePacketRegistry;

import me.i509.bulkyshulkies.api.ShulkerBoxType;
import me.i509.bulkyshulkies.api.block.old.AbstractShulkerBoxBlock;
import me.i509.bulkyshulkies.api.block.old.entity.inventory.ShulkerBlockEntity;
import me.i509.bulkyshulkies.api.client.GameSession;
import me.i509.bulkyshulkies.api.registry.ShulkerBoxTypes;
import me.i509.bulkyshulkies.mod.BulkyShulkiesMod;
import me.i509.bulkyshulkies.mod.NetworkingConstants;
import me.i509.bulkyshulkies.mod.ShulkerBoxKeys;
import me.i509.bulkyshulkies.mod.block.old.cursed.slab.ColoredSlabShulkerBoxBlock;
import me.i509.bulkyshulkies.mod.block.old.cursed.slab.CursedSlabShulkerBoxBlockEntity;
import me.i509.bulkyshulkies.mod.block.old.ender.EnderSlabBlock;
import me.i509.bulkyshulkies.mod.block.old.ender.EnderSlabBoxBlockEntity;
import me.i509.bulkyshulkies.mod.client.registry.ClientShulkerRegistries;
import me.i509.bulkyshulkies.mod.client.render.EnderSlabBlockEntityRenderer;
import me.i509.bulkyshulkies.mod.client.render.Facing1x1ShulkerBlockEntityRenderer;
import me.i509.bulkyshulkies.mod.client.render.FacingSlabShulkerBoxBlockEntityRenderer;
import me.i509.bulkyshulkies.mod.client.render.PlatinumShulkerBlockEntityRenderer;
import me.i509.bulkyshulkies.mod.registry.ShulkerBlockSettings;

@Environment(EnvType.CLIENT)
public final class BulkyShulkiesClientMod implements ClientModInitializer {
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

	private static void registerItemRenderer(ItemConvertible item, @Nullable DyeColor color) {
		BuiltinItemRendererRegistry.INSTANCE.register(item.asItem(), (stack, mode, matrices, vertexConsumers, light, overlay) -> {
			BlockEntity blockEntity = BulkyShulkiesClientMod.getRenderBlockEntity((AbstractShulkerBoxBlock) item, color);
			MinecraftClient.getInstance().method_31975().renderEntity(blockEntity, matrices, vertexConsumers, light, overlay);
		});
	}

	public static void registerBlockRenderer(ShulkerBoxType type, ShulkerBlockEntityRendererFactory factory) {
		BlockEntityRendererRegistry.INSTANCE.register(type.getBlockEntityType(), context -> {
			return factory.create(type, context);
		});
	}

	public static GameSession getSession() {
		return null;
	}

	@Override
	public void onInitializeClient() {
		ClientShulkerRegistries.init();

		// TODO: Replace
		ShulkerBlockSettings.iterateColors(ShulkerBlockSettings.COPPER_SHULKER_BOX, BulkyShulkiesClientMod::registerItemRenderer);
		ShulkerBlockSettings.iterateColors(ShulkerBlockSettings.IRON_SHULKER_BOX, BulkyShulkiesClientMod::registerItemRenderer);
		ShulkerBlockSettings.iterateColors(ShulkerBlockSettings.SILVER_SHULKER_BOX, BulkyShulkiesClientMod::registerItemRenderer);
		ShulkerBlockSettings.iterateColors(ShulkerBlockSettings.GOLD_SHULKER_BOX, BulkyShulkiesClientMod::registerItemRenderer);
		ShulkerBlockSettings.iterateColors(ShulkerBlockSettings.DIAMOND_SHULKER_BOX, BulkyShulkiesClientMod::registerItemRenderer);
		ShulkerBlockSettings.iterateColors(ShulkerBlockSettings.OBSIDIAN_SHULKER_BOX, BulkyShulkiesClientMod::registerItemRenderer);
		ShulkerBlockSettings.iterateColors(ShulkerBlockSettings.PLATINUM_SHULKER_BOX, BulkyShulkiesClientMod::registerItemRenderer);
		ShulkerBlockSettings.iterateColors(ShulkerBlockSettings.NETHERITE_SHULKER_BOX, BulkyShulkiesClientMod::registerItemRenderer);
		ShulkerBlockSettings.iterateColors(ShulkerBlockSettings.MISSING_TEX_SHULKER_BOX, BulkyShulkiesClientMod::registerItemRenderer);
		ShulkerBlockSettings.iterateColors(ShulkerBlockSettings.SLAB_SHULKER_BOX, BulkyShulkiesClientMod::registerItemRenderer);
		ShulkerBlockSettings.iterateColors(ShulkerBlockSettings.STAIR_SHULKER_BOX, BulkyShulkiesClientMod::registerItemRenderer);
		BulkyShulkiesClientMod.registerItemRenderer(ShulkerBlockSettings.ENDER_SLAB_BOX, null);
		// TODO

		registerBlockRenderer(ShulkerBoxTypes.COPPER, Facing1x1ShulkerBlockEntityRenderer::new);
		registerBlockRenderer(ShulkerBoxTypes.IRON, Facing1x1ShulkerBlockEntityRenderer::new);
		registerBlockRenderer(ShulkerBoxTypes.SILVER, Facing1x1ShulkerBlockEntityRenderer::new);
		registerBlockRenderer(ShulkerBoxTypes.GOLD, Facing1x1ShulkerBlockEntityRenderer::new);
		registerBlockRenderer(ShulkerBoxTypes.DIAMOND, Facing1x1ShulkerBlockEntityRenderer::new);
		registerBlockRenderer(ShulkerBoxTypes.OBSIDIAN, Facing1x1ShulkerBlockEntityRenderer::new);
		registerBlockRenderer(ShulkerBoxTypes.PLATINUM, PlatinumShulkerBlockEntityRenderer::new);
		//registerBlockRenderer(ShulkerBoxTypes.MISSING_TEX, Facing1x1ShulkerBlockEntityRenderer::new);
		registerBlockRenderer(ShulkerBoxTypes.ENDER_SLAB, EnderSlabBlockEntityRenderer::new);
		registerBlockRenderer(ShulkerBoxTypes.SLAB, FacingSlabShulkerBoxBlockEntityRenderer::new);

		ClientSpriteRegistryCallback.event(ShulkerRenderLayers.SHULKER_BOXES_ATLAS_TEXTURE).register(BulkyShulkiesClientMod::registerSprites);

		ClientTickEvents.END_CLIENT_TICK.register(BulkyShulkiesClientMod::tickHelmet);

		LivingEntityFeatureRendererRegistrationCallback.EVENT.register((entityType, entityRenderer, registrationHelper, context) -> {
			// FIXME: Get config
			ShulkerHelmetLidFeatureRenderer.handleRegistration(null, entityType, entityRenderer, registrationHelper, context);
		});
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
			ClientSidePacketRegistry.INSTANCE.sendToServer(NetworkingConstants.OPEN_HELMET, new PacketByteBuf(Unpooled.buffer()));
		}
	}

	@FunctionalInterface
	@Environment(EnvType.CLIENT)
	public interface ShulkerBlockEntityRendererFactory {
		BlockEntityRenderer<? extends ShulkerBlockEntity> create(ShulkerBoxType type, BlockEntityRendererFactory.Context context);
	}
}
